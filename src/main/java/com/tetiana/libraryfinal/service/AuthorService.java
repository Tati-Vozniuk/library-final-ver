package com.tetiana.libraryfinal.service;


import com.tetiana.libraryfinal.model.Author;
import com.tetiana.libraryfinal.repository.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void save(Author author) {
        authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }
}
