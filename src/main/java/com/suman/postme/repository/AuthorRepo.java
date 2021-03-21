package com.suman.postme.repository;

import com.suman.postme.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Long> {
    Optional<Author> findByAuthorName(String authorName);
}
