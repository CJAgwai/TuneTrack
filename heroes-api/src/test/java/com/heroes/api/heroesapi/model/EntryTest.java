package com.heroes.api.heroesapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Year;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Entry class
 * 
 * @author SWEN Faculty
 */
@Tag("Model-tier")
public class EntryTest {
    @Test
    public void testIdConstructor() {
        // Setup
        int expected_id = 99;

        // Invoke
        Entry entry = new Entry(99);

        // Analyze
        assertEquals(expected_id, entry.getId());
    }

    @Test
    public void testConstructor() {
        // Setup
        int expected_id = 31;
        String expected_title = "Alligator Bites Never Heal";
        String expected_artist = "Doechii";
        Year expected_year = Year.of(2025);
        String expected_genre = "Rap";
        int expected_rating = 4;
        LocalDate expected_listenDate = LocalDate.of(2025, 2, 15);
        String expected_review = "Great Album";
        String expected_favoriteSong = "Nissan Altima";

        // Invoke
        Entry entry = new Entry(31, "Alligator Bites Never Heal", "Doechii", Year.of(2025), "Rap", 4, LocalDate.of(2025, 2, 15), "Great Album", "Nissan Altima");

        // Analyze
        assertEquals(expected_id, entry.getId());
        assertEquals(expected_title, entry.getTitle());
        assertEquals(expected_artist, entry.getArtist());
        assertEquals(expected_year, entry.getYear());
        assertEquals(expected_genre, entry.getGenre());
        assertEquals(expected_rating, entry.getRating());
        assertEquals(expected_listenDate, entry.getListenDate());
        assertEquals(expected_review, entry.getReview());
        assertEquals(expected_favoriteSong, entry.getFavoriteSong());
    }

    @Test
    public void testSetters() {
        // Setup
        Entry entry = new Entry(1);
        String expected_title = "New Title";
        String expected_artist = "New Artist";
        Year expected_year = Year.of(2022);
        String expected_genre = "New Genre";
        int expected_rating = 5;
        LocalDate expected_listenDate = LocalDate.of(2022, 1, 1);
        String expected_review = "New Review";
        String expected_favoriteSong = "New Favorite Song";

        // Invoke
        entry.setTitle(expected_title);
        entry.setArtist(expected_artist);
        entry.setYear(expected_year);
        entry.setGenre(expected_genre);
        entry.setRating(expected_rating);
        entry.setListenDate(expected_listenDate);
        entry.setReview(expected_review);
        entry.setFavoriteSong(expected_favoriteSong);

        // Analyze
        assertEquals(expected_title, entry.getTitle());
        assertEquals(expected_artist, entry.getArtist());
        assertEquals(expected_year, entry.getYear());
        assertEquals(expected_genre, entry.getGenre());
        assertEquals(expected_rating, entry.getRating());
        assertEquals(expected_listenDate, entry.getListenDate());
        assertEquals(expected_review, entry.getReview());
        assertEquals(expected_favoriteSong, entry.getFavoriteSong());
    }

    @Test
    public void testToString() {
        // Setup
        int id = 31;
        String title = "Alligator Bites Never Heal";
        String artist = "Doechii";
        Year year = Year.of(2025);
        String genre = "Rap";
        int rating = 4;
        LocalDate listenDate = LocalDate.of(2025, 2, 15);
        String review = "Great Album";
        String favoriteSong = "Nissan Altima";

        Entry entry = new Entry(id, title, artist, year, genre, rating, listenDate, review, favoriteSong);
        String expectedToString = "Entry [id=31, title=Alligator Bites Never Heal, artist=Doechii, year=2025, rating=4, listenDate=2025-02-15, review=Great Album, favoriteSong=Nissan Altima]";

        // Invoke
        String actualToString = entry.toString();

        // Analyze
        assertEquals(expectedToString, actualToString);
    }
}
