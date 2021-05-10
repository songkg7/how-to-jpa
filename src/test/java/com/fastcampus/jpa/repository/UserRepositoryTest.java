package com.fastcampus.jpa.repository;

import com.fastcampus.jpa.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() {
//        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
        User user1 = new User("jack", "jack@fastcampus.com");
        User user2 = new User("steve", "steve@fastcampus.com");

        userRepository.saveAll(Lists.newArrayList(user1, user2));

        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);

    }

    @Test
    @Transactional
    void crud2() {

        User user = userRepository.getOne(1L); // getOne 은 Lazy 로딩을 하기 때문에 트랜잭션 안에서 실행되야한다.

        System.out.println("user = " + user);
    }

    @Test
    void crud3() {
        User user = userRepository.findById(1L).orElse(null); // findById 는 옵셔널로 래핑되어 있다.
        System.out.println(user);
    }




}