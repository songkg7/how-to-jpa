package com.fastcampus.jpa.service;

import com.fastcampus.jpa.domain.Author;
import com.fastcampus.jpa.domain.Book;
import com.fastcampus.jpa.repository.AuthorRepository;
import com.fastcampus.jpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void putBookAndAuthor() {
        Book book = new Book();  // 비영속상태
        book.setName("JPA 시작하기");

        bookRepository.save(book);  // 영속상태

        try {
            authorService.putAuthor();
        } catch (RuntimeException e) {
//            e.printStackTrace();
        }

//        throw new RuntimeException("오류가 발생하였습니다. transaction은 어떻게 될까요?");

    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void get(Long id) {
        System.out.println(">>>" + bookRepository.findById(id));
        System.out.println(">>>" + bookRepository.findAll());

        System.out.println(">>>" + bookRepository.findById(id));
        System.out.println(">>>" + bookRepository.findAll());

        Book book = bookRepository.findById(id).get();
        book.setName("바뀔까?");
        bookRepository.save(book);

    }

}
