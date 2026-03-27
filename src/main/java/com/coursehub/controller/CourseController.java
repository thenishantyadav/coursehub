package com.coursehub.controller;

import com.coursehub.dto.ApiResponse;
import com.coursehub.dto.CourseRequestDto;
import com.coursehub.dto.CourseResponseDto;
import com.coursehub.service.CourseService;
import java.time.LocalDateTime;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponseDto>>> getAllCourses() {
        ApiResponse<List<CourseResponseDto>> response = ApiResponse.<List<CourseResponseDto>>builder()
                .success(true)
                .message("Courses fetched successfully")
                .data(courseService.getAllCourses())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponseDto>> getCourseById(@PathVariable Long id) {
        ApiResponse<CourseResponseDto> response = ApiResponse.<CourseResponseDto>builder()
                .success(true)
                .message("Course fetched successfully")
                .data(courseService.getCourseById(id))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponseDto>> createCourse(@Valid @RequestBody CourseRequestDto requestDto) {
        ApiResponse<CourseResponseDto> response = ApiResponse.<CourseResponseDto>builder()
                .success(true)
                .message("Course created successfully")
                .data(courseService.createCourse(requestDto))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
