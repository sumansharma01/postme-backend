package com.suman.postme.controller;

import com.suman.postme.dto.PostDto;
import com.suman.postme.exception.PostException;
import com.suman.postme.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postme/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody PostDto postDto) throws PostException {
        postService.createPost(postDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllPosts(){
        return new ResponseEntity<>(postService.showAllPosts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) throws PostException {
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }


}
