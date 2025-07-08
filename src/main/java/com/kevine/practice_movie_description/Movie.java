package com.kevine.practice_movie_description;

// plain old Java object that holds movie data
public class Movie {
    // movie title
    private String title;
    // movie rating like PG 13
    private String rating;
    // AI generated description
    private String description;
    // AI generated review
    private String review;

    // no args constructor for frameworks like Spring or Jackson
    public Movie() {}

    // all args constructor to create a complete movie
    public Movie(String title, String rating, String description, String review) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.review = review;
    }

    // getter and setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // getter and setter for rating
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // getter and setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // getter and setter for review
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
