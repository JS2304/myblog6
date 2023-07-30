package com.blogapi6.blogapi6.Controller;

import com.blogapi6.blogapi6.Payload.PostDto;
import com.blogapi6.blogapi6.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    public PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto savedDto = postService.createpost(postDto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable("id") long id){
        PostDto dto = postService.FindById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/posts
    @GetMapping
    public List<PostDto> FindAll(
            @RequestParam(value="PageNo",defaultValue = "0",required = false) int PageNo,
            @RequestParam(value="PageSize",defaultValue = "4",required = false) int PageSize,
            @RequestParam(value="SortBy",defaultValue = "id",required = false) String Sortby,
            @RequestParam(value = "SortDir",defaultValue = "asc",required = false) String SortDir


    ){
        List<PostDto> postDtos = postService.FindAllPosts(PageNo,PageSize,Sortby,SortDir);
        return postDtos;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> Delete(@PathVariable("id") long id){
        postService.DeleteById(id);
        return new ResponseEntity<>("Post is deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> UpdatePost(@PathVariable("id") long id,@RequestBody PostDto postDto){
        PostDto updatedDto = postService.UpdatePost(id, postDto);
        return new ResponseEntity<>(updatedDto,HttpStatus.OK);
    }
}
