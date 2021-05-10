package com.fastcampus.jpa.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void test() {
        User user = new User();
        user.setEmail("haril@fastcampus.com");
        user.setName("haril");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User user2 = new User("haril", "haril@fastcampus.com");
        User user3 = User.builder()
                .name("haril")
                .email("haril@fastcampus.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        System.out.println(user);
    }

}