package com.eventplanner.paymentservice.service;

import com.eventplanner.paymentservice.model.Payment;
import com.eventplanner.paymentservice.model.PaymentStatus;
import com.eventplanner.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment createPayment(Payment payment) {
        payment.setStatus(PaymentStatus.PAID);
        payment.setPaymentDate(LocalDateTime.now());
        return paymentRepository.save(payment);
    }
}
