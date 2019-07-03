package com.bookstore.bookstore.services;


import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.repositories.PasswordResetTokenRepository;
import com.bookstore.bookstore.repositories.RoleRepository;
import com.bookstore.bookstore.repositories.UserRepository;
import com.bookstore.bookstore.security.PasswordResetToken;
import com.bookstore.bookstore.security.UserRole;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG= LoggerFactory.getLogger(UserService.class);

    private  PasswordResetTokenRepository passwordResetTokenRepository;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository, UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
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

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception{

        User localUser = userRepository.findByUsername(user.getUsername());

        if(localUser !=null){

            LOG.info("User {} already exists.", user.getUsername());
        }
        else{
            for (UserRole ur : userRoles){
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            localUser=userRepository.save(user);
        }
        return localUser;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }
}
