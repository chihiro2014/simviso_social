package com.simviso.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/9/2019
 * @Email: liujch1996@gmail.com
 */
@RabbitListener(queues = "simcode")
@Component
public class Customer2 {
    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("Simcode:"+ msg);
    }
}
