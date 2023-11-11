package com.ead.authservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.ead.authservice.User.Permission.*;
import static com.ead.authservice.User.Role.ADMIN;
import static com.ead.authservice.User.Role.CUSTOMER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/api/auth/**")
                .permitAll()

                .antMatchers("/api/auth/customer/**").hasAnyRole(ADMIN.name(), CUSTOMER.name())

                .antMatchers(GET, "/api/auth/customer/**").hasAnyAuthority(ADMIN_READ.name(), CUSTOMER_READ.name())
                .antMatchers(POST, "/api/auth/customer/**").hasAnyAuthority(ADMIN_CREATE.name(), CUSTOMER_CREATE.name())
                .antMatchers(PUT, "/api/auth/customer/**").hasAnyAuthority(ADMIN_UPDATE.name(), CUSTOMER_UPDATE.name())
                .antMatchers(DELETE, "/api/auth/customer/**").hasAnyAuthority(ADMIN_DELETE.name(), CUSTOMER_DELETE.name())

//                .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
//                .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
//                .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
//                .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
//                .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(
                        (request, response, authentication) ->
                                SecurityContextHolder.clearContext()
                );
        return http.build();
    }
}
