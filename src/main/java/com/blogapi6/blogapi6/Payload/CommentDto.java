package com.blogapi6.blogapi6.Payload;

import lombok.Data;

@Data
public class CommentDto {

    private long id;
    private String name;
    private String email;
    private String body;
}
