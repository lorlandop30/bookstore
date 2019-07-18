package com.bookstore.bookstore.repositories;
import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Book findBookById(Long id);

    Book findBookByIsbn(int isbn);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(Genre genre);
    List<Book> findAllByOrderByTitleAsc();
    List<Book> findAllByOrderByAuthorAsc();
    List<Book> findAllByOrderByPublicationDateAsc();
    List<Book> findAllByOrderByRatingAsc();
    List<Book> findAllByOrderByRatingDesc();
    List<Book> findAllByOrderByOurPriceAsc();

    List<Book> findByGenreOrderByTitleAsc(Genre genre);
    List<Book> findByGenreOrderByAuthorAsc(Genre genre);
    List<Book> findByGenreOrderByPublicationDateAsc(Genre genre);
    List<Book> findByGenreOrderByRatingAsc(Genre genre);
    List<Book> findByGenreOrderByRatingDesc(Genre genre);
    List<Book> findByGenreOrderByOurPriceAsc(Genre genre);

    List<Book> findByTopsellerOrderByTitleAsc(Boolean topseller);
    List<Book> findByTopsellerOrderByAuthorAsc(Boolean topseller);
    List<Book> findByTopsellerOrderByPublicationDateAsc(Boolean topseller);
    List<Book> findByTopsellerOrderByRatingAsc(Boolean topseller);
    List<Book> findByTopsellerOrderByRatingDesc(Boolean topseller);
    List<Book> findByTopsellerOrderByOurPriceAsc(Boolean topseller);

    List<Book> findByGenreAndTopsellerOrderByTitleAsc(Genre genre, Boolean topseller);
    List<Book> findByGenreAndTopsellerOrderByAuthorAsc(Genre genre, Boolean topseller);
    List<Book> findByGenreAndTopsellerOrderByPublicationDateAsc(Genre genre, Boolean topseller);
    List<Book> findByGenreAndTopsellerOrderByRatingAsc(Genre genre, Boolean topseller);
    List<Book> findByGenreAndTopsellerOrderByRatingDesc(Genre genre, Boolean topseller);
    List<Book> findByGenreAndTopsellerOrderByOurPriceAsc(Genre genre, Boolean topseller);


}

