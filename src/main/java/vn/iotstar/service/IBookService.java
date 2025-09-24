package vn.iotstar.service;

import vn.iotstar.entity.Book;
import java.util.List;

public interface IBookService {
    List<Book> findAll();
}
