package com.fastcampus.jpa.service;

import com.fastcampus.jpa.domain.User;
import com.fastcampus.jpa.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class EntityManagerTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("entityManagerTest. ")
    @Test
    void test_Count() throws Exception {
        System.out.println(em.createQuery("select u from User u").getResultList());
    }

    @DisplayName("cacheFindTest. ")
    @Test
    void test_cacheFindTest() throws Exception {

//        System.out.println(userRepository.findByEmail("haril@fastcampus.com"));
//        System.out.println(userRepository.findByEmail("haril@fastcampus.com"));
//        System.out.println(userRepository.findByEmail("haril@fastcampus.com"));
//
//        System.out.println(userRepository.findById(1L).get());
//        System.out.println(userRepository.findById(1L).get());
//        System.out.println(userRepository.findById(1L).get());

        userRepository.deleteById(1L);
    }

    @DisplayName("3. cache")
    @Test
    void test_3() throws Exception {
        User user = userRepository.findById(1L).get();
        user.setName("marrrrrtin");

        userRepository.save(user);

        System.out.println("--------------------------");

        user.setEmail("marrrrrrrtin@fastcampus.com");

        userRepository.save(user);

        userRepository.flush();

    }
}
