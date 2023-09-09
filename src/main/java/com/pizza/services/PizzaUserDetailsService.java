package com.pizza.services;

import com.pizza.repositories.UserRepo;
import com.pizza.models.PizzaUser;
import com.pizza.models.PizzaUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


@Component
public class PizzaUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public PizzaUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PizzaUser pizzaUser = userRepo.findUserByUsername(username);

        if (ObjectUtils.isEmpty(pizzaUser)) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new PizzaUserDetails(pizzaUser);

    }

}
