package com.bookstore.bookstore.services;


import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.repositories.PasswordResetTokenRepository;
import com.bookstore.bookstore.security.PasswordResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{



    private  PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    public UserServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordRestTokenForUser(User user, String token) {

        final PasswordResetToken myToken=new PasswordResetToken(token, user);

        passwordResetTokenRepository.save(myToken);
    }
}
