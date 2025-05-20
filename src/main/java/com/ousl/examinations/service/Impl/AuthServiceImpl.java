package com.ousl.examinations.service.Impl;

import com.ousl.examinations.model.User;
import com.ousl.examinations.payload.request.LoginRequest;
import com.ousl.examinations.payload.response.LoginResponse;
import com.ousl.examinations.repository.UserRepository;
import com.ousl.examinations.service.AuthService;
import com.ousl.examinations.service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthServiceImpl implements AuthService {

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authManager;

    public AuthServiceImpl(JWTService jwtService, UserRepository userRepository, AuthenticationManager authManager) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authManager = authManager;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        try{
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                String token =  jwtService.generateToken(loginRequest.getUsername(), new HashMap<>());
                User user = userRepository.findByUsername(loginRequest.getUsername());
                user.setPassword(null);
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setToken(token);
                loginResponse.setUser(user);
                return loginResponse;
            } else {
                return new LoginResponse();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getClass());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getStackTrace());
            return new LoginResponse();
        }
    }



}
