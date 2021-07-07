package com.fastcampus.jpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.fastcampus.jpa.domain.Comment;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager em;

    @DisplayName("1. commentTest")
    @Test
    @Transactional
    void test_1() throws Exception {
        Comment comment = new Comment();
        comment.setComment("별로에요");
//        comment.setCommentedAt(LocalDateTime.now());

        commentRepository.saveAndFlush(comment);

//        em.clear();

//        commentRepository.findAll().forEach(System.out::println);
//        System.out.println(commentRepository.findById(3L).get());
        System.out.println("comment = " + comment);
    }

}