package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Category;
import com.bookstore.bookstore.models.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Book findBookById(Long id);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(Genre genre);
   // @Query("select b from book join fetch b.genre g")
    //List<Book> findByCategory(Category category);

}

