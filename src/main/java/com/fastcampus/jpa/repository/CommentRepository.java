package com.fastcampus.jpa.repository;

import com.fastcampus.jpa.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
