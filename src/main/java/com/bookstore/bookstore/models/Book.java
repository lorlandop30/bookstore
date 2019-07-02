package com.bookstore.bookstore.models;

import javax.persistence.*;

@Entity
public class Book implements Comparable <Book> {
    @Override
    public int compareTo(Book o) {
        return 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long ID;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    private String title;
    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="genre_id")
    private Genre genre;
    private double price;
    private boolean topseller;
    private double rating;
    private int yearofpublication;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isTopseller() {
        return topseller;
    }

    public void setTopseller(boolean topseller) {
        this.topseller = topseller;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getYearofpublication() {
        return yearofpublication;
    }

    public void setYearofpublication(int yearofpublication) {
        this.yearofpublication = yearofpublication;
    }

    public Book(String title, String author, Genre genre, double price, boolean topseller, double rating, int yearofpublication) {

        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.topseller = topseller;
        this.rating = rating;
        this.yearofpublication = yearofpublication;
    }

    public Book() {
    }
}
