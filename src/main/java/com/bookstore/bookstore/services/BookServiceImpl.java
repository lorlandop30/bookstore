package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Book;
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
            return totalRating/(numberOfReviews);
        }
    }

    public int getNumberOfReviews (Long id) {

        Book book = bookRepository.findBookById(id);
        List<Review> reviews = book.getReviewsList();

        return reviews.size();
    }



}
