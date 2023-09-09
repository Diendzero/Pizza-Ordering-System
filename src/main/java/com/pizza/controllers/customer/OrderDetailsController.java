package com.pizza.controllers.customer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizza.services.customer.AddrService;
import com.pizza.services.customer.OrderdetailService;
import com.pizza.models.Address;

@Controller
@RequestMapping("/menu")
public class OrderDetailsController {
    @Autowired
    private OrderdetailService orderdetailService;
    @Autowired
    private AddrService addrService;

    @GetMapping("/orderdetails")
    public String showOrderDetails(@RequestParam(name = "orderId") int orderId, @RequestParam(name = "addrId", defaultValue = "0", required = false) Integer addrId, Model model) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderdetailList", orderdetailService.getOrderdetailListByOrderId(orderId));
        model.addAttribute("totalamount", orderdetailService.getTotalAmountByOrderId(orderId));
        if (addrId != null) {
            model.addAttribute("addrId", addrId);
        }
        return "customer/showOrderDetails";
    }

    @PostMapping("/orderdetails")
    public String storeOrderDetails() {
        return "redirect:/menu/orderdetails";
    }

    @GetMapping("/orderdetails/chooseAddr")
    public String getAddrForChosen(@RequestParam int orderId, Model model) {
        try {
            addrService.getAddrList();
        } catch (NullPointerException e) {
            model.addAttribute("errorMsg", e.getMessage());
            model.addAttribute("orderId", orderId);
            return "customer/chooseAddr";
        }
        List<Address> sortedAddr = addrService.getAddrList().stream()
                .sorted(Comparator.comparing(Address::isDefault, Comparator.reverseOrder())
                        .thenComparing(Address::getCreatedTime, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        model.addAttribute("addrList", sortedAddr);
        model.addAttribute("orderId", orderId);
        return "customer/chooseAddr";
    }

    @PostMapping("/orderdetails/chooseAddr/{id}/choose")
    @ResponseBody
    public String chooseAddr(@PathVariable int id, @RequestParam int orderId, HttpServletRequest request) {
        orderdetailService.chooseDeliveryAddress(orderId, id);
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/menu/orderdetails?orderId=" + orderId + "&addrId=" + id;
        return url;
    }


}
