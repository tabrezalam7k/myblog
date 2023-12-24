package com.interviewblog1.controller;

import com.interviewblog1.payload.CommentDto;
import com.interviewblog1.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
     public ResponseEntity<CommentDto>createComment(
             @PathVariable("postId") long postId,
             @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable("postId") long postId){
        List<CommentDto> dto = commentService.getCommentByPostId(postId);
        return dto;

    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto>getCommentByCommentId(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId
    ){
        CommentDto dto = commentService.getCommentByCommentId(postId, commentId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto>updateComment(@PathVariable("postId") long postId,
                                                   @PathVariable("commentId") long commentId,
                                                   @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @DeleteMapping(("/posts/{postId}/comments/{commentId}"))
    public ResponseEntity<String>deleteComment(@PathVariable("postId") long postId,
                                               @PathVariable("commentId") long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment is deleted", HttpStatus.OK);
    }

}
