package com.blogapi6.blogapi6.Service;

import com.blogapi6.blogapi6.Payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createpost(PostDto postDto);

    public PostDto FindById(long id);

    public List<PostDto> FindAllPosts(int pageNo, int pageSize, String sortby, String sortDir);

    public String DeleteById(long id);

    public PostDto UpdatePost(long id, PostDto postDto);

}
