package com.pizza.controllers;

import com.pizza.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UsernamePhoneController {
    @Autowired
    private UserService userService;

    @GetMapping("/checkUsernameExists")
    public Map<String, Boolean> checkUsernameExists(@RequestParam("username") String username) {
        boolean usernameExists = userService.existsByUsername(username);
        Map<String, Boolean> result = new HashMap<>();
        result.put("usernameExists", usernameExists);
        return result;
    }

    @GetMapping("/checkPhoneNumberExists")
    public Map<String, Boolean> checkPhoneNumberExists(@RequestParam("phoneNumber") String phoneNumber) {
        boolean phoneNumberExists = userService.existsByPhoneNumber(phoneNumber);
        Map<String, Boolean> result = new HashMap<>();
        result.put("phoneNumberExists", phoneNumberExists);
        return result;
    }
}
