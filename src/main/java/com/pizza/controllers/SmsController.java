package com.pizza.controllers;

import com.pizza.serviceImpl.SmsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@RestController
@CrossOrigin
public class SmsController {
    @Autowired
    private SmsServiceImpl smsServiceImpl;

    @GetMapping("/sendSms/{phoneNumber}")
    public boolean sendMsm(@PathVariable String phoneNumber, HttpSession session) {
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        int randomNumber = random.nextInt(max - min + 1) + min;
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", randomNumber);
        session.setAttribute("verification_code" + phoneNumber, randomNumber);
        session.setMaxInactiveInterval(60);
        boolean isSend = smsServiceImpl.sendSms(map, phoneNumber);
        return isSend;
    }

    @PostMapping("/verifyCode")
    public ResponseEntity<Map<String, Object>> verifyCode(@RequestBody Map<String, String> payload, HttpSession session) {
        String phoneNumber = payload.get("phoneNumberInput");
        String inputCode = payload.get("code");

        String storedCode = String.valueOf(session.getAttribute("verification_code" + phoneNumber));
        Map<String, Object> response = new HashMap<>();

        if (inputCode != null && inputCode.equals(storedCode)) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return ResponseEntity.ok(response);
    }


}
