package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Genre;

import java.util.List;

public interface BookService {
    List<Book> searchTitle(String title);
    List<Book> searchAuthor(String author);
    List<Book> searchyearofpublication(int yearofpublication);
}


