package com.taher.book.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.lang.System.currentTimeMillis;
import static java.util.stream.Collectors.toList;


@Service
public class JwtService
{
    @Value("${application.security.jwt.expiration}")
     private long jwtExpiration;
    @Value("${application.security.jwt.secret-key}")
     private String secretKey ;
     public String generateToken(UserDetails UserDetails) {
        return generateToken(new HashMap<String, Object>(), UserDetails);

     }
     private String generateToken(Map<String, Object> claims , UserDetails UserDetails) {

         return buildToken(claims, UserDetails, jwtExpiration) ;
     }
     private String buildToken(Map<String, Object> claims , UserDetails UserDetails, long jwtExpiration
     )
     {
         var authorities = UserDetails.getAuthorities().stream()
                 .map(GrantedAuthority::getAuthority)
                 .toList() ;
         return Jwts
                 .builder()
                 .setClaims(claims)
                 .setSubject(UserDetails.getUsername())
                 .setIssuedAt(new Date(System.currentTimeMillis()))
                 .setExpiration(new Date(System.currentTimeMillis() +jwtExpiration))
                 .claim("authorities" , authorities)
                 .signWith(getSignInKey())
                 .compact();
     }
     public boolean isTokenValid(String token, UserDetails UserDetails) {
        final String username = extractUsername(token) ;

     }

    private String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject) ;
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
         final Claims claims = extractAllClaims(token) ;
         return claimsResolver.apply(claims) ;
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();



    }

    private Key getSignInKey() {
         byte[] keyBytes = Decoders.BASE64.decode(secretKey) ;
         return Keys.hmacShaKeyFor(keyBytes) ;

     }



}