package com.ks.practice.service;

import com.ks.practice.dto.request.UserDetailsRequest;
import com.ks.practice.dto.response.UserDetailsResponse;
import com.ks.practice.entity.UserDetailsEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    public UserDetailsEntity addUser(UserDetailsRequest userDetailsRequest);

    public List<UserDetailsResponse> getAllUsers();

    public UserDetailsResponse getUserById(Long id);

    public Map<String, Object> getUserMapById(Long id);


}
