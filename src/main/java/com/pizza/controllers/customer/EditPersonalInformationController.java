package com.pizza.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pizza.services.customer.EditPersonalInfoService;

@Controller
@RequestMapping("/menu/personalCenter")
public class EditPersonalInformationController {

    @Autowired
    EditPersonalInfoService editPersonalInfoService;

    @GetMapping("/editPersonalInformation")
    public String EditPersonalInformation(Model model) {
        String username = editPersonalInfoService.getUserUsername();
        model.addAttribute("username", username);

        String password = editPersonalInfoService.getUserPassword();
        model.addAttribute("password", password);

        String phonenumber = editPersonalInfoService.getUserPhoneNumber();
        model.addAttribute("phonenumber", phonenumber);

        return "customer/editPersonalInformation";
    }

    @PostMapping("/editPersonalInformation")
    public String updatePersonalInformation(@RequestParam("user_phone") String user_phone,
                                            @RequestParam("username") String username,
                                            @RequestParam("password") String password) {
        if (!StringUtils.isEmpty(password)) {
            editPersonalInfoService.updateUserInformation(user_phone, username, password);
            return "redirect:/Diylogin";
        } else {
            editPersonalInfoService.updateUserInformationWithoutPassword(user_phone, username);
            return "redirect:/Diylogin";
        }
    }

    @GetMapping("/personalMainpage")
    public String mainPage() {
        return "customer/personalMainpage";
    }

}