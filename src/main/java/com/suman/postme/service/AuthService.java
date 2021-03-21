package com.suman.postme.service;

import com.suman.postme.dto.RegisterRequest;
import com.suman.postme.entity.Author;
import com.suman.postme.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegisterRequest registerRequest) {
        Author author=new Author();
        author.setAuthorName(registerRequest.getAuthorName());
        author.setEmail(registerRequest.getEmail());
        author.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        authorRepo.save(author);
    }
}
