package com.bookstore.bookstore.controllers;

public class BookshelfForm {
    private String sortColumn;
    private Long genreId;
    private Long categoryId;

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

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
