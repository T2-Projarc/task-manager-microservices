package com.example.authservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Replace this with your generated base64-encoded secret key
    private static final String SECRET_KEY = "wK8gH3Dh0JUZK+GkUP0rP+lPSYwSLJJxQlX6DYwIurY=";

    private final Key key;

    public JwtUtil() {
        // Decode the base64-encoded key to get the byte array
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        // Create the key
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                // Set expiration as per your requirement
                .setExpiration(new Date(System.currentTimeMillis() + 36000000)) // 10 hours
                .signWith(key)
                .compact();
    }
}
