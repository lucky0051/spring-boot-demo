package com.ks.practice.audit;

import com.google.gson.Gson;
import com.ks.practice.entity.ResponseMessage;
import com.ks.practice.repository.ResponseMessageRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class AuditResponseLogIntercepter implements HandlerInterceptor {


    private final ResponseMessageRepository responseLogRepository;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        // Log and store response information
        int statusCode = response.getStatus();
        ContentCachingResponseWrapper responseCacheWrapperObject = new ContentCachingResponseWrapper((HttpServletResponse) response);
        byte[] responseArray = responseCacheWrapperObject.getContentAsByteArray();
        try {
            String responseStr = new String(responseArray, responseCacheWrapperObject.getCharacterEncoding());
            Gson gson = new Gson();

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        ResponseMessage responseLog = new ResponseMessage();
        responseLogRepository.save(responseLog);
    }
}

