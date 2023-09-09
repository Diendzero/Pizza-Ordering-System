package com.pizza.controllers.manager;

import com.pizza.services.manager.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class UserInfoController {

    @Autowired
    private ManagerUserService managerUserService;

    @RequestMapping(value = "/userInfo")
    public ModelAndView toIndex() {
        ModelAndView view = new ModelAndView("manager/userInfo");
        view.addObject("user_list", managerUserService.selectAllUser());
        return view;
    }
}