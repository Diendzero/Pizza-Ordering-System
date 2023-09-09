package com.group20.pizza.services.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.group20.pizza.models.PizzaUser;
import com.group20.pizza.models.PizzaUserDetails;
import com.group20.pizza.repositories.UserRepo;

@Service
public class EditPersonalInfoService {

    @Autowired
    UserRepo pizzaUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String getUserUsername(){
        String username = ((PizzaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return username;
    }

    public String getUserPassword(){
        String password = ((PizzaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPassword();
        return password;
    }

    public String getUserPhoneNumber(){
        String phoneNumber = ((PizzaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPhoneNumber();
        return phoneNumber;
    }

    public void updateUserInformation(String phoneNumber, String username, String password){
        PizzaUser user = pizzaUserRepo.findByPhoneNumber(phoneNumber);

        if (user != null) {
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            pizzaUserRepo.save(user);
        }
    }

    public void updateUserInformationWithoutPassword(String phoneNumber, String username){
        PizzaUser user = pizzaUserRepo.findByPhoneNumber(phoneNumber);

        if (user != null) {
            user.setUsername(username);
            pizzaUserRepo.save(user);
        }
    }

}