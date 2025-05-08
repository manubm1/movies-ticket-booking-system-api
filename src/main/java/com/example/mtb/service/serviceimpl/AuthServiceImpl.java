package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.AuthResponse;
import com.example.mtb.dto.LoginRequest;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.exception.UserNotFoundException;
import com.example.mtb.repository.UserDetailsRepository;
import com.example.mtb.security.jwt.JWTServiceImpl;
import com.example.mtb.security.jwt.TokenPayload;
import com.example.mtb.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements  AuthService{


    private final JWTServiceImpl jwtService;
    private AuthenticationManager authenticationManager;
    private  final UserDetailsRepository userDetailsRepository;



    public AuthResponse login(LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(token);

        if(! authentication.isAuthenticated()){
            throw new UserNotFoundException("Invalid user name ");
        }

        UserDetails userDetails = userDetailsRepository.findByEmail(loginRequest.email());


        Map<String,Object> claims = new HashMap<>();
        if(authentication.isAuthenticated()){
            String role = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().get(0);
            claims.put("role",role);
        }

      TokenPayload payload = new TokenPayload(
              claims,
              authentication.getName(),
              Instant.now(),
              Instant.now().plusSeconds(300)
      );

        TokenPayload accessPayload = new TokenPayload(
                claims,
                authentication.getName(),
                Instant.now(),
                Instant.now().plusSeconds(300)
        );

        TokenPayload refreshPayload = new TokenPayload(
                claims,
                authentication.getName(),
                Instant.now(),
                Instant.now().plusSeconds(86400)
        );


        String jwt = jwtService.createJwtToken(payload);
        String access = jwtService.accessToken(accessPayload);
        String refresh = jwtService.refreshToken(refreshPayload);

        return new AuthResponse(userDetails.getUserId(),
                                 userDetails.getUsername(),
                                 userDetails.getEmail(),
                                 userDetails.getUserRole(),
                                 accessPayload.expiration().toEpochMilli(),
                                 refreshPayload.expiration().toEpochMilli(),
                                 access,
                                 refresh
        );
    }


}
