package com.interviewblog1.service;

import com.interviewblog1.payload.PostDto;
import com.interviewblog1.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy, String sortDir);
    PostDto getPostByPostId(long postId);
    PostDto updatePost(PostDto postDto, long postId);
    void deletePost(long postId);
}

