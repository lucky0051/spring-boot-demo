package com.ks.practice.repository;


import com.ks.practice.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginDetailsRepository extends JpaRepository<LoginDetails, Long> {
    Optional<LoginDetails> findByUserName(String username);
}
