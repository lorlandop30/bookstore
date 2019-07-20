package com.bookstore.bookstore.services;


import com.bookstore.bookstore.models.Review;
import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService{

    private ReviewRepository reviewRepository;
    private BookService bookService;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getBookReviews (Long isbn){

        List<Review> reviews = reviewRepository.findByBookisbn(isbn);

        return reviews;
    }

    public void addReview (Review review, User user, Book book){

        /*Save username if it may be shown*/
        if(review.getUsername().compareTo("true")==0){
            review.setUsername(user.getUsername());
            review.setShownickname(true);
        }
        else{
            review.setUsername("Anonymous");
            review.setShownickname(false);
        }

        book.setRating(((book.getRating()*(book.getReviewsList().size()))+review.getRating())/((book.getReviewsList().size())+1));

        review.setBookisbn(book.getIsbn());
        review.setUser(user);
        review.setBook(book);


        reviewRepository.save(review);

   }


}
