package com.eventplanner.EventPlanner.booking.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EventClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void checkEvent(Long eventId) {
        String url = "http://localhost:8080/api/events/" + eventId;
        restTemplate.getForObject(url, Void.class);
    }
}
