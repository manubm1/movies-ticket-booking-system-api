package com.example.mtb.service;

import com.example.mtb.dto.UserRegistrationRequest;
import com.example.mtb.dto.UserRequest;
import com.example.mtb.dto.UserResponse;
import com.example.mtb.entity.UserDetails;

public interface UserService {
    UserResponse userRegistration(UserRegistrationRequest users);


    UserResponse profileUpdate(String email, UserRequest request);

    String softDelete(String email);
}
