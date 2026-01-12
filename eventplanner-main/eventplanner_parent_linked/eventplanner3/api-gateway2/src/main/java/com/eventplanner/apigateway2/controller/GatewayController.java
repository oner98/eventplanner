package com.eventplanner.apigateway2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final RestTemplate restTemplate;

    public GatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 🎟 EVENT SERVICE
    @RequestMapping("/events/**")
    public ResponseEntity<String> eventProxy() {
        return restTemplate.getForEntity(
                "http://EVENT-SERVICE/events",
                String.class
        );
    }

    // 📅 BOOKING SERVICE
    @RequestMapping("/bookings/**")
    public ResponseEntity<String> bookingProxy() {
        return restTemplate.getForEntity(
                "http://BOOKING-SERVICE/bookings",
                String.class
        );
    }

    // 💳 PAYMENT SERVICE
    @RequestMapping("/payments/**")
    public ResponseEntity<String> paymentProxy() {
        return restTemplate.getForEntity(
                "http://PAYMENT-SERVICE/payments",
                String.class
        );
    }
}
