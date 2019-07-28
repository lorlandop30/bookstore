package com.bookstore.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class SavedCartList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SavedCart> cartList;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public SavedCartList(List<SavedCart> list, User user){
        id = this.id;
    }

}
