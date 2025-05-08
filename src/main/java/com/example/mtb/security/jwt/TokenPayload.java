package com.example.mtb.security.jwt;

import java.time.Instant;
import java.util.Map;

public record TokenPayload(Map<String,Object> claims, String subject, Instant IssuedAt, Instant expiration) {
}
