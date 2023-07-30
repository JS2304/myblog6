package com.blogapi6.blogapi6.Service.ServiceImpl;

import com.blogapi6.blogapi6.Entity.Post;
import com.blogapi6.blogapi6.Exception.ResourceNotFound;
import com.blogapi6.blogapi6.Payload.PostDto;
import com.blogapi6.blogapi6.Repository.PostRepository;
import com.blogapi6.blogapi6.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    public PostRepository postRepo;
    private ModelMapper modelMapper;
    public PostServiceImpl(PostRepository postRepo,ModelMapper modelMapper)
    {
        this.postRepo = postRepo;
        this.modelMapper=modelMapper;
    }

    @Override
    public PostDto createpost(PostDto postDto) {

        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post savedPost = postRepo.save(post);

        PostDto dto = new PostDto();

        dto.setId(savedPost.getId());
        dto.setTitle(savedPost.getTitle());
        dto.setDescription(savedPost.getDescription());
        dto.setContent(savedPost.getContent());

        return dto;
    }

    @Override
    public PostDto FindById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound(id)
        );

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

    @Override
    public List<PostDto> FindAllPosts(int pageNo, int pageSize, String sortby, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortby).ascending() : Sort.by(sortby).descending();


        Pageable Pageable = PageRequest.of(pageNo, pageSize,sort);
        Page<Post> allposts = postRepo.findAll(Pageable);
        List<Post> content = allposts.getContent();
        List<PostDto> dto = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return dto;

    }

    @Override
    public String DeleteById(long id) {
        postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound(id)
        );

        postRepo.deleteById(id);
        return null;

    }

    @Override
    public PostDto UpdatePost(long id, PostDto postDto) {
        postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound(id)
        );
        Post updatedPost = mapToEntity(postDto);
        updatedPost.setId(id);
        Post update = postRepo.save(updatedPost);
        return mapToDto(update);

    }

    public PostDto mapToDto(Post post){
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

    public Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }



}
