package com.blogapi6.blogapi6.Payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class PostDto {
    private long id;
    @NotEmpty
    @Size(min=4,message = "title atleast 4 Char")
    private String title;
    @NotEmpty
    @Size(min = 5,message = "description atleast 5 Char")
    private String description;
    @NotEmpty
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
