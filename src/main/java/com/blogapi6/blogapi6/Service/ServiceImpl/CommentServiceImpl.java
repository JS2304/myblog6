package com.blogapi6.blogapi6.Service.ServiceImpl;

import com.blogapi6.blogapi6.Entity.Comment;
import com.blogapi6.blogapi6.Entity.Post;
import com.blogapi6.blogapi6.Exception.ResourceNotFound;
import com.blogapi6.blogapi6.Payload.CommentDto;
import com.blogapi6.blogapi6.Repository.CommentRepository;
import com.blogapi6.blogapi6.Repository.PostRepository;
import com.blogapi6.blogapi6.Service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepo;
    private CommentRepository commentRepo;

    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDto createComment(long PostId, CommentDto commentDto) {
        Post post = postRepo.findById(PostId).orElseThrow(
                () -> new ResourceNotFound(PostId)
        );
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);

        CommentDto dto = new CommentDto();
        dto.setId(savedComment.getId());
        dto.setName(savedComment.getName());
        dto.setEmail(savedComment.getEmail());
        dto.setBody(savedComment.getBody());

        return dto;
    }

    @Override
    public List<CommentDto> findByPostId(long postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound(postId)
        );
        List<Comment> comments = commentRepo.findByPostId(postId);
        List<CommentDto> collect = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CommentDto deleteCommentById(long postId,long id) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound(postId)
        );
        commentRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound(id)
        );
        commentRepo.deleteById(id);
        return null;
    }

    @Override
    public CommentDto getCommentById(long postId,long id) {

        postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFound(postId)
        );

        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound(id)
        );

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long id, CommentDto commentDto) {


        Post post = postRepo.findById(postId).orElseThrow(
            () -> new ResourceNotFound(postId)
        );
        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound(id)
        );

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        //comment.setId(id);
        //comment.setPost(post);


        Comment savedComment = commentRepo.save(comment);

        return mapToDto(savedComment);

    }


    public CommentDto mapToDto(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        return dto;
    }
}
