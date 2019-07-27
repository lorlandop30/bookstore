package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Order;
import com.bookstore.bookstore.models.ShoppingCart;
import com.bookstore.bookstore.models.User;

public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart,
                      User user);

    Order findOne(Long id);
}
