package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.SavedCart;
import com.bookstore.bookstore.models.SavedCartList;
import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.repositories.SavedCartListRepository;

import java.util.List;

public class SavedCartListServiceImpl {

    private SavedCartListRepository savedCartListRepository;

    public SavedCartList saveAllCarts(List<SavedCart> cartList, User user){
        SavedCartList savedCartList = new SavedCartList(cartList, user);
        savedCartListRepository.save(savedCartList);
        return savedCartList;
    }
}
