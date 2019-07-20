package com.bookstore.bookstore.controllers;

public class BookshelfForm {
    private String sortColumn;
    private Long genreId;
    private Long categoryId;
    private Boolean topseller;
    private Boolean fiveStars;
    private Boolean fourStars;
    private Boolean threeStars;
    private Boolean twoStars;

    public Boolean getFourStars() {
        return fourStars;
    }

    public void setFourStars(Boolean fourStars) {
        this.fourStars = fourStars;
    }

    public Boolean getThreeStars() {
        return threeStars;
    }

    public void setThreeStars(Boolean threeStars) {
        this.threeStars = threeStars;
    }

    public Boolean getTwoStars() {
        return twoStars;
    }

    public void setTwoStars(Boolean twoStars) {
        this.twoStars = twoStars;
    }

    public Boolean getOneStars() {
        return oneStars;
    }

    public void setOneStars(Boolean oneStars) {
        this.oneStars = oneStars;
    }

    private Boolean oneStars;

    public Boolean getFiveStars() {
        return fiveStars;
    }

    public void setFiveStars(Boolean fiveStars) {
        this.fiveStars = fiveStars;
    }

    public Long getGenreId() {
        return genreId;
    }

    public Boolean getTopseller() {
        return topseller;
    }

    public void setTopseller(Boolean topseller) {
        this.topseller = topseller;
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
