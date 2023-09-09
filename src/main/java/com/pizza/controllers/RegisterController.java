package com.pizza.controllers;

import com.pizza.models.PizzaUser;
import com.pizza.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ModelAndView register(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("phoneNumber") String phoneNumber,
                                 @RequestParam("role") String roles) {
        PizzaUser pizzaUser = new PizzaUser();
        pizzaUser.setUsername(username);
        pizzaUser.setPassword(passwordEncoder.encode(password));
        pizzaUser.setPhoneNumber(phoneNumber);
        pizzaUser.setRoles(roles);
        userService.newUser(pizzaUser);
        ModelAndView view = new ModelAndView("login");
        return view;
    }
}
