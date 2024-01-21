package com.ks.practice.controller;

import com.itextpdf.text.DocumentException;
import com.ks.practice.dto.request.UserDetailsRequest;
import com.ks.practice.dto.response.ApiResponse;
import com.ks.practice.dto.response.UserDetailsResponse;
import com.ks.practice.dto.response.UsersDetailsResponse;
import com.ks.practice.entity.UserDetailsEntity;
import com.ks.practice.service.UserService;
import com.ks.practice.util.PdfUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody UserDetailsRequest userDetailsRequest) {
        UserDetailsEntity result = userService.addUser(userDetailsRequest);
        ApiResponse response = new ApiResponse();
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseMessage("Successfully added the data .");
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UsersDetailsResponse> getAllUser() {
        List<UserDetailsResponse> result = userService.getAllUsers();
        UsersDetailsResponse response = new UsersDetailsResponse(result);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseMessage("Success");
        return new ResponseEntity<UsersDetailsResponse>(response, HttpStatus.OK);

    }

    @PostMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportPdf(@PathVariable("id") Long id) throws IOException, DocumentException {
        List<Map<String, Object>> result = new ArrayList<>();
        result.add(userService.getUserMapById(id));
        ByteArrayOutputStream pdfStream = PdfUtil.PdfUtils.generatePdfStream(result);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String fileName = userService.getUserById(id).getName() + "_" + LocalDateTime.now().toString();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf");
        headers.setContentLength(pdfStream.size());
        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
    }
}
