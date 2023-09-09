package com.group20.pizza.services.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group20.pizza.models.OrderItems;
import com.group20.pizza.models.OrderdetailItem;
import com.group20.pizza.models.Orders;
import com.group20.pizza.models.Pizza;
import com.group20.pizza.repositories.OrderItemsRepo;
import com.group20.pizza.repositories.OrdersRepo;
import com.group20.pizza.repositories.PizzaRepo;

@Service
public class OrderdetailService {

    @Autowired
    OrderItemsRepo orderItemsRepo;
    @Autowired
    PizzaRepo pizzaRepo;

    public List<OrderdetailItem> getOrderdetailListByOrderId(int orderId) {
        List<OrderdetailItem> orderdetailList = new ArrayList<>();
        List<OrderItems> orderItems = orderItemsRepo.findByOrderIdOrderById(orderId);
        for (OrderItems item : orderItems) {
            int pizzaId = item.getPizzaId();
            Optional<Pizza> returnedPizza = pizzaRepo.findById(pizzaId);
            if (returnedPizza.isPresent()) {
                Pizza pizza = returnedPizza.get();
                OrderdetailItem orderdetailItem = new OrderdetailItem(item.getId(), pizza.getName(), pizza.getSize(), pizza.getBase(), item.getNum(), pizza.getPrice());
                orderdetailList.add(orderdetailItem);
            }
        }
        return orderdetailList;
    }

    public String getTotalAmountByOrderId(int orderId){
        int amount = 0;
        List<OrderdetailItem> orderdetailList = new ArrayList<OrderdetailItem>();
        List<OrderItems> orderItems = orderItemsRepo.findByOrderIdOrderById(orderId);
        for(OrderItems item: orderItems){
            int pizzaId = item.getPizzaId();
            Optional <Pizza> returnedPizza = pizzaRepo.findById(pizzaId);
            if(returnedPizza.isPresent()){
                Pizza pizza = returnedPizza.get();
                orderdetailList.add(new OrderdetailItem(item.getId(), pizza.getName(), pizza.getSize(), pizza.getBase(), item.getNum(), pizza.getPrice()));   
            }
            else{
            }  
        }
        for(OrderdetailItem cartitem: orderdetailList){
            amount += cartitem.getPizzaNumber() * Integer.parseInt(cartitem.getUnitPrice());
        }
        amount-=10;
        return Integer.toString(amount);
    }


    @Autowired
    private OrdersRepo orderRepository;

    public void updatePaymentStatus(int orderId) {
        Optional<Orders> orderoptional = orderRepository.findById(orderId);
            Orders order = orderoptional.get();
            order.setPaymentStatus("Paid");
            orderRepository.save(order);
  
    }

    public void chooseDeliveryAddress(int orderId, int addrId){
        Orders order = orderRepository.findById(orderId).orElse(null);
        order.setAddrId(addrId);
        orderRepository.save(order);
    }
}
