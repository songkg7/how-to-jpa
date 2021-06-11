package com.fastcampus.jpa.domain.listener;

import com.fastcampus.jpa.domain.User;
import com.fastcampus.jpa.domain.UserHistory;
import com.fastcampus.jpa.repository.UserHistoryRepository;
import com.fastcampus.jpa.support.BeanUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {

    @PrePersist
    @PreUpdate
    public void prePersistAndPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
