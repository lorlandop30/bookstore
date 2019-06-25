package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.BookToCartItem;
import com.bookstore.bookstore.models.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {
    void deleteByCartItem(CartItem cartItem);
}
