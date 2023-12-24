package com.interviewblog1.controller;

import com.interviewblog1.payload.PostDto;
import com.interviewblog1.payload.PostResponse;
import com.interviewblog1.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    //http://localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return  new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
    @GetMapping
    public PostResponse getAllPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "4", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ){
        PostResponse postResponse = postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
        return postResponse;
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto>getPostByPostId(@PathVariable("postId") long postId){
        PostDto dto = postService.getPostByPostId(postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto, @PathVariable("postId") long postId){
        PostDto dto = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String>deletePost(@PathVariable("postId") long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>("Post is deleted", HttpStatus.OK);
    }
}
