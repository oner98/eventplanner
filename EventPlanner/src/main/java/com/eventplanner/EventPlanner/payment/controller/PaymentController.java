package com.eventplanner.EventPlanner.payment.controller;

import com.eventplanner.EventPlanner.payment.model.Payment;
import com.eventplanner.EventPlanner.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(
            @RequestParam Long bookingId) {

        return ResponseEntity.ok(
                paymentService.createPayment(bookingId)
        );
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Payment> completePayment(@PathVariable Long id) {
        return ResponseEntity.ok(
                paymentService.completePayment(id)
        );
    }

}
