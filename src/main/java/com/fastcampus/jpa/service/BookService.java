package com.fastcampus.jpa.service;

import com.fastcampus.jpa.domain.Author;
import com.fastcampus.jpa.domain.Book;
import com.fastcampus.jpa.repository.AuthorRepository;
import com.fastcampus.jpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public void putBookAndAuthor() throws Exception {
        Book book = new Book();  // 비영속상태
        book.setName("JPA 시작하기");

        bookRepository.save(book);  // 영속상태

        Author author = new Author();
        author.setName("martin");

        // save() 는 자체적으로 트랜잭션이 걸려있다. 상위에 별도의 트랜잭션이 없다면 save 단위로 처리된다.
        authorRepository.save(author);

        throw new Exception("오류가 나서 커밋이 발생하지 않습니다.");
    }

}
