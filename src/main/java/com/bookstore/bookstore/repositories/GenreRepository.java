package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository <Genre, Long> {

}
