package com.suman.postme.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "post_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long id;

    @Column(name="post_title", nullable = false)
    private String title;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name="updated_date")
    private Instant updatedDate;

    @Column(name = "author_name")
    private String authorName;

}
