package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.User;
import org.springframework.data.repository.CrudRepository;

import com.bookstore.bookstore.models.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long>{
    Order findOrderById(Long id);
    Order findByUserId(Long id);

}
