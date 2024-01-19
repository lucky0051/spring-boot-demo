package com.ks.practice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequest {
    @NotEmpty
    private String name;
    @Email
    private String email;

    @Pattern(regexp = "\\d{10}", message = "The phone number code is invalid.")
    private String phoneNo;

    @Pattern(regexp = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
            , message = "The url number code is invalid.")
    private String linkedInURL;
}
