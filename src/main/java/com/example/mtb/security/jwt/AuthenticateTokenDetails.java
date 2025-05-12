package com.example.mtb.security.jwt;

import java.time.Instant;

public record AuthenticateTokenDetails(String email,
                                       String role,
                                       Instant tokenExpiration,
                                       String authenticateToken
                                       ) {
}
