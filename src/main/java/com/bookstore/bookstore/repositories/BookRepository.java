package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book findBookById(Long id);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(Genre genre);
}

