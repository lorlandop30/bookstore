package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Review;
import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }


    @Override
    public List<String> findDistinctCategoryBy(){
        return bookRepository.findDistinctCategoryBy();
    }

    @Override
    public List<String> findDistinctGenreBy() { return bookRepository.findDistinctGenreBy();}

    @Override
    public List<Book> findAllByOrderByTitleAsc() {
        return bookRepository.findAllByOrderByTitleAsc();
    }

    @Override
    public List<Book> findAllByOrderByAuthorAsc() {
        return bookRepository.findAllByOrderByAuthorAsc();
    }

    @Override
    public List<Book> findAllByOrderByPublicationdate() {
        return bookRepository.findAllByOrderByPublicationdate();
    }

    @Override
    public List<Book> findAllByOrderByRatingAsc() {
        return bookRepository.findAllByOrderByRatingAsc();
    }

    @Override
    public List<Book> findAllByOrderByRatingDesc() {
        return bookRepository.findAllByOrderByRatingDesc();

    }

    @Override
    public List<Book> findAllByOrderByPriceAsc(){
        return bookRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Book> findByTopsellerOrderByTitleAsc(Boolean topseller) {
        return bookRepository.findByTopsellerOrderByTitleAsc(topseller);
    }

    @Override
    public List<Book> findByTopsellerOrderByAuthorAsc(Boolean topseller) {
        return bookRepository.findByTopsellerOrderByAuthorAsc(topseller);
    }

    @Override
    public List<Book> findByTopsellerOrderByPublicationdate(Boolean topseller) {
        return bookRepository.findByTopsellerOrderByPublicationdate(topseller);
    }

    @Override
    public List<Book> findByTopsellerOrderByRatingAsc(Boolean topseller) {
        return bookRepository.findByTopsellerOrderByRatingAsc(topseller);
    }

    @Override
    public List<Book> findByTopsellerOrderByRatingDesc(Boolean topseller) {
        return bookRepository.findByTopsellerOrderByRatingDesc(topseller);
    }

    @Override
    public List<Book> findByTopsellerOrderByPriceAsc(Boolean topseller) {
        return bookRepository.findByTopsellerOrderByPriceAsc(topseller);
    }

    public List<Review> getReviewsList(Long id) {

        Book book = bookRepository.findBookById(id);
        List<Review> reviews = book.getReviewsList();
         return reviews;
    }

    public double getAverageRating(Long id) {

        double totalRating = 0.0;
        double numberOfReviews;
        double averageRating;

        Book book = bookRepository.findBookById(id);
        List<Review> reviews = book.getReviewsList();

        numberOfReviews = (double) reviews.size();

        for (Review review : reviews) {
            totalRating += (double) review.getRating();
        }

        if (numberOfReviews == 0.0) {
            averageRating = 0.0;
        } else {
            averageRating = Math.round(totalRating / (numberOfReviews) * 100) / 100;
        }

        return averageRating;
    }

    public int getNumberOfReviews(Long id) {

        Book book = bookRepository.findBookById(id);
        List<Review> reviews = book.getReviewsList();

        return reviews.size();
    }

    public Book findOne(Long id){
        return bookRepository.findById(id).orElse(null);
    }


}
