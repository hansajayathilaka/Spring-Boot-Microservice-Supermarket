package com.ead.authservice.config;

import com.ead.authservice.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    //I did this a final
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            //Design pattern
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        //added
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

      final String authHeader = request.getHeader("Authorization");
      final String jwt;
      final String userEmail;
      //I added a !
      if (authHeader == null || !authHeader.startsWith("Bearer ")){
          filterChain.doFilter(request, response);
          return;
      }
      jwt = authHeader.substring(7);
      userEmail = jwtService.extractUsername(jwt);
      if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
          UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
          //checking the token is valid or not from the database
          var isTokenValid = tokenRepository.findByToken(jwt)
                  .map(t -> !t.isExpired() && !t.isRevoked())
                  .orElse(false);
          if(jwtService.isTokenValid(jwt,userDetails) && isTokenValid){
              UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                      userDetails,
                      null,
                      userDetails.getAuthorities()
              );
              authToken.setDetails(
                      new WebAuthenticationDetailsSource().buildDetails(request)
              );
              SecurityContextHolder.getContext().setAuthentication(authToken);
          }
      }
      filterChain.doFilter(request,response);
    }
}
