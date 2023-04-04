package com.example.bukkinyulszbackend.security;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.security.data.CustomUserDetails;
import com.example.bukkinyulszbackend.services.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);


    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public void setCustomUserDetailsService(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final String username = auth.getPrincipal() + "";
        final String password = auth.getCredentials() + "";
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (!userDetails.isEnabled()) {
            throw new DisabledException("disabled account");
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        System.out.println("customUserDetails: "+customUserDetails);
        if (!customUserDetails.verifyPassword(password)) {
            throw new BadCredentialsException("bad credentials");
        }
        System.out.println("asd");
        return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
