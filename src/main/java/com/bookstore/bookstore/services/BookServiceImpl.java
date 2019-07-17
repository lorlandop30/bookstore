package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;

import com.bookstore.bookstore.models.Genre;
import com.bookstore.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public List<Book> findAllByOrderByTitleAsc() {
        return bookRepository.findAllByOrderByTitleAsc();
    }

    @Override
    public List<Book> findAllByOrderByAuthorAsc() {
        return bookRepository.findAllByOrderByAuthorAsc();
    }

    @Override
    public List<Book> findAllByOrderByPublicationDateAsc() {
        return bookRepository.findAllByOrderByPublicationDateAsc();
    }

    @Override
    public List<Book> findAllByOrderByRatingAsc() {
        return bookRepository.findAllByOrderByRatingAsc();
    }

    @Override
    public List<Book> findAllByOrderByOurPriceAsc() {
        return bookRepository.findAllByOrderByOurPriceAsc();
    }

    @Override
    public List<Book> findByGenreOrderByTitleAsc(Genre genre) {
        return bookRepository.findByGenreOrderByTitleAsc(genre);
    }

    @Override
    public List<Book> findByGenreOrderByAuthorAsc(Genre genre) {
        return bookRepository.findByGenreOrderByAuthorAsc(genre);
    }

    @Override
    public List<Book> findByGenreOrderByPublicationDateAsc(Genre genre) {
        return bookRepository.findByGenreOrderByPublicationDateAsc(genre);
    }

    @Override
    public List<Book> findByGenreOrderByRatingAsc(Genre genre) {
        return bookRepository.findByGenreOrderByRatingAsc(genre);
    }

    @Override
    public List<Book> findByGenreOrderByOurPriceAsc(Genre genre) {
        return bookRepository.findByGenreOrderByOurPriceAsc(genre);
    }

    @Override
    public List<Book> searchTitle(String title) {
        return null;
    }

    @Override
    public List<Book> searchAuthor(String author) {
        return null;
    }

}
