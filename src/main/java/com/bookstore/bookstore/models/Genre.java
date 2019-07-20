package com.bookstore.bookstore.models;

import javax.persistence.*;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long ID;
    private String name;

    public Genre(Long ID, String name) {
        this.name = name;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }

    public Genre() {

    }

    public Long getID(){
        return ID;
    }

    public void setID(Long ID){
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;
}
