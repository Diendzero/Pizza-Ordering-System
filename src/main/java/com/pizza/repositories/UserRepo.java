package com.pizza.repositories;

import com.pizza.models.PizzaUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<PizzaUser, Integer> {

    boolean existsByUsername(String username);

    boolean existsByPhoneNumber(String phoneNumber);

    PizzaUser findUserByUsername(String username);

    PizzaUser findByPhoneNumber(String phoneNumber);

}
