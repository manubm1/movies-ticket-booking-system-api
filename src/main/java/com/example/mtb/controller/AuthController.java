package com.example.mtb.controller;

import com.example.mtb.dto.AuthResponse;
import com.example.mtb.dto.LoginRequest;
import com.example.mtb.responseBuilders.ResponseStructure;
import com.example.mtb.security.jwt.AuthenticateTokenDetails;
import com.example.mtb.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
@Component
public class AuthController {


    private final AuthService authService;



    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<AuthResponse>> login(@RequestBody LoginRequest loginRequest){

          AuthResponse response = authService.login(loginRequest);
          return new ResponseEntity(response,HttpStatus.OK);
    }


    @PostMapping("/refresh")
    public ResponseEntity<ResponseStructure<AuthResponse>>  refresh(HttpServletRequest request){
        AuthenticateTokenDetails details = (AuthenticateTokenDetails) request.getAttribute("tokenDetails");
        AuthResponse responses = authService.refresh(details);
        return new ResponseEntity(responses,HttpStatus.OK);
    }

}
