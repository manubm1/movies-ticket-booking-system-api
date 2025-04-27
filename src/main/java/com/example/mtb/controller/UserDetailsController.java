package com.example.mtb.controller;

import com.example.mtb.dto.UserRegistrationRequest;
import com.example.mtb.dto.UserRequest;
import com.example.mtb.dto.UserResponse;

import com.example.mtb.responseBuilders.ResponseBuilder;
import com.example.mtb.responseBuilders.ResponseStructure;
import com.example.mtb.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class UserDetailsController {

    private final UserService userService;

    @Autowired

    private final ResponseBuilder responseBuilder;

    @ResponseBody
    @PostMapping("/registration")
    ResponseEntity<ResponseStructure<UserResponse>> userRegistration(@Valid @RequestBody  UserRegistrationRequest users){
        UserResponse user = userService.userRegistration(users);
        return responseBuilder.success(HttpStatus.CREATED, " registered successfully to Booking platform", user);

    }

    @PatchMapping("/profile/{email}")
    ResponseEntity<ResponseStructure<UserResponse>> profileUpdate(@PathVariable String email, @RequestBody UserRequest request){
        UserResponse details = userService.profileUpdate(email,request);

        return responseBuilder.success(HttpStatus.ACCEPTED,"Profile updated Successfully",details);
    }
}
