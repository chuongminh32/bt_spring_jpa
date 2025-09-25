package vn.iotstar.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "author")   // trong DB là "author" (không phải authors)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int authorId;

    @Column(name = "author_name", nullable = false, length = 100)
    private String authorName;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private LocalDate  dateOfBirth;

    @ManyToMany(mappedBy = "authors", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Book> books;

    public Author() {}

    public Author(int authorId, String authorName, LocalDate  dateOfBirth) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.dateOfBirth = dateOfBirth;
    }

    // Getter & Setter
    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}
