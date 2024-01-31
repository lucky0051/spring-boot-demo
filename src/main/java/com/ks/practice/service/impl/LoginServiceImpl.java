package com.ks.practice.service.impl;

import com.ks.practice.dto.request.LoginRequest;
import com.ks.practice.dto.response.LoginRespose;
import com.ks.practice.entity.LoginDetails;
import com.ks.practice.entity.UserLoginToken;
import com.ks.practice.repository.LoginDetailsRepository;
import com.ks.practice.repository.UserLoginTokenRepository;
import com.ks.practice.security.model.UserDetailsImpl;
import com.ks.practice.service.LoginService;
import com.ks.practice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final LoginDetailsRepository loginDetailsRepository;
    private final AuthenticationManager authenticationManager;
    private final UserLoginTokenRepository userLoginTokenEntityRepository;
    private final JwtUtils jwtUtils;

    @Override
    public LoginRespose login(LoginRequest loginRequest) {
        Optional<LoginDetails> user = loginDetailsRepository.findByUserName(loginRequest.getUserName());
        if (user.isPresent()) {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.get().getId().toString(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String token = jwtUtils.generateTokenFromUserId(userDetails.getId());
            if (token != null) {
                UserLoginToken tokenEntity = new UserLoginToken();
                tokenEntity.setToken(token);
                tokenEntity.setUserName(loginRequest.getUserName());
                userLoginTokenEntityRepository.save(tokenEntity);
                return new LoginRespose(token);
            }
        }

        return null;
    }
}
