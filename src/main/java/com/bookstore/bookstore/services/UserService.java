package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.security.PasswordResetToken;
import org.springframework.beans.factory.annotation.Autowired;


public interface UserService {

    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordRestTokenForUser(final User user, final String token);
}
