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

    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 ORDER BY title", nativeQuery = true)
    List<Book> findByCategoryOrderByTitleAsc(Category category);
    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 ORDER BY author", nativeQuery = true)
    List<Book> findByCategoryOrderByAuthorAsc(Category category);
    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 ORDER BY publication_date", nativeQuery = true)
    List<Book> findByCategoryOrderByPublicationDateAsc(Category category);
    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 ORDER BY rating", nativeQuery = true)
    List<Book> findByCategoryOrderByRatingAsc(Category category);
    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 ORDER BY rating desc", nativeQuery = true)
    List<Book> findByCategoryOrderByRatingDesc(Category category);
    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 ORDER BY our_price", nativeQuery = true)
    List<Book> findByCategoryOrderByOurPriceAsc(Category category);


    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 AND topseller ORDER BY title", nativeQuery = true)
    List<Book> findByCategoryAndTopsellerOrderByTitleAsc(Category category);
    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 AND topseller ORDER BY author", nativeQuery = true)
    List<Book> findByCategoryAndTopsellerOrderByAuthorAsc(Category category);
    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 AND topseller ORDER BY publication_date", nativeQuery = true)
    List<Book> findByCategoryAndTopsellerOrderByPublicationDateAsc(Category category);
    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 AND topseller ORDER BY rating", nativeQuery = true)
    List<Book> findByCategoryAndTopsellerOrderByRatingAsc(Category category);
    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 AND topseller ORDER BY rating desc", nativeQuery = true)
    List<Book> findByCategoryAndTopsellerOrderByRatingDesc(Category category);
    @Query(value = "SELECT b.* FROM book b JOIN genre g ON b.genre_id = g.id JOIN category c ON g.category_id = c.id WHERE c.id = ?1 AND topseller ORDER BY our_price", nativeQuery = true)
    List<Book> findByCategoryAndTopsellerOrderByOurPriceAsc(Category category);
}

