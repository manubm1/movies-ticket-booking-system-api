package com.example.mtb.security.filter;

import com.example.mtb.enums.TokenType;
import com.example.mtb.security.jwt.AuthenticateTokenDetails;
import com.example.mtb.security.jwt.ExtractedToken;
import com.example.mtb.security.jwt.JWTServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class AuthFilters extends OncePerRequestFilter {

    private final JWTServiceImpl jwtService;
    private final TokenType tokenType;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isvalidate(token)) {
            if (token.contains("Bearer")) {
                token = token.substring(7);
                var extractedToken = jwtService.pasrseToken(token);

                Claims claims = Optional.ofNullable(extractedToken).
                        map(ExtractedToken::claims).
                        orElse(null);

                JwsHeader headers = Optional.ofNullable(extractedToken).
                        map(ExtractedToken::headers).
                        orElse(null);


                boolean tokenTypes = Optional.ofNullable(headers).
                        map(h -> (String) h.get("type"))
                        .map(String::toUpperCase)
                        .map(TokenType::valueOf)
                        .filter(t -> t.equals(tokenType))
                        .map(t -> true)
                        .orElse(false);


                if (!tokenTypes || claims == null) filterChain.doFilter(request, response);
                String role = claims.get("role", String.class);
                String email = claims.getSubject();

                if (isvalidate(email) && isvalidate(role) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var authToken = new UsernamePasswordAuthenticationToken(email, role, List.of(new SimpleGrantedAuthority(role)));

                    authToken.setDetails(request);
                    SecurityContextHolder.getContext().setAuthentication((authToken));

                    AuthenticateTokenDetails tokenDetails = new AuthenticateTokenDetails(
                            email,role,claims.getExpiration().toInstant(),token);
                    request.setAttribute("tokenDetails", tokenDetails);
                }
                filterChain.doFilter(request, response);
            }
            filterChain.doFilter(request, response);
        }
        filterChain.doFilter(request, response);
    }


    private static Boolean isvalidate(String s){

        return s!=null && !s.isEmpty();
    }




}

