package com.bookstore.bookstore.models;

import javax.persistence.*;

@Entity
public class Book implements Comparable <Book> {
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
    private String genre;
    private String iSBN;
    private String publisher;
    private int yearofpublication;

    public Book(String title, String author, String genre, String iSBN, String publisher, int yearofpublication) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.iSBN = iSBN;
        this.publisher = publisher;
        this.yearofpublication = yearofpublication;
    }

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearofpublication() {
        return yearofpublication;
    }

    public void setYearofpublication(int yearofpublication) {
        this.yearofpublication = yearofpublication;
    }

    public Book() {
    }

    @Override
    public int compareTo(Book o) {
        if (o == null){
            return -1;
        }
        return this.getTitle().compareTo(o.getTitle());
    }
}
