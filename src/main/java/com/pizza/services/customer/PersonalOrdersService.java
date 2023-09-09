package com.pizza.services.customer;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pizza.models.OrderItems;
import com.pizza.models.Orders;
import com.pizza.models.Pizza;
import com.pizza.repositories.OrderItemsRepo;
import com.pizza.repositories.OrdersRepo;
import com.pizza.repositories.PizzaRepo;


@Service
public class PersonalOrdersService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrderItemsRepo orderItemsRepo;

    @Autowired
    private PizzaRepo pizzaRepo;


    public List<Orders> findOrdersByPhoneNum(String phoneNum) {

        if (ordersRepo.count() == 0) throw new NullPointerException("No order record! Please wait patiently!!");

        return ordersRepo.findOrdersByPhoneNum(phoneNum)
                .stream()
                .filter(order -> "Unpaid".equals(order.getPaymentStatus()))
                .collect(Collectors.toList());

    }


    public Orders findOrderById(int id) {
        return ordersRepo.findById(id).orElse(null);
    }

    public List<OrderItems> findOrderItemsByOrderId(int orderId) {
        return orderItemsRepo.findByOrderId(orderId);
    }

    public Pizza findPizzaById(int id) {
        return pizzaRepo.findById(id).orElse(null);
    }

    public List<Orders> findPaidOrdersByPhoneNum(String phoneNumber) {
        return ordersRepo.findOrdersByPhoneNumAndPaymentStatus(phoneNumber, "Paid");
    }

    public List<Orders> findUnpaidOrdersByPhoneNum(String phoneNumber) {
        return ordersRepo.findOrdersByPhoneNumAndPaymentStatus(phoneNumber, "Unpaid");
    }

}