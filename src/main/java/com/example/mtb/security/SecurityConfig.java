package com.example.mtb.security;

import com.example.mtb.enums.TokenType;

import com.example.mtb.security.filter.AuthFilters;
import com.example.mtb.security.jwt.JWTServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {


    private final JWTServiceImpl jwtService;


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Order(2)
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable());

        http.securityMatcher("/**");

        http.authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.POST,"/registration","/login")
                .permitAll()
                .anyRequest().authenticated());

//        http.formLogin(Customizer.withDefaults());
        http.addFilterBefore(new AuthFilters(jwtService,TokenType.ACCESS), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Order(1)
    @Bean
    SecurityFilterChain refreshFilter(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable());

        http.securityMatcher("/refresh/**");
        http.authorizeHttpRequests(auth->auth
                .anyRequest()
                .authenticated());

        setdefault(new AuthFilters(jwtService,TokenType.REFRESH),http);

        return http.build();

        }


    HttpSecurity setdefault(AuthFilters authFilters,HttpSecurity http) throws Exception {

        http.csrf(csrf->csrf.disable());
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(authFilters,UsernamePasswordAuthenticationFilter.class);
        return  http;
    }

}
