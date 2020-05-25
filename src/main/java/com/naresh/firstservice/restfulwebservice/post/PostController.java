package com.naresh.firstservice.restfulwebservice.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostDaoService service;

    @GetMapping(path = "/user/{userId}/posts")
    public List<Post> getUserPosts(@PathVariable int userId){
        return service.getUserPosts(userId);
    }

    @GetMapping(path = "/user/{userId}/posts/{id}")
    public Post getPost(@PathVariable int userId, @PathVariable int id){
        Post post = service.getUserPost(userId, id);
        if(post == null)
            throw new PostNotFoundException("userId: " + userId + " postId: " + id + " NOT FOUND");
        return post;
    }

    @PostMapping(path = "/user/{userId}/posts")
    public ResponseEntity<Object> createUserPost(@RequestBody Post post){
        Post newPost = service.createUserPost(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/user/{userId}/posts/{id}")
    public void deleteUserPost(@PathVariable int id, @PathVariable int userId){
        Post post = service.deleteUserPost(id, userId);
        if(post == null)
            throw new PostNotFoundException("userId: " + userId + " postId: " + id + " NOT FOUND");
    }
}
