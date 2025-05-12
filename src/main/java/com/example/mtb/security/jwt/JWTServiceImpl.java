package com.example.mtb.security.jwt;

import com.example.mtb.config.AppEMV;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Configuration
@Component
@AllArgsConstructor
public class JWTServiceImpl {
//    private String secret;
       private  final AppEMV emv ;

    public String createJwtToken(TokenPayload tokenPayload) {
        return Jwts.builder()
                .setHeaderParam("type",tokenPayload.tokentype().name())
                .setClaims(tokenPayload.claims())
                .setSubject(tokenPayload.subject())
                .setIssuedAt(new Date(tokenPayload.IssuedAt().toEpochMilli()))
                .setExpiration(new Date(tokenPayload.expiration().toEpochMilli()))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignatureKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(emv.getToken().getScrete()));
    }


    public ExtractedToken pasrseToken(String token){

        try {
              Jws<Claims> claims= Jwts.parserBuilder().setSigningKey(getSignatureKey()).build().parseClaimsJws(token);
           ExtractedToken extractToken = new ExtractedToken( claims.getHeader(), claims.getBody());
           return extractToken;
        } catch (JwtException ex){
            return null;
        }

    }


}
