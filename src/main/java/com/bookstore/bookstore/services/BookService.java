package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book findOne(Long id);
    List<Book> findAll();
}
