package com.pizza.controllers.manager;

import com.pizza.models.PizzaUser;
import com.pizza.services.manager.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private ManagerUserService managerUserService;

    @GetMapping(value = "/select")
    public ModelAndView selectLike(String search) {
        ModelAndView view = new ModelAndView("index::userTable");
        view.addObject("user_list", managerUserService.selectLike(search));
        return view;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteUser(Integer id) {
        managerUserService.deleteUser(id);
        ModelAndView view = new ModelAndView("index::userTable");
        view.addObject("user_list", managerUserService.selectAllUser());
        return view;
    }

    @PostMapping(value = "/insert")
    public ModelAndView insertUser(PizzaUser user) {
        managerUserService.insertUser(user);
        ModelAndView view = new ModelAndView("index::userTable");
        view.addObject("user_list", managerUserService.selectAllUser());
        return view;
    }

    @PostMapping(value = "update")
    public ModelAndView updateUser(PizzaUser user) {
        managerUserService.updateUser(user);
        ModelAndView view = new ModelAndView("index::userTable");
        view.addObject("user_list", managerUserService.selectAllUser());
        return view;
    }
}
