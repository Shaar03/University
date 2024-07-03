package com.project.University.service.webtoken;

import com.project.University.entity.MyUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JWTService {

    @Autowired
    MyUser myUser;

    private static final String SECRET_KEY = "192F5B6A01F4BFF1E2EDE309F42CFB59379B1B18E8B9F77F7590BF0E3E4298B4BA3F70CF993D2A2490E4DB9A23A47A0EBE53F047FC211D8FE50CE29C96801610";
    private static final long VALIDITY = TimeUnit.MINUTES.toMillis(30);

    public String generateToken(UserDetails userDetails){
        Map<String, String> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().toString().replaceAll("[\\[\\]]", ""));
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey(){
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractUsername(String jwt){
        Claims claims = getClaims(jwt);

        if(claims != null)
            return claims.getSubject();
        return null;
    }

    private Claims getClaims(String jwt) {
        try{
            return Jwts.parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        }
        catch(JwtException e){
            System.out.println("Invalid JWT Signature");
            return null;
        }

    }

    public boolean isTokenValid(String jwt){
        Claims claims = getClaims(jwt);
        if(claims != null)
            return claims.getExpiration().after(Date.from(Instant.now()));
        return false;
    }
}
