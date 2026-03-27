package com.coursehub.controller;

import com.coursehub.dto.ApiResponse;
import com.coursehub.dto.AuthResponseDto;
import com.coursehub.dto.LoginRequestDto;
import com.coursehub.dto.SignupRequestDto;
import com.coursehub.service.AuthService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthResponseDto>> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        ApiResponse<AuthResponseDto> response = ApiResponse.<AuthResponseDto>builder()
                .success(true)
                .message("User registered successfully")
                .data(authService.signup(requestDto))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(@Valid @RequestBody LoginRequestDto requestDto) {
        ApiResponse<AuthResponseDto> response = ApiResponse.<AuthResponseDto>builder()
                .success(true)
                .message("Login successful")
                .data(authService.login(requestDto))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
