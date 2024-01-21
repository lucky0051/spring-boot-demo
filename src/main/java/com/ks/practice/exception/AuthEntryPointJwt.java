package com.ks.practice.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ks.practice.dto.response.ApiResponse;
import com.ks.practice.entity.ResponseMessage;
import com.ks.practice.repository.ResponseMessageRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private final ResponseMessageRepository responseLogRepository;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        log.error("Unauthorized error: {}", authException.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ApiResponse apiResponse = new ApiResponse("Unauthorized", HttpStatus.UNAUTHORIZED.value());
        final ObjectMapper mapper = new ObjectMapper();


        //save status in  DB
        ResponseMessage responseLog = new ResponseMessage();
        responseLog.setResponseCode(response.getStatus());
        responseLog.setResponseMessage(authException.getMessage());
        responseLog.setTime(LocalDateTime.now());
        responseLogRepository.save(responseLog);
        mapper.writeValue(response.getOutputStream(), apiResponse);
    }
}