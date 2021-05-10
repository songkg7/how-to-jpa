package com.fastcampus.jpa.repository;

import com.fastcampus.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
