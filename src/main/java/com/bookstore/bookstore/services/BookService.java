package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll ();
    Book findBookById(Long id);
    double getAverageRating (Long id);
    int getNumberOfReviews (Long id);
    List<Book> searchTitle(String title);
    List<Book> searchAuthor(String author);
}
