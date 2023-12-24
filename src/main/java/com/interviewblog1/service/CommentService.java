package com.interviewblog1.service;

import com.interviewblog1.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);
    List<CommentDto> getCommentByPostId(long postId);
    CommentDto getCommentByCommentId(long postId,long commentId);
    CommentDto updateComment(long postId, long commentId,CommentDto commentDto);
     void deleteComment(long postId, long commentId);
}
