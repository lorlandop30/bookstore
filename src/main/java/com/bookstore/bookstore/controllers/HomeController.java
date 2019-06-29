package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.security.PasswordResetToken;
import com.bookstore.bookstore.services.BookService;
import com.bookstore.bookstore.services.UserSecurityService;
import com.bookstore.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/account")
    public String account(){
        return "account";
    }

    @RequestMapping("/bookshelf")
    public String bookshelf(Model model){
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);

        return "bookshelf";
    }
    @RequestMapping("/login")
        public String login(Model model){
        model.addAttribute("classActiveLogin", true);
        return "account";
    }

    @RequestMapping("/forgetPassword")
        public String forgetPassword(
                Locale locale,
                @RequestParam("Token") String token,
                Model model)
            {
        model.addAttribute("classActiveForgetPassword", true);
        return "forgetPassword";
    }

    @RequestMapping("/shoppingCart")
    public String shoppingCart(){
        return "shoppingCart";
    }

    /*
    @RequestMapping("newUser")
        public String newUser(Model model){
                PasswordResetToken passToken = userService.getPasswordResetToken(token);
        model.addAttribute("classActiveNewUser", true);
        return "newUser";
    }
*/

}

