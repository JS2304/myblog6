package com.blogapi6.blogapi6.Service;

import com.blogapi6.blogapi6.Payload.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(long PostId,CommentDto commentDto);
    public List<CommentDto> findByPostId(long postId);
    public CommentDto deleteCommentById(long postId,long id);
    public CommentDto getCommentById(long postId,long id);
    public CommentDto updateComment(long postId,long id,CommentDto commentDto);




}
