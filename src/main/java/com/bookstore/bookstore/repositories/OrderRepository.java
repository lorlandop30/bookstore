package com.bookstore.bookstore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.bookstore.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
    Order findOrderById(Long id);

}
