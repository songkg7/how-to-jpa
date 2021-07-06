package com.fastcampus.jpa.repository;

import com.fastcampus.jpa.domain.Review;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @DisplayName("1. reviewTest")
    @Transactional
    @Test
    void test_1() throws Exception {
        // NOTE: N + 1 의 발생과 해결법, fetch join

        List<Review> reviews = reviewRepository.findAll();
//        List<Review> reviews = reviewRepository.findAllByFetchJoin();
//        List<Review> reviews = reviewRepository.findAllByEntityGraph();

//        System.out.println("reviews = " + reviews);
//
//        System.out.println("전체를 가져왔습니다.");
//
//        System.out.println(reviews.get(0).getComments());
//
//        System.out.println("첫번째 리뷰의 댓글들을 가져왔습니다.");
//
//        System.out.println(reviews.get(1).getComments());
//
//        System.out.println("두번째 리뷰의 댓글들을 가져왔습니다.");

        reviews.forEach(System.out::println);
    }

}
