package com.ks.practice.service.impl;

import com.ks.practice.entity.LoginDetails;
import com.ks.practice.repository.LoginDetailsRepository;
import com.ks.practice.security.model.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final LoginDetailsRepository loginRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<LoginDetails> user = loginRepository.findById(Long.parseLong(userId));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found ");
        }
        LoginDetails userDetails = user.get();
        return UserDetailsImpl.build(userDetails);
    }
}
