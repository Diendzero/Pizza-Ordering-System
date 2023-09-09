package com.pizza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.pizza.services.customer.MenuService;


@Controller
public class MainController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/managerMenu")
    public String getManagerMenu(Model model) {
        model.addAttribute("username", "Manager");
        return "manager/managerMenu";
    }

    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("menuList", menuService.getPizzaMenu());
        return "customer/menu";
    }

}
