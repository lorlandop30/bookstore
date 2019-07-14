package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.UserShipping;

public interface UserShippingService {
    UserShipping findById(Long id);

    void removeById(Long id);
}
