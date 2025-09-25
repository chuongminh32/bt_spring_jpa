package vn.iotstar.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "userid")
    private int userId;

    @Column(name = "bookid")
    private int bookId;

    public RatingId() {}

    public RatingId(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingId)) return false;
        RatingId that = (RatingId) o;
        return userId == that.userId && bookId == that.bookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookId);
    }
}
