package com.example.authservice.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Encoders;

import java.security.Key;

public class KeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generate a key for HS256
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Base64 Encoded Secret Key: " + base64Key);
    }
}
