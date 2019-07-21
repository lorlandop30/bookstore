package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAllById(long genreId);
    Book findBookById(Long id);

    Book findBookByIsbn(int isbn);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(Genre genre);
}

