package com.blogapi6.blogapi6.Repository;

import com.blogapi6.blogapi6.Entity.Post;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
