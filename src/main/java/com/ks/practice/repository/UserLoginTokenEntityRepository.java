package com.ks.practice.repository;

import com.ks.practice.entity.UserLoginTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginTokenEntityRepository extends JpaRepository<UserLoginTokenEntity, Long> {
}
