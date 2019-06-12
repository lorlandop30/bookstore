package com.bookstore.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/rating")


public class RatingController {

    @RequestMapping(method= RequestMethod.GET)

    public String gotoRating(){

        return "rating";
    }
}
