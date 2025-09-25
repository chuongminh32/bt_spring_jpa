package vn.iotstar.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "bookid")
    private Integer bookId;

    @Column(nullable = false)
    private Integer isbn;   // SQL: int

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 100)
    private String publisher;

    @Column(precision = 6, scale = 2)
    private BigDecimal price;   // SQL: decimal(6,2)

    @Column(columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "publish_date")
    private LocalDate  publishDate;

    @Column(name = "cover_image", length = 100)
    private String coverImage;

    private Integer quantity;

    @ManyToMany
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

//    Thêm quan hệ OneToMany với Rating
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    // ===== Constructor =====
    public Book() {}

    public Book(Integer bookId, Integer isbn, String title, String publisher,
                BigDecimal price, String description, LocalDate  publishDate,
                String coverImage, Integer quantity) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
        this.description = description;
        this.publishDate = publishDate;
        this.coverImage = coverImage;
        this.quantity = quantity;
    }

    // ===== Getter & Setter =====
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getIsbn() {
        return isbn;
    }
    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate  getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(LocalDate  publishDate) {
        this.publishDate = publishDate;
    }

    public String getCoverImage() {
        return coverImage;
    }
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
