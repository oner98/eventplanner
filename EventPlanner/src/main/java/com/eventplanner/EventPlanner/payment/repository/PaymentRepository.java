package com.eventplanner.EventPlanner.payment.repository;

import com.eventplanner.EventPlanner.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
