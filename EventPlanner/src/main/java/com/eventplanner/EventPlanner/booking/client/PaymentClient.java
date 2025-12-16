package com.eventplanner.EventPlanner.booking.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void createPayment(Long bookingId) {
        String url = "http://localhost:8080/api/payments?bookingId=" + bookingId;
        restTemplate.postForObject(url, null, Void.class);
    }
}
