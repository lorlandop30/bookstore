package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.SavedCart;
import com.bookstore.bookstore.models.SavedCartList;
import com.bookstore.bookstore.models.User;

import java.util.List;

public interface SavedCartListService {
    SavedCartList saveAllCarts(List<SavedCart> cartList, User user);
}
