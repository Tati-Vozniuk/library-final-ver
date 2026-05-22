package com.tetiana.libraryfinal.repository;

import com.tetiana.libraryfinal.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}
