package com.pizza.controllers.manager;

import com.pizza.models.Orders;
import com.pizza.services.manager.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping(value = "/order")
public class OrdersController {

    @Autowired
    private OrdersService orderService;

    @GetMapping(value = "/select")
    public ModelAndView selectLike(String search) {
        ModelAndView view = new ModelAndView("manager/orderlist::ordersTable");
        try {
            view.addObject("order_list", orderService.selectLike(search));
        } catch (NullPointerException e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteOrder(Integer id) {
        orderService.deleteOrder(id);
        ModelAndView view = new ModelAndView("manager/orderlist::ordersTable");
        view.addObject("order_list", orderService.selectAllOrder());
        return view;
    }

    @PostMapping(value = "/insert")
    public ModelAndView insertOrder(Orders order) {
        // 插入数据
        orderService.insertOrder(order);
        // 回传代码片段
        ModelAndView view = new ModelAndView("manager/orderlist::ordersTable");
        view.addObject("order_list", orderService.selectAllOrder());
        return view;
    }

    @PostMapping(value = "update")
    public ModelAndView updateOrder(Orders order) {
        orderService.updateOrder(order);
        ModelAndView view = new ModelAndView("manager/orderlist::ordersTable");
        view.addObject("order_list", orderService.selectAllOrder());
        return view;
    }

    @PostMapping(value = "/deletemultiple")
    public ModelAndView deleteMultipleOrder(String ids) {
        orderService.deleteMultipleOrder(ids);
        ModelAndView view = new ModelAndView("manager/orderlist::ordersTable");
        view.addObject("order_list", orderService.selectAllOrder());
        return view;
    }

}
