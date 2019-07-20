package com.bookstore.bookstore.controllers;


import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Review;
import com.bookstore.bookstore.models.User;
//import com.bookstore.service.UserService;

import com.bookstore.bookstore.services.BookService;
import com.bookstore.bookstore.services.ReviewService;
import com.bookstore.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller



public class RatingController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/rating")
    public String gotoReview(
            @PathParam("id") Long id, Model model, Principal principal) {

        if(principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        Book book = bookService.findBookById(id);

        model.addAttribute("book", book);

        return "rating";
    }

    @RequestMapping(value = "/addReview", method = RequestMethod.POST)
    public String addReview(Model model, Principal principal, @ModelAttribute("review") Review review,
                            @RequestParam(value = "showName", required = false) String showname,
                            @RequestParam(value = "score", required = false) double score,
                            @RequestParam(value = "bookId", required = false) Long id){

        User user = userService.findByUsername(principal.getName());

        Book book = bookService.findBookById(id);

        model.addAttribute("user", user);
        model.addAttribute("book", book);
        review.setUsername(showname);
        review.setRating(score);

        reviewService.addReview(review, user, book);

        return "index";
    }



}
