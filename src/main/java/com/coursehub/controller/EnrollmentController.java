package com.coursehub.controller;

import com.coursehub.dto.ApiResponse;
import com.coursehub.dto.EnrollmentProgressUpdateDto;
import com.coursehub.dto.EnrollmentRequestDto;
import com.coursehub.dto.EnrollmentResponseDto;
import com.coursehub.entity.User;
import com.coursehub.service.EnrollmentService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<EnrollmentResponseDto>> enrollInCourse(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody EnrollmentRequestDto requestDto
    ) {
        ApiResponse<EnrollmentResponseDto> response = ApiResponse.<EnrollmentResponseDto>builder()
                .success(true)
                .message("Enrollment created successfully")
                .data(enrollmentService.enrollStudent(currentUser.getId(), requestDto))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #userId")
    public ResponseEntity<ApiResponse<List<EnrollmentResponseDto>>> getEnrolledCourses(@PathVariable Long userId) {
        ApiResponse<List<EnrollmentResponseDto>> response = ApiResponse.<List<EnrollmentResponseDto>>builder()
                .success(true)
                .message("Enrolled courses fetched successfully")
                .data(enrollmentService.getEnrolledCoursesByUserId(userId))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{enrollmentId}/progress")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<EnrollmentResponseDto>> updateProgress(
            @PathVariable Long enrollmentId,
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody EnrollmentProgressUpdateDto requestDto
    ) {
        ApiResponse<EnrollmentResponseDto> response = ApiResponse.<EnrollmentResponseDto>builder()
                .success(true)
                .message("Enrollment progress updated successfully")
                .data(enrollmentService.updateProgress(enrollmentId, currentUser.getId(), requestDto))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
