package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.SavedCart;
import com.bookstore.bookstore.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavedCartRepository extends CrudRepository<SavedCart, Long> {
    List<SavedCart> findAllByUser(User user);
}

