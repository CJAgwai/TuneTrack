import java.time.LocalDate;
import java.time.Year;

public class Entry {
   
    private String title;
    private String artist;
    private Year year;
    private int rating;
    private LocalDate listenDate;
    private String review;
    private String favoriteSong;

    public Entry( String title, String artist, Year year, int rating, LocalDate listenDate, String review,String favoriteSong) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.rating = rating;
        this.listenDate = listenDate;
        this.review = review;
        this.favoriteSong = favoriteSong;
    }

    // Getters

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public Year getYear() {
        return year;
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
    
    public static void main(String[] args) {
        Entry entry = new Entry("The Dark Side of the Moon", "Pink Floyd", Year.of(1973), 5, LocalDate.of(2021, 1, 1), "This album is a masterpiece.", "Time");
        System.out.println(entry.getTitle());
        System.out.println(entry.getArtist());
        System.out.println(entry.getRating());
        System.out.println(entry.getReview());
        System.out.println(Year.of(1900).isBefore(Year.of(2000)));
        System.out.println(LocalDate.now().toString());
    }
}