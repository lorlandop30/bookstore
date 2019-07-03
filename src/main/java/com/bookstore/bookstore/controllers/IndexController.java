package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.security.PasswordResetToken;
import com.bookstore.bookstore.security.Role;
import com.bookstore.bookstore.security.UserRole;
import com.bookstore.bookstore.services.UserSecurityService;
import com.bookstore.bookstore.services.UserService;
import com.bookstore.bookstore.utility.MailConstructor;
import com.bookstore.bookstore.utility.SecurityUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;


@Controller
public class IndexController {


    private UserService userService;
    private UserSecurityService userSecurityService;
    private JavaMailSender mailSender;
    private MailConstructor mailConstructor;

    @Autowired
    public IndexController(UserService userService, UserSecurityService userSecurityService,
                           JavaMailSender mailSender,
                           MailConstructor mailConstructor
    ) {
        this.userService = userService;
        this.userSecurityService = userSecurityService;
        this.mailSender=mailSender;
        this.mailConstructor=mailConstructor;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);

        return "MyAccount";
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword(
            HttpServletRequest request,
            @ModelAttribute("email") String email,
            Model model
    ) {

        model.addAttribute("classActiveForgetPassword", true);

        User user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("emailNotExist", true);
            return "MyAccount";
        }

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        userService.save(user);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        SimpleMailMessage newEmail = mailConstructor.constuctResetTokenEmail(appUrl, request.getLocale(), token, user, password);

        mailSender.send(newEmail);

        model.addAttribute("forgetPasswordEmailSent", "true");


        return "MyAccount";
    }

    @PostMapping("/newUser")
    public String newUserPost(
            HttpServletRequest request,
            @ModelAttribute("email") String userEmail,
            @ModelAttribute("Username") String userName,
            Model model) throws Exception {
        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email", userEmail);
        model.addAttribute("username", userName);


        if (userService.findByEmail(userEmail) != null) {
            model.addAttribute("emailExists", true);
            return "MyAccount";
        }

        if (userService.findByUsername(userName) != null) {
            model.addAttribute("usernameExists", true);
            return "MyAccount";
        }



        User user = new User();
        user.setUsername(userName);
        user.setEmail(userEmail);
        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);

        String token = UUID.randomUUID().toString();

        userService.createPasswordRestTokenForUser(user, token);

        String appUrl= "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        SimpleMailMessage email=mailConstructor.constuctResetTokenEmail(appUrl, request.getLocale(), token, user, password );
        mailSender.send(email);

        model.addAttribute("emailSent", true);

        return "MyAccount";
    }

    @RequestMapping("/newUser")
    public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
        PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);

        if (passwordResetToken == null) {
            model.addAttribute("message", "Invalid token");

            return "redirect:/badRequest";
        }

        User user = passwordResetToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("user", user);

        model.addAttribute("classActiveEdit", true);

        return "MyProfile";
    }

}