package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iotstar.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Tìm sách theo ISBN (Unique)
    Optional<Book> findByIsbn(int isbn);

    // Tìm sách theo tiêu đề (chứa từ khóa)
    List<Book> findByTitleContainingIgnoreCase(String keyword);

    // Tìm sách theo publisher
    List<Book> findByPublisherContainingIgnoreCase(String publisher);

    // Tìm theo tác giả (nếu Book có quan hệ @ManyToMany Author)
    List<Book> findByAuthors_AuthorNameContainingIgnoreCase(String authorName);
}
