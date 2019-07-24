package com.bookstore.bookstore.repositories;
import com.bookstore.bookstore.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAllById(long genreId);
    Book findBookById(Long id);
    Book findBookByIsbn(int isbn);
    List<Book> findByRating(double rating);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findAllByOrderByTitleAsc();
    List<Book> findAllByOrderByAuthorAsc();
    List<Book> findAllByOrderByPublicationdate();
    List<Book> findAllByOrderByRatingAsc();
    List<Book> findAllByOrderByRatingDesc();
    List<Book> findAllByOrderByPriceAsc();
    List<Book> findByTopsellerOrderByTitleAsc(Boolean topseller);
    List<Book> findByTopsellerOrderByAuthorAsc(Boolean topseller);
    List<Book> findByTopsellerOrderByPublicationdate(Boolean topseller);
    List<Book> findByTopsellerOrderByRatingAsc(Boolean topseller);
    List<Book> findByTopsellerOrderByRatingDesc(Boolean topseller);
    List<Book> findByTopsellerOrderByPriceAsc(Boolean topseller);

    @Query(value="SELECT DISTINCT b.language FROM book b ORDER BY b.language", nativeQuery = true)
    List<String> findDistinctLanguageBy();

    @Query(value="SELECT DISTINCT b.category FROM book b ORDER BY b.category", nativeQuery = true)
    List<String> findDistinctCategoryBy();
    @Query(value="SELECT DISTINCT b.genre FROM book b ORDER BY b.genre", nativeQuery = true)
    List<String> findDistinctGenreBy();

    @Query(value="SELECT DISTINCT b.format FROM book b ORDER BY b.format", nativeQuery = true)
    List<String> findDistinctFormatBy();


}

