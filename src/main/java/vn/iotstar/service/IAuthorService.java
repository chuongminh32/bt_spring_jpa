package vn.iotstar.service;

import vn.iotstar.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAuthorService {
    Page<Author> findAll(Pageable pageable);
    Author findById(int id);
    Author save(Author author);
    void delete(int id);
}
