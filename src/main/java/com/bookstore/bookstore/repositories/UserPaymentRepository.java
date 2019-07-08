package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.UserPayment;
import org.springframework.data.repository.CrudRepository;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long> {

    UserPayment findUserPaymentById(Long id);


}