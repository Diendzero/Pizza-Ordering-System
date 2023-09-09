package com.pizza.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pizza.services.customer.OrderdetailService;

@Controller
public class PaymentController {

    @GetMapping("/menu/payment")
    public String showPayment(@RequestParam(name = "orderId") int orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "customer/payment";
    }

    @Autowired
    private OrderdetailService orderdetailService;

    @PostMapping("/menu/payment")
    public String updatePaymentStatus(@RequestParam("orderId") int orderId) {
        orderdetailService.updatePaymentStatus(orderId);
        return "paymentSucceeded";
    }

}
