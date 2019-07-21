package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.SavedCart;
import com.bookstore.bookstore.models.ShoppingCart;
import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.repositories.SavedCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedCartServiceImpl implements SavedCartService {

    @Autowired
    private SavedCartRepository savedCartRepository;

    @Override
    public List<SavedCart> addCartToSaved(ShoppingCart shoppingCart, User user, String name){
        SavedCart savedCart = new SavedCart(shoppingCart, user, name);
        List<SavedCart> cartList = savedCartRepository.findAllByUser(user);
        cartList.add(savedCart);
        return cartList;
    }

    public void deleteAllSavedCarts(List<SavedCart> savedCartList){
        savedCartRepository.deleteAll();
    }

    public List<SavedCart> findSavedCartsByUser(User user){
        return savedCartRepository.findAllByUser(user);
    };

    @Override
    public void removeCartFromSaved(SavedCart savedCart) {
        savedCartRepository.delete(savedCart);
    }

}
