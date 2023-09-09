package com.pizza.services.manager;

import com.pizza.models.Pizza;
import java.util.List;


public interface PizzaService {

    void insertPizza(Pizza pizza);

    void deletePizza(Integer id);

    void updatePizza(Pizza pizza);

    List<Pizza> selectAllPizza();

    List<Pizza> selectLike(String search);

}
