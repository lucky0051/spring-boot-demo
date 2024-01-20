package com.ks.practice.audit;

import com.google.gson.Gson;
import com.ks.practice.entity.ResponseMessage;
import com.ks.practice.repository.ResponseMessageRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuditResponseLogIntercepter implements HandlerInterceptor {


    private final ResponseMessageRepository responseLogRepository;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        // Log and store response information
        int statusCode = response.getStatus();
        String responseStr = null;
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        byte[] responseArray = responseWrapper.getContentAsByteArray();
        try {
            responseStr = new String(responseArray, responseWrapper.getCharacterEncoding());
            log.debug("Response " + responseStr);
            Gson gson = new Gson();

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        ResponseMessage responseLog = new ResponseMessage();
        responseLog.setResponseCode(response.getStatus());
        responseLog.setResponseMessage(responseStr);
        responseLog.setTime(LocalDateTime.now());
        responseLogRepository.save(responseLog);
    }
}

