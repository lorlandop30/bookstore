package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Review;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public interface BookService {

    List<Book> findAll ();
    Optional<Book> findBookById(Long id);
        List<Book> searchTitle(String title);
        List<Book> searchAuthor(String author);

    Book findOne(Long id);
    Book findBookById(Long id);
    Book findBookByIsbn(int isbn);
    List<Book> findByRating(double rating);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findAllByOrderByTitleAsc();
    List<Book> findAllByOrderByAuthorAsc();
    List<Book> findAllByOrderByPublicationdate();
    List<Book> findAllByOrderByRatingAsc();
    List<Book> findAllByOrderByRatingDesc();
    List<Book> findAllByOrderByPriceAsc();
    List<Book> findByTopsellerOrderByTitleAsc(Boolean topseller);
    List<Book> findByTopsellerOrderByAuthorAsc(Boolean topseller);
    List<Book> findByTopsellerOrderByPublicationdate(Boolean topseller);
    List<Book> findByTopsellerOrderByRatingAsc(Boolean topseller);
    List<Book> findByTopsellerOrderByRatingDesc(Boolean topseller);
    List<Book> findByTopsellerOrderByPriceAsc(Boolean topseller);
    List<String> findDistinctLanguageBy();
    List<String> findDistinctCategoryBy();
    List<String> findDistinctFormatBy();
    List<String> findDistinctGenreBy();

    double getAverageRating (Long id);
    int getNumberOfReviews (Long id);
    List<Review> getReviewsList(Long id);

}
