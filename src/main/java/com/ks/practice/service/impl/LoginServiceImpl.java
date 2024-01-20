package com.ks.practice.service.impl;

import com.ks.practice.dto.request.LoginRequest;
import com.ks.practice.dto.response.LoginRespose;
import com.ks.practice.entity.UserLoginTokenEntity;
import com.ks.practice.repository.UserLoginTokenEntityRepository;
import com.ks.practice.service.LoginService;
import com.ks.practice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserLoginTokenEntityRepository userLoginTokenEntityRepository;
    private final JwtUtils jwtUtils;

    @Override
    public LoginRespose login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
        if (token != null) {
            UserLoginTokenEntity tokenEntity = new UserLoginTokenEntity();
            tokenEntity.setToken(token);
            tokenEntity.setUserName(loginRequest.getUserName());
            userLoginTokenEntityRepository.save(tokenEntity);
            return new LoginRespose(token);
        }
        return null;
    }
}
