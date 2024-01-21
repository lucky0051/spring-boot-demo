package com.ks.practice.repository;

import com.ks.practice.entity.UserLoginToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginTokenRepository extends JpaRepository<UserLoginToken, Long> {
}
