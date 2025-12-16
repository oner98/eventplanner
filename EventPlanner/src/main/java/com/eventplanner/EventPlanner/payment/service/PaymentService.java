package com.eventplanner.EventPlanner.payment.service;

import com.eventplanner.EventPlanner.payment.model.Payment;
import com.eventplanner.EventPlanner.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment createPayment(Long bookingId) {

        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setAmount(100.0); // şimdilik sabit
        payment.setStatus("CREATED");
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    public Payment completePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus("PAID");
        return paymentRepository.save(payment);
    }
}
