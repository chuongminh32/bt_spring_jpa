package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.entity.Rating;
import vn.iotstar.entity.RatingId;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {
    List<Rating> findByBook_BookId(Integer bookId);
    void deleteByBookBookId(int bookId);
}
