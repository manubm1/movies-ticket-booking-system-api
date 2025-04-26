package com.example.mtb.service;

import com.example.mtb.dto.UserRegistrationRequest;
import com.example.mtb.entity.UserDetails;

public interface UserService {
    UserDetails userRegistration(UserRegistrationRequest users);
}
