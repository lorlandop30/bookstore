package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Genre;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}

