package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Payment;
import com.bookstore.bookstore.models.UserPayment;

public interface PaymentService {
    Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
