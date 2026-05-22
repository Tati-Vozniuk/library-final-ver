package com.tetiana.libraryfinal.service;

import com.tetiana.libraryfinal.model.Book;
import com.tetiana.libraryfinal.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepository;

    public List<Book> search(String query, String filterType) {
        if (query == null || query.isBlank()) return findAll();
        if ("author".equals(filterType)) {
            return bookRepository.findByAuthor_NameContainingIgnoreCase(query);
        }
        return bookRepository.findByTitleContainingIgnoreCase(query);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}