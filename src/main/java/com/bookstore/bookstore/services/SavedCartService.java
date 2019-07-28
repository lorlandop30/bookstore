package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.SavedCart;
import com.bookstore.bookstore.models.ShoppingCart;
import com.bookstore.bookstore.models.User;

import java.util.List;

public interface SavedCartService {
    List<SavedCart> addCartToSaved(ShoppingCart shoppingCart, User user, String name);
    List<SavedCart> findSavedCartsByUser(User user);
    void removeCartFromSaved(SavedCart savedCart);
}
