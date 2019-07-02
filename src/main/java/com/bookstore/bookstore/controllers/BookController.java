package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Genre;
import com.bookstore.bookstore.services.BookService;
import com.bookstore.bookstore.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @RequestMapping("/showGenres")
    public String showGenres(Model model){
        List<Genre> genres = genreService.listGenres();
        System.out.println(genres);
        return null;
    }

    @RequestMapping("/searchTitle")
    public List<Book> searchTitle(@RequestParam("title") String title){
    return null;
    }
}
