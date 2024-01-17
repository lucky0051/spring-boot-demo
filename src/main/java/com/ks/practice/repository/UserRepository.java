package com.ks.practice.repository;

import com.ks.practice.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByEmailAndPhoneNo(String email, String phoneNumber);
}
