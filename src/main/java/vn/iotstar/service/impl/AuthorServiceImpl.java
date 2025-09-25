package vn.iotstar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.iotstar.entity.Author;
import vn.iotstar.repository.AuthorRepository;
import vn.iotstar.service.IAuthorService;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {

    private final AuthorRepository repo;

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Author findById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Author save(Author author) {
        return repo.save(author);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}
