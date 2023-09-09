package com.pizza.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pizza.services.customer.ShoppingCartService;
import org.springframework.security.core.context.SecurityContextHolder;
import com.pizza.models.PizzaUserDetails;

@Controller
@RequestMapping("/menu")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/shoppingcart")
    public String showShoppingCart(Model model) {
        String phoneNum = ((PizzaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPhoneNumber();
        model.addAttribute("phoneNumber", phoneNum);
        model.addAttribute("shoppingcartlist", shoppingCartService.getShoppingCartListByUsr(phoneNum, 1));
        model.addAttribute("totalnum", shoppingCartService.getTotalNumByUsr(phoneNum, 1));
        model.addAttribute("totalamount", shoppingCartService.getTotalAmountByUsr(phoneNum, 1));
        if (shoppingCartService.isEmptyByUsr(phoneNum, 1)) {
            model.addAttribute("emptycart", "No pizza selected yet!");
        }
        return "customer/showShoppingCart";
    }

    @PostMapping("/shoppingcart")
    public String shoppingCartOperations(@RequestParam(name = "action") String action,
                                         @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                                         @RequestParam(name = "id", required = false, defaultValue = "0") int itemId,
                                         @RequestParam(name = "num", required = false, defaultValue = "0") int pizzaNumber) {

        if (action.equals("emptycart")) {
            shoppingCartService.emptyByUsr(phoneNumber, 1);
            return "redirect:/menu/shoppingcart";
        } else if (action.equals("editnum")) {
            shoppingCartService.editNum(itemId, pizzaNumber);
            return "redirect:/menu/shoppingcart";
        } else {
            String totalamount = shoppingCartService.getTotalAmountByUsr(phoneNumber, 1);
            int orderId = shoppingCartService.startOrder(phoneNumber, 1);
            shoppingCartService.addNewOrder(orderId, phoneNumber, totalamount);
            return "redirect:/menu/orderdetails?orderId=" + orderId;
        }


    }

}