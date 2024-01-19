package com.ks.practice.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsResponse {
    private String name;
    private String email;
    private String phonneNo;
    private String linkedInURL;
}
