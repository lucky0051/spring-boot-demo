package com.ks.practice.controller;

import com.ks.practice.dto.request.UserDetailsRequest;
import com.ks.practice.dto.response.ApiResponse;
import com.ks.practice.entity.UserDetails;
import com.ks.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@RequestBody UserDetailsRequest userDetailsRequest) {
        UserDetails result = userService.addUser(userDetailsRequest);
        ApiResponse response = new ApiResponse();
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseMessage("Successfully added the data .");
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }
}
