package com.fastcampus.jpa.repository;

import com.fastcampus.jpa.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

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

    @Test
    void crud4() {
        long count = userRepository.count();
        System.out.println("count = " + count);
    }

    @Test
    void crud5() {
        userRepository.deleteById(1L);
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud6() {
        userRepository.deleteAll();
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud7_page() {
        // NOTE: page 는 0페이지부터 시작한다.
        Page<User> users = userRepository.findAll(PageRequest.of(0, 3));

        System.out.println("users = " + users);
        System.out.println("users.getTotalElements() = " + users.getTotalElements());
        System.out.println("users.getTotalPages() = " + users.getTotalPages());
        System.out.println("users.getNumberOfElements() = " + users.getNumberOfElements());
        System.out.println("users.getSort() = " + users.getSort());
        System.out.println("users.getSize() = " + users.getSize());
        users.getContent().forEach(System.out::println);
    }

    @Test
    void example() {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith()); // ex) contains(), ...

        Example<User> example = Example.of(new User("ma", "fastcampus.com"), matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }



}