package com.group20.pizza.services.customer;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.group20.pizza.models.OrderItems;
import com.group20.pizza.models.Pizza;
import com.group20.pizza.repositories.OrderItemsRepo;
import com.group20.pizza.repositories.PizzaRepo;
import com.group20.pizza.models.PizzaUserDetails;

@Service
public class CustomizationService {
    @Autowired
    PizzaRepo pizzaRepo;
    @Autowired
    OrderItemsRepo orderItemsRepo;

    public int findcustomizedpizza(String name, String size, String base){
        return pizzaRepo.findByNameAndSizeAndBase(name, size, base).getId();
    }

    public String getCustomizedPizzaPrice(int pizzaId){
        Optional<Pizza> returnedpizza = pizzaRepo.findById(pizzaId);
        if(returnedpizza.isPresent()){
            Pizza pizza = returnedpizza.get();
            return pizza.getPrice();
        }
        else{
            return "";
        }
    }

    public void addToCart(int pizzaId){
        String phoneNum = ((PizzaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPhoneNumber();
        Optional<OrderItems> item = orderItemsRepo.findByPhoneNumberAndPizzaIdAndIsInShoppingCart(phoneNum, pizzaId, 1);
        if (item.isPresent()){
            OrderItems repeatItem = item.get();
            if(repeatItem.getNum() == 1000){
            }
            else{
                repeatItem.setNum(repeatItem.getNum() + 1);
                orderItemsRepo.save(repeatItem);
            }
        }
        else orderItemsRepo.save(new OrderItems(0, phoneNum, pizzaId));
    }

}
