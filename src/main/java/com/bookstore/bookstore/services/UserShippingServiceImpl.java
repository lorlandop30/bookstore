package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.UserShipping;
import com.bookstore.bookstore.repositories.UserShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShippingServiceImpl implements UserShippingService {

    @Autowired
    private UserShippingRepository userShippingRepository;


    public UserShipping findById(Long id) {
        return userShippingRepository.findUserShippingById(id);
    }

    public void removeById(Long id) {
        userShippingRepository.deleteById(id);


    }
}