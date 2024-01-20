package com.ks.practice.mapper;


import com.ks.practice.dto.request.UserDetailsRequest;
import com.ks.practice.dto.response.UserDetailsResponse;
import com.ks.practice.entity.UserDetailsEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetailsEntity toUserDetails(UserDetailsRequest request) {
        if (request != null) {
            return UserDetailsEntity.builder()
                    .email(request.getEmail())
                    .phoneNo(request.getPhoneNo())
                    .name(request.getName())
                    .linkedInURL(request.getLinkedInURL()).build();
        }
        return null;
    }

    public UserDetailsResponse toUserDetails(UserDetailsEntity userDetails) {
        if (userDetails != null) {
            return UserDetailsResponse.builder()
                    .email(userDetails.getEmail())
                    .phoneNo(userDetails.getPhoneNo())
                    .name(userDetails.getName())
                    .linkedInURL(userDetails.getLinkedInURL()).build();
        }
        return null;
    }


}
