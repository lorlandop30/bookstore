package com.bookstore.bookstore.services;

import org.springframework.stereotype.Service;

import com.bookstore.bookstore.models.Payment;
import com.bookstore.bookstore.models.UserPayment;
import com.bookstore.bookstore.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{

    public Payment setByUserPayment(UserPayment userPayment, Payment payment) {
        payment.setType(userPayment.getType());
        payment.setHolderName(userPayment.getHolderName());
        payment.setCardNumber(userPayment.getCardNumber());
        payment.setExpiryMonth(userPayment.getExpiryMonth());
        payment.setExpiryYear(userPayment.getExpiryYear());
        payment.setCvc(userPayment.getCvc());

        return payment;
    }

}
