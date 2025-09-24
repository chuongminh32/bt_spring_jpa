package vn.iotstar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
//	    Optional<Book> findByEmail(String email);
//	    Optional<Book> findByPhone(int string);
//	    Optional<Book> findByUsername(String username);
}
