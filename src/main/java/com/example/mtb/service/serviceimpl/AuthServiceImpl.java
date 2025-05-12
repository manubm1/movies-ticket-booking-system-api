package com.example.mtb.service.serviceimpl;

import com.example.mtb.config.AppEMV;
import com.example.mtb.dto.AuthResponse;
import com.example.mtb.dto.LoginRequest;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.enums.TokenType;
import com.example.mtb.exception.UserNotFoundException;
import com.example.mtb.repository.UserDetailsRepository;
import com.example.mtb.security.jwt.AuthenticateTokenDetails;
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
    private final  AuthenticationManager authenticationManager;
    private  final UserDetailsRepository userDetailsRepository;
    private final AppEMV emv;



    public AuthResponse login(LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(token);

        if(!authentication.isAuthenticated()){
            throw new UserNotFoundException("Invalid user name ");
        }

        UserDetails userDetails = userDetailsRepository.findByEmail(loginRequest.email());


        Map<String,Object> claims = new HashMap<>();
        if(authentication.isAuthenticated()){
            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList().get(0);
            claims.put("role",role);
        }

        Instant now = Instant.now();

      TokenPayload accessPayload = new TokenPayload(
              claims,
              authentication.getName(),
              now,
              now.plusSeconds(emv.getToken().getAccessToken()),
              TokenType.ACCESS
      );


        TokenPayload refreshPayload = new TokenPayload(
                claims,
                authentication.getName(),
                now,
                now.plusSeconds(emv.getToken().getRefreshToken()),
                TokenType.REFRESH
        );


        String access= jwtService.createJwtToken(accessPayload);
        String refresh = jwtService.createJwtToken(refreshPayload);

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




    @Override
    public AuthResponse refresh(AuthenticateTokenDetails details) {
        UserDetails user =  userDetailsRepository.findByEmail(details.email());

        Map<String,Object> claims = new HashMap<>();
        String role = details.role();
        claims.put("role",role);

        Instant now = Instant.now();

        TokenPayload accessPayload = new TokenPayload(
                claims,
                details.email(),
                now,
                now.plusSeconds(emv.getToken().getAccessToken()),
                TokenType.ACCESS
        );



        String accessToken = jwtService.createJwtToken(accessPayload);


        return  new AuthResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getUserRole(),
                accessPayload.expiration().toEpochMilli(),
                details.tokenExpiration().toEpochMilli(),
                accessToken,
                details.authenticateToken()
        );
    }

}
