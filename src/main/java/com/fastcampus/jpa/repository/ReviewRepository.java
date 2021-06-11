package com.fastcampus.jpa.repository;

import com.fastcampus.jpa.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
