package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Category;
import com.bookstore.bookstore.models.Genre;
import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.repositories.BookRepository;
import com.bookstore.bookstore.repositories.CategoryRepository;
import com.bookstore.bookstore.repositories.GenreRepository;
import com.bookstore.bookstore.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/book")
public class BookController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "addBook";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
        bookRepository.save(book);

        MultipartFile bookImage = book.getBookImage();

        try {
            byte[] bytes = bookImage.getBytes();
            String name = book.getId() + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File("src/main/resources/static/img/books/" + name)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "addBook";
    }

    @RequestMapping("/genreBrowser")
    public String showGenres(Model model) {
        List<Genre> genres = genreService.listGenres();
        System.out.println(genres);
        model.addAttribute("genres", genres);
        return "genreBrowser";
    }

    @RequestMapping("/categoryBrowser")
    public String showCategories(Model model) {
        List<Category> categories = categoryService.listCategories();
        System.out.println(categories);
        model.addAttribute("categories", categories);
        return "categoryBrowser";
    }

    @RequestMapping("/searchTitle")
    public List<Book> searchTitle(@RequestParam("title") String title) {
        return null;
    }

    @RequestMapping("/bookshelf")
    public String listByGenre(@RequestParam(value = "genreId", required = false) Long genreId,
                              @RequestParam(value = "categoryId", required = false) Long categoryId,
                              @RequestParam(value = "sortColumn", required = false) String sort, Model model, Principal principal) {


        Collection<Book> bookList = null;

        if (categoryId == null || categoryId <= 0) {
            Genre genre = new Genre();
            genre.setID(genreId);
            if (sort == null || "".equals(sort)) {
                sort = "title";
                bookList = bookService.findByGenreOrderByTitleAsc(genre);
            } else if ("title".equalsIgnoreCase(sort)) {
                bookList = bookService.findByGenreOrderByTitleAsc(genre);
            } else if ("author".equalsIgnoreCase(sort)) {
                bookList = bookService.findByGenreOrderByAuthorAsc(genre);
            } else if ("date".equalsIgnoreCase(sort)) {
                bookList = bookService.findByGenreOrderByPublicationDateAsc(genre);
            } else if ("rating".equalsIgnoreCase(sort)) {
                bookList = bookService.findByGenreOrderByRatingAsc(genre);
            } else if ("price".equalsIgnoreCase(sort)) {
                bookList = bookService.findByGenreOrderByOurPriceAsc(genre);
            } else {
                model.addAttribute("emptyList", Boolean.TRUE);
            }
        }
        else {
            Category category = new Category();
            category.setId(categoryId);
            List<Genre> genres = genreRepository.findByCategory(category);
            bookList = new HashSet<>();
            for (Genre genre2: genres) {
                List<Book> bks = bookRepository.findByGenre(genre2);
                bookList.addAll(bks);
            }
        }
        model.addAttribute("bookList", bookList);
        BookshelfForm bf = new BookshelfForm(sort);
        bf.setGenreId(genreId);
        bf.setCategoryId(categoryId);

        model.addAttribute("formobject", bf);
        List<String> sortColumns = Arrays.asList(new String[] {"title", "author", "date", "rating", "price"});
        model.addAttribute("sortColumns", sortColumns);
        if(principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);

        }


        return "bookshelf";
    }

}
//
//    private BookRepository bookRepository;
//
//    @Autowired
//    BookController(BookRepository bookRepository){
//        this.bookRepository=bookRepository;
//    }
//
//
//    // controller to import books from postman
//
//    @PostMapping("/importBooks")
//    public void savingBooks(@RequestParam(value = "title") String title,
//                              @RequestParam(value = "author") String author,
//                              @RequestParam(value = "publisher") String publisher,
//                              @RequestParam(value = "publicationDate") String publicationDate,
//                              @RequestParam(value = "language") String language,
//                              @RequestParam(value = "category") String category,
//                              @RequestParam(value = "numberOfPages") int numberOfPages,
//                              @RequestParam(value = "format") String format,
//                              @RequestParam(value = "isbn") int isbn,
//                              @RequestParam(value = "shippingWeight") double shippingWeight,
//                              @RequestParam(value = "listPrice") double listPrice,
//                              @RequestParam(value = "ourPrice") double ourPrice,
//                              @RequestParam(value = "description") String description,
//                              @RequestParam(value = "inStockNumber") int inStockNumber
//                              ){
//
//        Book book=new Book(title,author,publisher,publicationDate,language,category,
//                numberOfPages,format,isbn,shippingWeight,listPrice,ourPrice,
//                description,inStockNumber);
//
//        bookRepository.save(book);
//
//    }


