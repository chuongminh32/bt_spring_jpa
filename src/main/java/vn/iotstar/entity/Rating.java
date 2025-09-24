package vn.iotstar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // cần có khóa chính, nếu bảng rating chưa có thì nên thêm cột id (identity)

    @Column(name = "userid", nullable = false)
    private int userId;

    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;

    @Column(name = "rating")
    private Integer rating; // ví dụ 1-5

    @Column(name = "review_text", columnDefinition = "TEXT")
    private String reviewText;

    public Rating() {}

    public Rating(int userId, Book book, Integer rating, String reviewText) {
        this.userId = userId;
        this.book = book;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }
}
