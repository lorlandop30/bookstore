package com.bookstore.bookstore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.bookstore.models.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
