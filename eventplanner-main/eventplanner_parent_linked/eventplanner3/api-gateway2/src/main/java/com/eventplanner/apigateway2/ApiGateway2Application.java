package com.eventplanner.apigateway2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateway2Application {

    public static void main(String[] args) {
        SpringApplication.run(ApiGateway2Application.class, args);
    }
}
