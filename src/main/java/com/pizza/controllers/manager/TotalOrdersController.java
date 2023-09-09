package com.pizza.controllers.manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pizza.models.OrderItems;
import com.pizza.models.Orders;
import com.pizza.models.Pizza;
import com.pizza.services.customer.PersonalOrdersService;


@Controller
public class TotalOrdersController {

    @Autowired
    PersonalOrdersService personalordersService;

    @GetMapping("totalOrderList/orders/{orderId}/items")
    public ResponseEntity<List<OrderItems>> getOrderItems(@PathVariable("orderId") int orderId) {
        List<OrderItems> orderItems = personalordersService.findOrderItemsByOrderId(orderId);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @GetMapping("totalOrderList/orders/pizzas/{pizzaId}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable("pizzaId") int pizzaId) {
        Pizza pizza = personalordersService.findPizzaById(pizzaId);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }
}
