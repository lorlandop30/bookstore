package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Genre;

import java.util.List;

public interface BookService {

    List<Book> findAll ();
    Book findBookById(Long id);
    List<Book> findAllByOrderByTitleAsc();
    List<Book> findAllByOrderByAuthorAsc();
    List<Book> findAllByOrderByPublicationDateAsc();
    List<Book> findAllByOrderByRatingAsc();
    List<Book> findAllByOrderByOurPriceAsc();

    List<Book> findByGenreOrderByTitleAsc(Genre genre);
    List<Book> findByGenreOrderByAuthorAsc(Genre genre);
    List<Book> findByGenreOrderByPublicationDateAsc(Genre genre);
    List<Book> findByGenreOrderByRatingAsc(Genre genre);
    List<Book> findByGenreOrderByOurPriceAsc(Genre genre);

    List<Book> searchTitle(String title);
    List<Book> searchAuthor(String author);
    double getAverageRating (Long id);
    int getNumberOfReviews (Long id);

}
