package com.fastcampus.jpa.service;

import com.fastcampus.jpa.domain.User;
import com.fastcampus.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EntityManager em;
    private final UserRepository userRepository;

    @Transactional
    public void put() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@fastcampus.com");

//        userRepository.save(user);

        em.persist(user);
//        em.detach(user);

        user.setName("newUserAfterPersist");
        em.merge(user);

//        em.flush();
//        em.clear();


        User user1 = userRepository.findById(1L).get();
        em.remove(user1);

        user1.setName("marrrrrrrtin");
        em.merge(user1);

    }

}
