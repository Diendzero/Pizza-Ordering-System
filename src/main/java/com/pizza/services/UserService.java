package com.pizza.services;

import com.pizza.repositories.UserRepo;
import com.pizza.models.PizzaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public PizzaUser newUser(PizzaUser pizzaUser) {
        return userRepo.save(pizzaUser);
    }

    public boolean existsByUsername(String userName) {
        return userRepo.existsByUsername(userName);
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepo.existsByPhoneNumber(phoneNumber);
    }

    public PizzaUser findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

}
