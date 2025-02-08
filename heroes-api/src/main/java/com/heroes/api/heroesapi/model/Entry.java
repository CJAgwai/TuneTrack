package com.heroes.api.heroesapi.model;

import java.time.LocalDate;
import java.time.Year;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Entry {
   
    static final String STRING_FORMAT = "Entry [id=%d, title=%s, artist=%s, year=%s, rating=%d, listenDate=%s, review=%s, favoriteSong=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("title") private String title;
    @JsonProperty("artist") private String artist;
    @JsonProperty("year") private Year year;
    @JsonProperty("genre") private String genre;
    @JsonProperty("rating") private int rating;
    @JsonProperty("listenDate") private LocalDate listenDate;
    @JsonProperty("review") private String review;
    @JsonProperty("favoriteSong") private String favoriteSong;
    
    /**
     * Create an entry with the given id, title, artist, year, rating, 
     * listenDate, review, and favoriteSong
     * @param id The id of the entry
     * @param title The title of the album
     * @param artist The artist of the album
     * @param year The year the album was released
     * @param genre The genre of the album
     * @param rating The rating of the album out of 5
     * @param listenDate The date the album was listened to
     * @param review The review of the album
     * @param favoriteSong The favorite song on the album
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */

    public Entry(@JsonProperty("id") int id, @JsonProperty("title") String title,@JsonProperty("artist") String artist, @JsonProperty("year") Year year,@JsonProperty("genre") String genre, @JsonProperty("rating") int rating, @JsonProperty("listenDate")LocalDate listenDate, @JsonProperty("review") String review, @JsonProperty("favoriteSong") String favoriteSong) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.listenDate = listenDate;
        this.review = review;
        this.favoriteSong = favoriteSong;
    }

    
    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public Year getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public LocalDate getListenDate() {
        return listenDate;
    }

    public String getReview() {
        return review;
    }

    public String getFavoriteSong() {
        return favoriteSong;
    }

    // Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setYear(Year year) {
        this.year = year;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setListenDate(LocalDate listenDate) {
        this.listenDate = listenDate;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setFavoriteSong(String favoriteSong) {
        this.favoriteSong = favoriteSong;
    }
    
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,title,artist,year,rating,listenDate,review,favoriteSong);
    }
}