package com.ks.practice.service.impl;

import com.ks.practice.dto.request.UserDetailsRequest;
import com.ks.practice.dto.response.UserDetailsResponse;
import com.ks.practice.entity.UserDetailsEntity;
import com.ks.practice.mapper.UserDetailsMapper;
import com.ks.practice.repository.UserRepository;
import com.ks.practice.service.UrlShortnerService;
import com.ks.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    UserDetailsMapper userDetailsMapper;
    private UserRepository userRepository;

    private UrlShortnerService bitlyService;

    @Override
    public UserDetailsEntity addUser(UserDetailsRequest userDetailsRequest) {
        UserDetailsEntity userTobeSaved = userDetailsMapper.toUserDetails(userDetailsRequest);
        Optional<UserDetailsEntity> existing = userRepository.findByEmailAndPhoneNo(userDetailsRequest.getEmail(),
                userDetailsRequest.getPhoneNo());
        if (existing.isPresent()) {
            UserDetailsEntity existingUser = existing.get();
            existingUser.setName(userDetailsRequest.getName());
            existingUser.setEmail(userDetailsRequest.getEmail());
            existingUser.setPhoneNo(userDetailsRequest.getPhoneNo());
            userTobeSaved = existingUser;
        }
        return userRepository.save(userTobeSaved);
    }

    @Override
    public List<UserDetailsResponse> getAllUsers() {
        List<UserDetailsEntity> allUsers = userRepository.findAll();
        List<UserDetailsResponse> result = new ArrayList<>();
        if (!allUsers.isEmpty()) {
            result = allUsers.stream().map(userDetails -> {
                UserDetailsResponse userdetailsResponse = userDetailsMapper.toUserDetails(userDetails);
                String userDetailsShortLinkedInUrl = bitlyService.getShortUrl(userDetails.getLinkedInURL());
                userdetailsResponse.setLinkedInURL(userDetailsShortLinkedInUrl);
                return userdetailsResponse;
            }).collect(Collectors.toList());
        }

        return result;
    }

    @Override
    public UserDetailsResponse getUserById(Long id) {
        Optional<UserDetailsEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            return userDetailsMapper.toUserDetails(user.get());
        }
        return null;
    }

    public Map<String, Object> getUserMapById(Long id) {
        UserDetailsResponse result = getUserById(id);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("Name", result.getName());
        userMap.put("Email", result.getEmail());
        userMap.put("Mobile", result.getPhonneNo());
        userMap.put("LinkedIn", result.getLinkedInURL());
        return userMap;

    }
}
