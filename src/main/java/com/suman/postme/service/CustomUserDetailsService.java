package com.suman.postme.service;

import com.suman.postme.entity.Author;
import com.suman.postme.exception.PostException;
import com.suman.postme.repository.AuthorRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthorRepo authorRepo;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String authorName) throws UsernameNotFoundException {
        Optional<Author> author = authorRepo.findByAuthorName(authorName);
        author.orElseThrow(() -> new PostException(authorName + " not found"));
        return new org.springframework.security.core.userdetails.User(author.get().getAuthorName(),
                author.get().getPassword(), true, true,
                true, true,
                getAuthorities("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }
}
