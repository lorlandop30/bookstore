package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.UserPayment;
import com.bookstore.bookstore.repositories.UserPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentServiceImpl implements UserPaymentService{

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    public UserPayment findById(Long id) {
        return userPaymentRepository.findUserPaymentById(id);
    }

    public void removeById(Long id) {
        userPaymentRepository.deleteById(id);
    }
}
