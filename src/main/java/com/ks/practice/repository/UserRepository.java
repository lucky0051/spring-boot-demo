package com.ks.practice.repository;

import com.ks.practice.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetailsEntity, Long> {
    Optional<UserDetailsEntity> findByEmailAndPhoneNo(String email, String phoneNumber);

}
