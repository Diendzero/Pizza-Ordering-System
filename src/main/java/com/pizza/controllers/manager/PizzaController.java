package com.pizza.controllers.manager;

import com.pizza.models.Pizza;
import com.pizza.services.manager.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(value = "/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping(value = "/select")
    public ModelAndView selectLike(String search) {
        ModelAndView view = new ModelAndView("manager/pizzalist::pizzaTable");
        view.addObject("pizza_list", pizzaService.selectLike(search));
        return view;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deletePizza(Integer id) {
        pizzaService.deletePizza(id);
        ModelAndView view = new ModelAndView("manager/pizzalist::pizzaTable");
        view.addObject("pizza_list", pizzaService.selectAllPizza());
        return view;
    }

    @PostMapping(value = "/insert")
    public ModelAndView insertPizza(Pizza pizza) {
        pizzaService.insertPizza(pizza);
        ModelAndView view = new ModelAndView("manager/pizzalist::pizzaTable");
        view.addObject("pizza_list", pizzaService.selectAllPizza());
        return view;
    }

    @PostMapping(value = "update")
    public ModelAndView updatePizza(Pizza pizza) {
        pizzaService.updatePizza(pizza);
        ModelAndView view = new ModelAndView("manager/pizzalist::pizzaTable");
        view.addObject("pizza_list", pizzaService.selectAllPizza());
        return view;
    }
}
