package com.pizza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pizza.models.Pizza;


public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

    Pizza findByNameAndSizeAndBase(String name, String size, String base);

}