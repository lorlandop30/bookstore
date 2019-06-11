package com.bookstore.bookstore.services;


import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        User user= userRepository.findByUsername(username);

        if(user ==null){
            throw  new UsernameNotFoundException("Username not found");
        }
        return user;
    }
}
