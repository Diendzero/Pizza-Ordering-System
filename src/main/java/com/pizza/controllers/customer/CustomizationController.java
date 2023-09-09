package com.pizza.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pizza.services.customer.CustomizationService;

@Controller
public class CustomizationController {

    @Autowired
    CustomizationService customizationService;

    @GetMapping("/menu/customization")
    public String showCustomization(@RequestParam(name = "name") String name, @RequestParam(name = "size") String size, @RequestParam(name = "base") String base, @RequestParam(name = "imageurl") String imageurl, @RequestParam(name = "description") String description, Model model) {
        int pizzaId = customizationService.findcustomizedpizza(name, size, base);
        String price = customizationService.getCustomizedPizzaPrice(pizzaId);
        model.addAttribute("price", price);
        model.addAttribute("name", name);
        model.addAttribute("size", size);
        model.addAttribute("base", base);
        model.addAttribute("pizzaId", pizzaId);
        model.addAttribute("imageurl", imageurl);
        model.addAttribute("description", description);
        return "customer/customization";
    }

    @PostMapping("/menu/customization")
    public String addToCart(@RequestParam(name = "pizzaId") int pizzaId) {
        customizationService.addToCart(pizzaId);
        return "redirect:/menu/shoppingcart";
    }

}
