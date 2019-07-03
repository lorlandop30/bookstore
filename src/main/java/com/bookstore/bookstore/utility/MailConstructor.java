package com.bookstore.bookstore.utility;

import com.bookstore.bookstore.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MailConstructor {

    private Environment env;

    @Autowired
    public MailConstructor( Environment env) {

        this.env=env;
    }

    public SimpleMailMessage constuctResetTokenEmail(
            String contextPath,
            Locale locale,
            String token,
            User user,
            String password
    ){
        String url=contextPath+ "/newUser?token="+token;
        String message=" \n Please click to verify your information. Your password is: \n" +password;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject(" Bookstore - New User");
        email.setText(url+ message);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
