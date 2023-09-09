package com.pizza.serviceImpl;

import com.pizza.models.Orders;
import com.pizza.repositories.OrdersRepo;
import com.pizza.services.manager.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepo orderRepo;


    @Override
    public void insertOrder(Orders order) {
        orderRepo.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepo.deleteById(id);
    }

    @Override
    public void deleteMultipleOrder(String ids) {
        String[] ss = ids.split(",");
        for (String s : ss) {
            orderRepo.deleteById(Integer.parseInt(s));
        }

    }

    @Override
    public void updateOrder(Orders order) {
        orderRepo.saveAndFlush(order);
    }

    @Override
    public List<Orders> selectAllOrder() {
        if (orderRepo.count() == 0) throw new NullPointerException("No order record! Please wait patiently!!");
        return orderRepo.findAll();
    }

    @Override
    public List<Orders> selectLike(String search) {
        List<Orders> list = new ArrayList<>();
        Orders order = new Orders();
        order.setPaymentStatus(search);
        order.setDeliveryStatus(search);
        order.setCreationTime(search);

        if (search.contains("Paid")) {
            list = orderRepo.findByPaymentStatus(search);
            return list;
        }

        try {
            Integer id = Integer.parseInt(search);
            Optional<Orders> optional = orderRepo.findById(id);
            if (!optional.isPresent()) {
                list = selectVague(order);
            } else {
                list.add(optional.get());
            }
        } catch (NumberFormatException e) {
            list = selectVague(order);
        }
        if (list.size() == 0) throw new NullPointerException("No such order record!");
        return list;
    }

    private List<Orders> selectVague(Orders order) {
        List<Orders> list = null;

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("deliveryStatus", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("creationTime", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("paymentStatus", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase("id");
        Example<Orders> example = Example.of(order, matcher);

        list = orderRepo.findAll(example);
        return list;
    }

}
