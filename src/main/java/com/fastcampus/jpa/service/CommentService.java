package com.fastcampus.jpa.service;

import com.fastcampus.jpa.domain.Comment;
import com.fastcampus.jpa.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void init() {
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setComment("최고에요");

            commentRepository.save(comment);
        }
    }

    // readOnly = true 로 설정하면 더티체킹이 일어나지 않으므로 대용량 처리에서 유리하다.
    // 필요한 경우에 메소드 scope 에서 따로 트랜잭션 옵션을 주면 된다.
    @Transactional(readOnly = true)
    public void updateSomething() {
        List<Comment> comments = commentRepository.findAll();

        for (Comment comment : comments) {
            comment.setComment("별로에요");

            // NOTE: Dirty check, 영속성 관리 중에 일어난 변경은 별도의 세이브 요청이 없더라도 영속화처리된댜.
//            commentRepository.save(comment);
        }
    }

    @Transactional
    public void insertSomething() {
        // 생성만 하면 영속성이 관리되지 않는다.
//        Comment comment = new Comment();

        // 찾아온 객체는 영속성이 관리되고 있다.
        Comment comment = commentRepository.findById(1L).get();
        comment.setComment("이건 뭐죠");

//        commentRepository.save(comment);

    }

}
