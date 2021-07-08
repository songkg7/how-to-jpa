package com.fastcampus.jpa.service;

import static org.junit.jupiter.api.Assertions.*;

import com.fastcampus.jpa.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("1. commentTest")
    @Test
    void test_1() throws Exception {
        commentService.init();

//        commentRepository.findAll().forEach(System.out::println);

        commentService.updateSomething();
        commentService.insertSomething();

    }


}