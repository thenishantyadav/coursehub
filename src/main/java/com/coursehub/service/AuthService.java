package com.coursehub.service;

import com.coursehub.dto.AuthResponseDto;
import com.coursehub.dto.LoginRequestDto;
import com.coursehub.dto.SignupRequestDto;

public interface AuthService {

    AuthResponseDto signup(SignupRequestDto requestDto);

    AuthResponseDto login(LoginRequestDto requestDto);
}
