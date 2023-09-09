package com.pizza.controllers.customer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pizza.models.Address;
import com.pizza.services.customer.AddrService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/menu/personalCenter/myAddress")
public class AddrController {
    @Autowired
    private AddrService addrService;

    // As a user, I want to view all my delivery addresses.
    @GetMapping("/viewAddress")
    public String getAddr(Model model) {
        try {
            addrService.getAddrList();
        } catch (NullPointerException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "customer/myAddr";
        }
        List<Address> sortedAddr = addrService.getAddrList().stream()
                .sorted(Comparator.comparing(Address::isDefault, Comparator.reverseOrder())
                        .thenComparing(Address::getCreatedTime, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        model.addAttribute("addrList", sortedAddr);
        return "customer/myAddr";
    }

    @PostMapping("/viewAddress/{id}/changeDefault")
    @ResponseBody
    public void addrChangeDefault(@PathVariable int id) {
        addrService.selectedAddrChangeDefault(id);
    }

    @PostMapping("/viewAddress/{id}/delete")
    @ResponseBody
    public void deleteItem(@PathVariable int id) {
        addrService.deleteSelectedAddr(id);
    }

    // As a user, I want to add a new delivery addresses.
    @GetMapping("/viewAddress/new")
    public String addNewAddr(Model model) {
        model.addAttribute("address", new Address());
        return "customer/newAddr";
    }

    @PostMapping("/viewAddress/new")
    public String confirmNewAddr(@ModelAttribute("address") Address address, Model model) {
        try {
            addrService.newAddr(address);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMsg", "Something wrong: " + e.getMessage());
            return "customer/newAddr";
        }
        model.addAttribute("successMsg", "Create delivery address successfully!");
        return "customer/newAddr";
    }

}