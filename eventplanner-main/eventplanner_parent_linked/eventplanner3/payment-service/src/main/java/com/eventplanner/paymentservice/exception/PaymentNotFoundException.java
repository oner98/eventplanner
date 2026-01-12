package com.eventplanner.paymentservice.exception;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(Long id) {
        super("Payment not found with id: " + id);
    }
}

