package com.example.bukkinyulszbackend.security;

import com.example.bukkinyulszbackend.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.example.bukkinyulszbackend")
public class WebSecurityConfig {
    public static final String SECURITY_ACCESS_HAS_ROLE_ADMIN = "hasRole('ADMIN')";

    private static boolean enabled;
    private static String[] corsOrigins;

    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    public void setUnauthorizedHandler(AuthEntryPointJwt unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

    private CustomAuthenticationProvider authenticationProvider;

    @Autowired
    public void setAuthenticationProvider(CustomAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }


    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public void setCustomUserDetailsService(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Value("${main.security.enabled}")
    public void setEnabled(boolean enabled) {
        WebSecurityConfig.enabled = enabled;
    }
    public static boolean isEnabled() {
        return enabled;
    }

    @Value("${main.security.cors.origins}")
    public void setCorsOrigins(String[] corsOrigins) {
        WebSecurityConfig.corsOrigins = corsOrigins;
    }

    public static String[] getCorsOrigins() {
        return corsOrigins;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

/*    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }*/



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        if (enabled) {
            http.cors().and().csrf().disable()
//                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeHttpRequests((requests) -> requests
                            .requestMatchers("/actuator/**")
                            .access(new WebExpressionAuthorizationManager(WebSecurityConfig.SECURITY_ACCESS_HAS_ROLE_ADMIN + " or hasIpAddress('127.0.0.1')" + " or hasIpAddress('10.0.0.0/8')"))
                            .requestMatchers("/**").permitAll()
                            .anyRequest().authenticated()
                    );
            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        } else {
            http.cors().and().csrf().disable()
                    .authorizeHttpRequests()
                    .anyRequest().permitAll();
        }
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(corsOrigins));
        configuration.setAllowedMethods(Arrays.asList(RequestMethod.GET.name(), RequestMethod.POST.name(), RequestMethod.PUT.name(), RequestMethod.DELETE.name(), RequestMethod.OPTIONS.name()));
        configuration.setAllowedHeaders(Arrays.asList("content-type", "authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
