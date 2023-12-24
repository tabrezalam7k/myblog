package com.interviewblog1.service.impl;

import com.interviewblog1.entity.Comment;
import com.interviewblog1.entity.Post;
import com.interviewblog1.exception.BlogAPIException;
import com.interviewblog1.exception.ResourceNotFoundException;
import com.interviewblog1.payload.CommentDto;
import com.interviewblog1.payload.PostResponse;
import com.interviewblog1.repository.CommentRepository;
import com.interviewblog1.repository.PostRepository;
import com.interviewblog1.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper=mapper;
    }



    @Override
    public CommentDto createComment(long postId,CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post posts = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id: " + postId)
        );
        comment.setPost(posts);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(comment);
        return dto;
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id: " + postId)
        );
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentDto> dto = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());


        return dto;
    }

    @Override
    public CommentDto getCommentByCommentId(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id: " + postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found with id: " + commentId)
        );
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException("comment does not belong to this post");
        }
        CommentDto dto = mapToDto(comment);
        return dto;
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id: " + postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found with id: " + commentId)
        );
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException("comment does not belong to this post");
        }
         comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(updatedComment);
        return dto;
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id: " + postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found with id: " + commentId)
        );
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException("comment does not belong to this post");
        }
        commentRepository.delete(comment);
    }

    Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
    CommentDto mapToDto(Comment comment){
        CommentDto dto = mapper.map(comment, CommentDto.class);
//        CommentDto dto = new CommentDto();
//        dto.setId(comment.getId());
//        dto.setName(comment.getName());
//        dto.setEmail(comment.getEmail());
//        dto.setBody(comment.getBody());
        return dto;

    }
}
