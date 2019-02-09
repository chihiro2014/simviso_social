package com.simviso.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/9/2019
 * @Email: liujch1996@gmail.com
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @RabbitListener
    public void excuteSms(Map<String,String> map){
        System.out.println("手机号:"+map.get("mobile"));
        System.out.println("验证码:"+map.get("checkcode"));

    }
}
