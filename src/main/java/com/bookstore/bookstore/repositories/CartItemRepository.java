package com.bookstore.bookstore.repositories;

import java.util.List;
import com.bookstore.bookstore.models.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long>{
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
    CartItem findCartItemById(Long id);
}
