package com.fastcampus.jpa.service;

import com.fastcampus.jpa.domain.Book;
import com.fastcampus.jpa.repository.AuthorRepository;
import com.fastcampus.jpa.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("1. transactionTest")
    @Test
    void test_1() throws Exception {
        try {
            bookService.putBookAndAuthor();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("book : " + bookRepository.findAll());
        System.out.println("author : " + authorRepository.findAll());

    }

    @DisplayName("2. isolation")
    @Test
    void test_2() throws Exception {
        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println(">>>" + bookRepository.findAll());

    }

}