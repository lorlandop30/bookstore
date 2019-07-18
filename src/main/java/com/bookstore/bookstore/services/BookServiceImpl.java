package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Genre;
import com.bookstore.bookstore.models.Review;
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
    public List<Book> findAllByOrderByRatingDesc() {
        return bookRepository.findAllByOrderByRatingDesc();
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
    public List<Book> findByGenreOrderByRatingDesc(Genre genre) {
        return bookRepository.findByGenreOrderByRatingDesc(genre);
    }

    @Override
    public List<Book> findByGenreOrderByOurPriceAsc(Genre genre) {
        return bookRepository.findByGenreOrderByOurPriceAsc(genre);
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
    public List<Book> findByTopsellerOrderByPublicationDateAsc(Boolean topseller) {
        return bookRepository.findByTopsellerOrderByPublicationDateAsc(topseller);
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
    public List<Book> findByTopsellerOrderByOurPriceAsc(Boolean topseller) {
        return bookRepository.findByTopsellerOrderByOurPriceAsc(topseller);
    }

    @Override
    public List<Book> findByGenreAndTopsellerOrderByTitleAsc(Genre genre, Boolean topseller) {
        return bookRepository.findByGenreAndTopsellerOrderByTitleAsc(genre, topseller);
    }

    @Override
    public List<Book> findByGenreAndTopsellerOrderByAuthorAsc(Genre genre, Boolean topseller) {
        return bookRepository.findByGenreAndTopsellerOrderByAuthorAsc(genre, topseller);
    }

    @Override
    public List<Book> findByGenreAndTopsellerOrderByPublicationDateAsc(Genre genre, Boolean topseller) {
        return bookRepository.findByGenreAndTopsellerOrderByPublicationDateAsc(genre, topseller);
    }

    @Override
    public List<Book> findByGenreAndTopsellerOrderByRatingAsc(Genre genre, Boolean topseller) {
        return bookRepository.findByGenreAndTopsellerOrderByRatingAsc(genre, topseller);
    }

    @Override
    public List<Book> findByGenreAndTopsellerOrderByRatingDesc(Genre genre, Boolean topseller) {
        return bookRepository.findByGenreAndTopsellerOrderByRatingDesc(genre, topseller);
    }

    @Override
    public List<Book> findByGenreAndTopsellerOrderByOurPriceAsc(Genre genre, Boolean topseller) {
        return bookRepository.findByGenreAndTopsellerOrderByOurPriceAsc(genre, topseller);
    }

    public double getAverageRating (Long id) {

        double totalRating = 0.0;
        double numberOfReviews;

        Book book = bookRepository.findBookById(id);
        List<Review> reviews = book.getReviewsList();

        numberOfReviews = (double)reviews.size();

        for( Review review: reviews ){
            totalRating += (double)review.getRating();
        }

        if(numberOfReviews==0.0){
            return 0.0;
        } else{
            return Math.round(totalRating/(numberOfReviews)*100)/100;
        }
    }

    public int getNumberOfReviews (Long id) {

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


}
