package com.taher.book.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.ExpiresFilter;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpHeaders;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    final private jwtService jwtService ;
    @Override
    protected void doFilterInternal(@NonNull  HttpServletRequest request
            ,@NonNull  HttpServletResponse response
            ,@NonNull  FilterChain filterChain
    ) throws ServletException, IOException {
          if(request.getServletPath().contains("/api/v1/auth")){
              filterChain.doFilter(request, response);
              return ;
          }
          final String authheader = request.getHeader("Authorization");
          final String jwt;
          final String userEmail ;
          if(authheader==null || !authheader.startsWith("Bearer ")){
              filterChain.doFilter(request, response);
              return;
          }
          jwt = authheader.substring(7);
          userEmail = jwtService.extractUsername(jwt) ; 
    }
}
