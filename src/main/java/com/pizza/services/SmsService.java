package com.pizza.services;

import java.util.HashMap;


public interface SmsService {

    boolean sendSms(HashMap<String, Object> map, String phoneNumber);

}
