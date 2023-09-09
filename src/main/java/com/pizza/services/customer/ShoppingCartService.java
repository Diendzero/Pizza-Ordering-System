package com.pizza.services.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pizza.models.OrderItems;
import com.pizza.models.Orders;
import com.pizza.models.Pizza;
import com.pizza.models.ShoppingCartItem;
import com.pizza.repositories.OrderItemsRepo;
import com.pizza.repositories.OrdersRepo;
import com.pizza.repositories.PizzaRepo;


@Service
public class ShoppingCartService {

    @Autowired
    OrderItemsRepo orderItemsRepo;
    @Autowired
    PizzaRepo pizzaRepo;
    @Autowired
    OrdersRepo ordersRepo;


    public List<ShoppingCartItem> getShoppingCartListByUsr(String phoneNumber, int isInShoppingCart) {
        List<ShoppingCartItem> shoppingCartList = new ArrayList<ShoppingCartItem>();
        List<OrderItems> orderItems = orderItemsRepo.findByPhoneNumberAndIsInShoppingCartOrderById(phoneNumber, isInShoppingCart);
        for (OrderItems item : orderItems) {
            int pizzaId = item.getPizzaId();
            Optional<Pizza> returnedPizza = pizzaRepo.findById(pizzaId);
            if (returnedPizza.isPresent()) {
                Pizza pizza = returnedPizza.get();
                shoppingCartList.add(new ShoppingCartItem(item.getId(), pizza.getName(), pizza.getSize(), pizza.getBase(), item.getNum(), pizza.getPrice()));
            } else {
            }
        }
        return shoppingCartList;
    }

    public int getTotalNumByUsr(String phoneNumber, int isInShoppingCart) {
        int totalNum = 0;
        List<OrderItems> orderItems = orderItemsRepo.findByPhoneNumberAndIsInShoppingCartOrderById(phoneNumber, isInShoppingCart);
        for (OrderItems item : orderItems) {
            totalNum += item.getNum();
        }
        return totalNum;
    }

    public String getTotalAmountByUsr(String phoneNumber, int isInShoppingCart) {
        int amount = 0;
        List<ShoppingCartItem> shoppingCartList = new ArrayList<ShoppingCartItem>();
        List<OrderItems> orderItems = orderItemsRepo.findByPhoneNumberAndIsInShoppingCartOrderById(phoneNumber, isInShoppingCart);
        for (OrderItems item : orderItems) {
            int pizzaId = item.getPizzaId();
            Optional<Pizza> returnedPizza = pizzaRepo.findById(pizzaId);
            if (returnedPizza.isPresent()) {
                Pizza pizza = returnedPizza.get();
                shoppingCartList.add(new ShoppingCartItem(item.getId(), pizza.getName(), pizza.getSize(), pizza.getBase(), item.getNum(), pizza.getPrice()));
            } else {
            }
        }
        for (ShoppingCartItem cartitem : shoppingCartList) {
            amount += cartitem.getPizzaNumber() * Integer.parseInt(cartitem.getUnitPrice());
        }
        return Integer.toString(amount);
    }

    public void editNum(int id, int num) {
        Optional<OrderItems> returnedItem = orderItemsRepo.findById(id);
        if (returnedItem.isPresent()) {
            OrderItems item = returnedItem.get();
            if (num > 0) {
                item.setNum(num);
                orderItemsRepo.save(item);
            } else {
                orderItemsRepo.deleteById(id);
            }
        } else {
        }
    }

    public void emptyByUsr(String phoneNumber, int isInShoppingCart) {
        List<OrderItems> orderItems = orderItemsRepo.findByPhoneNumberAndIsInShoppingCartOrderById(phoneNumber, isInShoppingCart);
        orderItemsRepo.deleteAll(orderItems);
    }

    public boolean isEmptyByUsr(String phoneNumber, int isInShoppingCart) {
        List<OrderItems> orderItems = orderItemsRepo.findByPhoneNumberAndIsInShoppingCartOrderById(phoneNumber, isInShoppingCart);
        return orderItems.isEmpty();

    }

    public int startOrder(String phoneNumber, int isInShoppingCart) {
        int orderId = 0;
        List<OrderItems> orderItems = orderItemsRepo.findByPhoneNumberAndIsInShoppingCartOrderById(phoneNumber, isInShoppingCart);
        orderId = orderItems.get(0).getId();
        for (OrderItems item : orderItems) {
            item.setInShoppingCart(0);
            item.setOrderId(orderId);
            orderItemsRepo.save(item);
        }
        return orderId;
    }

    public void addNewOrder(int orderId, String phoneNumber, String totalamount) {
        ordersRepo.save(new Orders(orderId, phoneNumber, totalamount));
    }

}