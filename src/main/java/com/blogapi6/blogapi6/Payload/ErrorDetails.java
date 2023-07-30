package com.blogapi6.blogapi6.Payload;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ErrorDetails {
    private Date time;
    private String message;
    private String Description;

    public ErrorDetails(Date time, String message, String description) {
        this.time = time;
        this.message = message;
        Description = description;
    }
}
