package com.simviso.gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/2/2019
 * @Email: liujch1996@gmail.com
 */
@SpringBootApplication
@EnableCaching
public class GatheringApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatheringApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
