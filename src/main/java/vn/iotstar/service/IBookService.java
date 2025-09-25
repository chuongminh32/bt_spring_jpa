// IBookService.java
package vn.iotstar.service;

import vn.iotstar.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {
    Page<Book> findAll(Pageable pageable);
    Book findById(int id);
    Book save(Book book);
    void delete(int id);
}
