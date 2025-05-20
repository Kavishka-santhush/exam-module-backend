package com.ousl.examinations.config;


import com.ousl.examinations.service.UserDetailsImplementation;
import com.ousl.examinations.service.JWTService;

import com.ousl.examinations.service.UserDetailsServiceImplementation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter  {

    private final JWTService jwtService;
    private final ApplicationContext context;
    private final UserDetailsServiceImplementation userDetailsServiceImplementation;

    public JwtFilter(JWTService jwtService, ApplicationContext context, UserDetailsServiceImplementation userDetailsServiceImplementation) {
        this.jwtService = jwtService;
        this.context = context;
        this.userDetailsServiceImplementation = userDetailsServiceImplementation;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
        }

        //if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        if (username != null) {
            UserDetails userDetails = userDetailsServiceImplementation.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);

    }
}
