package com.bookstore.bookstore.models;

import com.bookstore.bookstore.models.User;

import javax.persistence.*;

@Entity

public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int bookisbn; //foreign key from Book table
    private boolean shownickname;
    private double rating; //five stars rating (1-5)
    private String comment;
    private String username;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="book_isbn")
    private Book book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBookisbn() {
        return bookisbn;
    }

    public void setBookisbn(int bookisbn) {
        this.bookisbn = bookisbn;
    }

    public boolean isShownickname() {
        return shownickname;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean getShownickname() {
        return shownickname;
    }

    public void setShownickname(boolean show_nickname) {
        this.shownickname = show_nickname;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user_name) {
        this.username = user_name;
    }

}
