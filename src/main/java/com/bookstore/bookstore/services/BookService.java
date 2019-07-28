package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Review;
import com.bookstore.bookstore.models.User;

import java.util.List;

public interface BookService {

    List<Book> findAll ();
    Book findOne(Long id);
    Book findBookById(Long id);
    List<String> findDistinctCategoryBy();
    List<String> findDistinctGenreBy();
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
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);


    double getAverageRating (Long id);
    int getNumberOfReviews (Long id);
    List<Review> getReviewsList(Long id);

}
