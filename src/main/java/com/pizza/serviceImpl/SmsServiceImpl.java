package com.pizza.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.utils.StringUtils;
import com.pizza.services.SmsService;
import org.springframework.stereotype.Service;
import java.util.HashMap;


@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public boolean sendSms(HashMap<String, Object> map, String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            return false;
        }
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tNn9841f1qv2n1xCJwm", "IYNyw0eKGDVMqsg24LES8Io6EO0w33");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();

        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "贝咖木");
        request.putQueryParameter("TemplateCode", "SMS_460680114");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

}
