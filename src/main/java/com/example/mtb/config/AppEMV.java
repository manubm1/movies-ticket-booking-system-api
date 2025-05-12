package com.example.mtb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
@Getter
@Setter
public class AppEMV {

    private  Token token;
    @Getter
    @Setter
    public static class Token{
        private String screte;
        private Long accessToken;
        private Long refreshToken;


    }
}
