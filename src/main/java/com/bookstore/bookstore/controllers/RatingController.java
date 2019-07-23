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
    public String addReview(Model model, Principal principal,
                            @RequestParam(value = "sortColumn", required = false) String sort,
                            @RequestParam(value = "topseller", required = false) Boolean topseller,
                            @ModelAttribute("review") Review review,
                            @RequestParam(value = "showName", required = false) String showname,
                            @RequestParam(value = "score", required = false) double score,
                            @RequestParam(value = "bookId", required = false) Long id) {

        User user = userService.findByUsername(principal.getName());

        Book book = bookService.findBookById(id);

        review.setUsername(showname);
        review.setRating(score);

        reviewService.addReview(review, user, book);

        BookshelfForm bf = new BookshelfForm(sort);
        bf.setTopseller(topseller);
        model.addAttribute("formobject", bf);
        List<String> sortColumns = Arrays.asList(new String[]{"title", "author", "date", "rating asc", "rating desc", "price"});
        model.addAttribute("sortColumns", sortColumns);
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("user", user);
        }

        List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        model.addAttribute("averageRating", bookService.getAverageRating(id));

        model.addAttribute("bookReviewsList", bookService.getReviewsList(id));

        model.addAttribute("numberOfReviews", bookService.getNumberOfReviews(id));

        if (bookService.getNumberOfReviews(id) == 0) {
            model.addAttribute("NoReviews", true);
        } else {
            model.addAttribute("Reviews", true);
        }

        model.addAttribute("book", book);

        return "bookDetail";

    }



}
