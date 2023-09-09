package com.group20.pizza.services.customer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group20.pizza.models.Pizza;
import com.group20.pizza.repositories.PizzaRepo;

@Service
public class MenuService {

    @Autowired
    private PizzaRepo pizzaRepo;

    public List<Pizza> getPizzaMenu(){
        List<Pizza> allPizzas = pizzaRepo.findAll();
        List<Pizza> result = allPizzas.stream()
            .collect(Collectors.groupingBy(Pizza::getName,
                Collectors.minBy(Comparator.comparing(Pizza::getPrice))))
            .values().stream()
            .map(Optional::get)
            .collect(Collectors.toList());
        return result;
    }
}
