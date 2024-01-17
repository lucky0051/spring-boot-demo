package com.ks.practice.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse {
    protected String responseMessage;
    protected Integer responseCode;
}
