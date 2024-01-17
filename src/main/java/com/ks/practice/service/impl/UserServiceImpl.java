package com.ks.practice.service.impl;

import com.ks.practice.dto.request.UserDetailsRequest;
import com.ks.practice.entity.UserDetails;
import com.ks.practice.mapper.UserDetailsMapper;
import com.ks.practice.repository.UserRepository;
import com.ks.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    UserDetailsMapper userDetailsMapper;
    private UserRepository userRepository;

    @Override
    public UserDetails addUser(UserDetailsRequest userDetailsRequest) {
        UserDetails userTobeSaved = userDetailsMapper.toUserDetails(userDetailsRequest);
        Optional<UserDetails> existing = userRepository.findByEmailAndPhoneNo(userDetailsRequest.getEmail(), userDetailsRequest.getPhonneNo());
        if (existing.isPresent()) {
            UserDetails existingUser = existing.get();
            existingUser.setName(userDetailsRequest.getName());
            existingUser.setEmail(userDetailsRequest.getEmail());
            existingUser.setPhoneNo(userDetailsRequest.getPhonneNo());
            userTobeSaved = existingUser;
        }
        return userRepository.save(userTobeSaved);
    }
}
