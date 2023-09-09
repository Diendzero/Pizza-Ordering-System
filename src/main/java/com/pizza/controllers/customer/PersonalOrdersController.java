package com.pizza.controllers.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.pizza.models.Comment;
import com.pizza.models.OrderItems;
import com.pizza.models.Orders;
import com.pizza.models.Pizza;
import com.pizza.services.customer.CommentService;
import com.pizza.services.customer.PersonalOrdersService;
import org.springframework.security.core.context.SecurityContextHolder;
import com.pizza.models.PizzaUserDetails;

@Controller
@RequestMapping("/menu/personalCenter")
public class PersonalOrdersController {

    @Autowired
    PersonalOrdersService personalordersService;

    @GetMapping("/orders")
    public ModelAndView toOrders() {
        ModelAndView view = new ModelAndView("customer/orders");
        String phoneNum = ((PizzaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPhoneNumber();

        try {
            view.addObject("UnpaidOrders", personalordersService.findUnpaidOrdersByPhoneNum(phoneNum));
            view.addObject("PaidOrders", personalordersService.findPaidOrdersByPhoneNum(phoneNum));
        } catch (NullPointerException e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }

    @GetMapping("/orders/searchById/{orderId}")
    public ResponseEntity<Orders> searchOrderById(@PathVariable("orderId") int orderId) {
        Orders order = personalordersService.findOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}/items")
    public ResponseEntity<List<OrderItems>> getOrderItems(@PathVariable("orderId") int orderId) {
        List<OrderItems> orderItems = personalordersService.findOrderItemsByOrderId(orderId);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @GetMapping("/orders/pizzas/{pizzaId}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable("pizzaId") int pizzaId) {
        Pizza pizza = personalordersService.findPizzaById(pizzaId);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    private final CommentService commentService;

    @Autowired
    public PersonalOrdersController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/orders/{orderId}/edit-comment")
    public String showEditCommentPage(@PathVariable("orderId") int orderId, Model model) {
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        model.addAttribute("orderId", orderId);
        return "customer/edit-comment";
    }

    @PostMapping("/orders/{orderId}/edit-comment")
    public String saveComment(@PathVariable("orderId") int orderId, @ModelAttribute("comment") Comment comment, RedirectAttributes redirectAttributes) {
        String phoneNum = ((PizzaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPhoneNumber();
        commentService.saveComment(orderId, comment, phoneNum);
        redirectAttributes.addFlashAttribute("message", "Comment saved successfully");
        return "redirect:/menu/personalCenter/orders";
    }

    @GetMapping("/orders/comment-center")
    public ModelAndView showCommentCenter() {
        String phoneNum = ((PizzaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPhoneNumber();
        List<Comment> comments = commentService.findAllCommentsByPhoneNum(phoneNum);
        Map<Integer, String> creationTimes = new HashMap<>();
        for (Comment comment : comments) {
            int orderId = comment.getOrderId();
            String creationTime = commentService.findCreationTimeByOrderId(orderId);
            creationTimes.put(orderId, creationTime);
        }
        ModelAndView view = new ModelAndView("customer/comment-center");
        view.addObject("comments", comments);
        view.addObject("creationTimes", creationTimes);
        return view;
    }

    @PostMapping("/orders/comment-center/delete/{id}")
    public String deleteComment(@PathVariable("id") int commentId, RedirectAttributes redirectAttributes) {
        commentService.deleteCommentById(commentId);
        redirectAttributes.addFlashAttribute("message", "Comment deleted successfully");
        return "redirect:/menu/personalCenter/orders/comment-center";
    }
}