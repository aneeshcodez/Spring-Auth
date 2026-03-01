package com.example.demo.Service;


import io.jsonwebtoken.Claims;

import java.util.function.Function;

public class JwtService {

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public  <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaim(String token) {
    }

}
