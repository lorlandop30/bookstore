package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.UserShipping;
import org.springframework.data.repository.CrudRepository;

public interface UserShippingRepository extends CrudRepository<UserShipping, Long> {

UserShipping findUserShippingById(Long id);
}
