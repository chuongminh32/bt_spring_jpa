package vn.iotstar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {

    @EmbeddedId
    private RatingId id;

    @ManyToOne
    @MapsId("bookId")  // ánh xạ bookId trong RatingId
    @JoinColumn(name = "bookid")
    private Book book;

    @ManyToOne
    @MapsId("userId")  // ánh xạ userId trong RatingId
    @JoinColumn(name = "userid")
    private User user;

    @Column(name = "rating")
    private Integer rating; // ví dụ 1-5

    @Column(name = "review_text", columnDefinition = "TEXT")
    private String reviewText;

    public Rating() {}

    public Rating(User user, Book book, Integer rating, String reviewText) {
        this.id = new RatingId(user.getId(), book.getBookId());
        this.user = user;
        this.book = book;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    // Getter & Setter
    public RatingId getId() { return id; }
    public void setId(RatingId id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }
}
