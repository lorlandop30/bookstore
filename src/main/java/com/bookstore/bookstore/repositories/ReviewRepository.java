package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {

    /*
     * Note that this method is not implemented and its working code will be
     * automatically generated from its signature by Spring Data JPA.
     */
    List<Review> findByBookisbn(Long isbn);

}
