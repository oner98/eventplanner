package com.eventplanner.EventPlanner.booking.service;

import com.eventplanner.EventPlanner.booking.client.EventClient;
import com.eventplanner.EventPlanner.booking.client.PaymentClient;
import com.eventplanner.EventPlanner.booking.model.Booking;
import com.eventplanner.EventPlanner.booking.repository.BookingRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventClient eventClient;
    private final PaymentClient paymentClient;

    public Booking createBooking(Long userId, Long eventId) {

        eventClient.checkEvent(eventId);

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setEventId(eventId);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("CREATED");

        Booking saved = bookingRepository.save(booking);
        paymentClient.createPayment(saved.getId());

        return saved;
    }
}
