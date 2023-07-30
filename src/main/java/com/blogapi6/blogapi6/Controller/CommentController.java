package com.blogapi6.blogapi6.Controller;

import com.blogapi6.blogapi6.Payload.CommentDto;
import com.blogapi6.blogapi6.Service.CommentService;
import javafx.geometry.Pos;
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

    //localhosts://api/posts/1/comments
    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComments(@PathVariable("postId") long postId,@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto>  findPostId(@PathVariable("postId") long postId){
        List<CommentDto> comments = commentService.findByPostId(postId);
        return comments;
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> DeleteCommentByPostId(@PathVariable("postId") long postId,@PathVariable("id") long id){
        CommentDto dto = commentService.deleteCommentById(postId, id);

        return new ResponseEntity<>("Comment is deleted",HttpStatus.OK);

    }
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> GetCommentById(@PathVariable("postId") long postId,@PathVariable("id") long id){
        CommentDto dto = commentService.getCommentById(postId,id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PutMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> UpdateComments(@PathVariable("postId") long postId,@PathVariable("id") long id,@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateComment(postId, id, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


}
