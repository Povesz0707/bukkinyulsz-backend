package com.example.bukkinyulszbackend.security.util;

import com.example.bukkinyulszbackend.security.data.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Component
public class JwtUtil {
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
    @Value("${main.jwt.secretKey}")
    private String secretKeyString;
    private SecretKey secretKey;

    @Value("${main.jwt.expiration}")
    private int expiration;


    public String generateJwtToken(@NotNull final Authentication auth) {
        final CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiration))
                .signWith( SignatureAlgorithm.HS512, secretKeyString)
                .compact();
    }

    public String getUserNameFromJwtToken(@NotNull final String token) {
        return Jwts.parserBuilder().setSigningKey(secretKeyString).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(@NotNull final String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKeyString).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.warn("JWT token is invalid: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}", e.getMessage());
        } catch (SignatureException e) {
            log.warn("JWT token signature is invalid: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
