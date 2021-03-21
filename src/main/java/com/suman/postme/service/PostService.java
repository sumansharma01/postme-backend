package com.suman.postme.service;

import com.suman.postme.dto.PostDto;
import com.suman.postme.entity.Post;
import com.suman.postme.exception.PostException;
import com.suman.postme.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private AuthService authService;

    public void createPost(PostDto postDto) throws PostException {
        Post post=new Post();

        User author=authService.getCurrentUser().orElseThrow(()->new PostException("No user logged in!"));
        post.setAuthorName(author.getUsername());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        post.setCreatedDate(Instant.now());
        post.setUpdatedDate(Instant.now());

        postRepo.save(post);
    }

    public List<PostDto> showAllPosts() {
        List<Post> posts=postRepo.findAll();
        List<PostDto> l=new ArrayList<>();
        for(Post p:posts){
            PostDto postDto=new PostDto();
            postDto=mapFromPostToDto(Optional.ofNullable(p));
            l.add(postDto);
        }
        return l;
    }

    private PostDto mapFromPostToDto(Optional<Post> post) {
        PostDto postDto=new PostDto();
        postDto.setAuthorName(post.get().getAuthorName());
        postDto.setDescription(post.get().getDescription());
        postDto.setTitle(post.get().getTitle());
        return postDto;
    }

    private Post mapFromPostDtoToPost(PostDto postDto){
        Post post=new Post();
        post.setAuthorName(postDto.getAuthorName());
        post.setTitle(postDto.getTitle());
        post.setDescription(post.getDescription());
        post.setUpdatedDate((Instant.now()));
        post.setCreatedDate(Instant.now());

        return post;
    }

    public PostDto getPostById(Long id) throws PostException {
        Optional<Post> post=postRepo.findById(id);
        return mapFromPostToDto(post);

    }
}
