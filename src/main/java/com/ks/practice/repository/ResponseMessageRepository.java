package com.ks.practice.repository;

import com.ks.practice.entity.ResponseMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseMessageRepository extends JpaRepository<ResponseMessage, Long> {
}
