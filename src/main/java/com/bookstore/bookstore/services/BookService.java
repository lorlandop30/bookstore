package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll ();
    Book findBookById(Long id);
    List<Book> findAllByOrderByTitleAsc();
    List<Book> findAllByOrderByAuthorAsc();
    List<Book> findAllByOrderByPublicationDateAsc();
    List<Book> findAllByOrderByRatingAsc();
    List<Book> findAllByOrderByPriceAsc();

    List<Book> searchTitle(String title);
        List<Book> searchAuthor(String author);
}
