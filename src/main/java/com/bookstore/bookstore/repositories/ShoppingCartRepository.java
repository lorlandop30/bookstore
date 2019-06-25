package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {


}
