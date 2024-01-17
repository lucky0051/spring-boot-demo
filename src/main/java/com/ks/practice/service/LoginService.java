package com.ks.practice.service;

import com.ks.practice.dto.request.LoginRequest;
import com.ks.practice.dto.response.LoginRespose;

public interface LoginService {

    public LoginRespose login(LoginRequest loginRequest);
}
