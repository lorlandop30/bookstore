package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public interface BookService {

    List<Book> findAll ();
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

    List<Book> findByGenreOrderByPriceAsc(String genre, Pageable pageable);
    List<Book> findByGenreOrderByTitleAsc(String genre, Pageable pageable);
    List<Book> findByGenreOrderByAuthorAsc(String genre, Pageable pageable);
    List<Book> findByGenreOrderByPublicationDateAsc(String genre, Pageable pageable);
    List<Book> findByGenreOrderByRatingAsc(String genre, Pageable pageable);
    List<Book> findByGenreOrderByRatingDesc(String genre, Pageable pageable);


    List<Book> findByGenreAndTopsellerOrderByPriceAsc(String genre, Pageable pageable);
    List<Book> findByGenreAndTopsellerOrderByTitleAsc(String genre, Pageable pageable);
    List<Book> findByGenreAndTopsellerOrderByAuthorAsc(String genre, Pageable pageable);
    List<Book> findByGenreAndTopsellerOrderByPublicationDateAsc(String genre, Pageable pageable);
    List<Book> findByGenreAndTopsellerOrderByRatingAsc(String genre, Pageable pageable);
    List<Book> findByGenreAndTopsellerOrderByRatingDesc(String genre, Pageable pageable);
    double getAverageRating (Long id);
    int getNumberOfReviews (Long id);
    List<Review> getReviewsList(Long id);

}
