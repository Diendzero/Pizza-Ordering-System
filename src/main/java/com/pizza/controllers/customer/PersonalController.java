package com.pizza.controllers.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/menu/personalCenter")
public class PersonalController {

    @GetMapping("/mainPage")
    public ModelAndView viewPersonalInfo() {
        ModelAndView view = new ModelAndView("customer/personalMainPage");
        return view;
    }

    @GetMapping("/edit")
    public String editPersonalInfo() {
        return "customer/personalInfo";
    }

}