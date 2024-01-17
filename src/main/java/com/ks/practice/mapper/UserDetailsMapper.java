package com.ks.practice.mapper;


import com.ks.practice.dto.request.UserDetailsRequest;
import com.ks.practice.entity.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetails toUserDetails(UserDetailsRequest request) {
        if (request != null) {
            return UserDetails.builder().email(request.getEmail())
                    .name(request.getEmail()).linkedInURL(request.getLinkedInURL()).build();
        }
        return null;
    }
}
