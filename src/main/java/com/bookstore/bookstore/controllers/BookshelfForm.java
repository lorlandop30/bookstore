package com.bookstore.bookstore.controllers;

public class BookshelfForm {
    private String sortColumn;

    public BookshelfForm() {
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public BookshelfForm(String sortColumn) {
        this.sortColumn = sortColumn;
    }
}
