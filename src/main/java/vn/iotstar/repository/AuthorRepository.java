// AuthorRepository.java
package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
