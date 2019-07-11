package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.Genre;
import com.bookstore.bookstore.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{
    @Autowired
    GenreRepository genreRepository;
    @Override
    public List<Genre> listGenres() {
        return genreRepository.findAll();
    }
}
