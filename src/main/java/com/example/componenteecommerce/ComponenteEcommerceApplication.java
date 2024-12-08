package com.example.componenteecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ComponenteEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComponenteEcommerceApplication.class, args);
    }

}
