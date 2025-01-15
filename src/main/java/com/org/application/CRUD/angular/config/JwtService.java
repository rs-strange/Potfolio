package com.org.application.CRUD.angular.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    Logger logger = LoggerFactory.getLogger(JwtService.class);
    private static final String SECRET_KEY = "7e564320513c71527b4c236b262636535f305e5a513c6d63473e4a3e39544a3f";

    public String extractUsername(String token){
        logger.info("[JwtService] - extractUsername");
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails){
        logger.info("[JwtService] - generateToken");
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims, UserDetails userDetails){
        logger.info("[JwtService] - generateToken Jwts creacion");
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        logger.info("[JwtService] - isTokenValid");
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        logger.info("[JwtService] - isTokenValid");
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        logger.info("[JwtService] - extractExpiration");
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        logger.info("[JwtService] - extractClaim");
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    private Claims extractAllClaims(String token){
        logger.info("[JwtService] - extractAllClaims");
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(){
        logger.info("[JwtService] - getSignInKey");
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }



}
