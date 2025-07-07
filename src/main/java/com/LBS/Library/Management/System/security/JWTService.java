package com.LBS.Library.Management.System.security;

import com.LBS.Library.Management.System.util.SecretKeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    public final String secretKey = SecretKeyGenerator.generateSecretKey();

    public String generateToken(@NotBlank(message = "Email is required!") String email) {
        Map<String, Object> Claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(Claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() * 36000))
                .and()
                .signWith(getKey())
                .compact();
    }
    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token){
        return getClaims(token).getSubject();
    }

    public Date getExpiration(String token){
        return getClaims(token).getExpiration();
    }

    private boolean isExpired(String token){
        return getExpiration(token).before(new Date());
    }

    public boolean validate(String token, UserDetails userDetails){
        String email = getUsername(token);
        return(email.equals(userDetails.getUsername())) && !isExpired(token);
    }
}
