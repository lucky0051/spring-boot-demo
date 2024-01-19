package com.ks.practice.repository;


import com.ks.practice.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDetailsRepository extends JpaRepository<LoginDetails, Long> {
    LoginDetails findByUserName(String username);
}
