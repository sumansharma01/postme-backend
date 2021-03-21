package com.suman.postme.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "author_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    private Long id;

    @Column(name = "author_name", nullable = false, unique = true)
    private String authorName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="email",nullable = false,unique = true)
    private String email;
}
