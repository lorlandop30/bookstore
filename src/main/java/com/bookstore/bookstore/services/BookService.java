package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll ();
    Optional<Book> findBookById(Long id);
        List<Book> searchTitle(String title);
        List<Book> searchAuthor(String author);

    Book findOne(Long id);
    Book findBookById(Long id);
    double getAverageRating (Long id);
    int getNumberOfReviews (Long id);
    List<Book> searchTitle(String title);
    List<Book> searchAuthor(String author);
}
