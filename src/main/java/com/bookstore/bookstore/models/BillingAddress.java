package com.bookstore.bookstore.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserBilling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
