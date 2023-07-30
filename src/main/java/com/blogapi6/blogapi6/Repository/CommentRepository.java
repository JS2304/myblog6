package com.blogapi6.blogapi6.Repository;

import com.blogapi6.blogapi6.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long PostId);

}
