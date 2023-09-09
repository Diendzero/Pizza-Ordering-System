package com.group20.pizza.controllers.manager;

import com.group20.pizza.models.Orders;
import com.group20.pizza.services.manager.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class OrderListController {

    @Autowired
    private OrdersService orderService;

    @RequestMapping(value = "/orderlist")
    public ModelAndView toOrderList() {
        ModelAndView view = new ModelAndView("manager/orderlist");
        try {
            view.addObject("order_list", orderService.selectAllOrder());
        }catch(NullPointerException e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }

}
