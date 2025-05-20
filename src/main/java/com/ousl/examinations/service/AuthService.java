package com.ousl.examinations.service;

import com.ousl.examinations.payload.request.LoginRequest;
import com.ousl.examinations.payload.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
