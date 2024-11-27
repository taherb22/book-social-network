package com.taher.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
@RequiredArgsConstructor

public  class BeansConfig
{
    private final  UserDetailsService userDetailsService;
    @Bean
    public  AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider() ;
        authprovider.setUserDetailsService(userDetailsService);
        authprovider.setPasswordEncoder(passwordencoder());
        return authprovider ;
    }
   @Bean
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }

}
