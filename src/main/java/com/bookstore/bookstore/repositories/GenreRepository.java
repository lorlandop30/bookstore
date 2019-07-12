package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.Category;
import com.bookstore.bookstore.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAll();
    List<Genre> findByCategory(Category category);

}
