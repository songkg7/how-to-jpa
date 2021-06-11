package com.fastcampus.jpa.repository;

import com.fastcampus.jpa.domain.Book;
import com.fastcampus.jpa.domain.BookRepository;
import com.fastcampus.jpa.domain.BookReviewInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("1. Crud")
    @Test
    void test_1() throws Exception {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
//        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(bookReviewInfoRepository.findAll());

    }

    @DisplayName("2. Crud")
    @Test
    void test_2() throws Exception {
        givenBookReviewInfo();

        Book result = bookReviewInfoRepository
                        .findById(1L)
                        .orElseThrow(RuntimeException::new)
                        .getBook();

        System.out.println("result = " + result);

    }

    private Book givenBook() {
        Book book = new Book();
        book.setName("JPA");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>>" + bookReviewInfoRepository.findAll());
    }

}