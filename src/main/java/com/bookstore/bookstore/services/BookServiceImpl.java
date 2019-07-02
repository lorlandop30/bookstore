package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService
{
    @Override
    public List<Book> searchTitle(String title) {
        return null;
    }

    @Override
    public List<Book> searchAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> searchISBN(String ISBN) {
        return null;
    }

    @Override
    public List<Book> searchyearofpublication(int yearofpublication) {
        return null;
    }
}
