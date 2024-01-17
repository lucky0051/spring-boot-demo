package com.ks.practice.service;

import com.ks.practice.dto.request.UserDetailsRequest;
import com.ks.practice.entity.UserDetails;

public interface UserService {
    public UserDetails addUser(UserDetailsRequest userDetailsRequest);
}
