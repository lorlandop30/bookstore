package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Review;
import com.bookstore.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;


    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public Book findBookByIsbn(int isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }

    @Override
    public List<Book> findByRating(double rating) {
        return bookRepository.findByRating(rating);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
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
    public List<Book> findAllByOrderByPriceAsc() {
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

    @Override
    public List<String> findDistinctLanguageBy(){
        return bookRepository.findDistinctLanguageBy();
    }
    @Override
    public List<String> findDistinctCategoryBy(){
        return bookRepository.findDistinctCategoryBy();
    }
    @Override
    public List<String>findDistinctFormatBy(){
        return bookRepository.findDistinctFormatBy();
    }

    @Override
    public List<String> findDistinctGenreBy() {
        return bookRepository.findDistinctGenreBy();
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

    @Override
    public List<Book> searchTitle(String title) {
        return null;
    }

    @Override
    public List<Book> searchAuthor(String author) {
        return null;
    }

    public Book findOne(Long id){
        return bookRepository.findById(id).orElse(null);
    }


}
