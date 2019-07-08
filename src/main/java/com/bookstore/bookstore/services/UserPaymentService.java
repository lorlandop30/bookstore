package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.UserPayment;

public interface UserPaymentService {
    UserPayment findById(Long id);

    void removeById(Long id);
}
