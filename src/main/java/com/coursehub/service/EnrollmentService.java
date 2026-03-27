package com.coursehub.service;

import com.coursehub.dto.EnrollmentProgressUpdateDto;
import com.coursehub.dto.EnrollmentRequestDto;
import com.coursehub.dto.EnrollmentResponseDto;
import java.util.List;

public interface EnrollmentService {

    EnrollmentResponseDto enrollStudent(Long userId, EnrollmentRequestDto requestDto);

    List<EnrollmentResponseDto> getEnrolledCoursesByUserId(Long userId);

    EnrollmentResponseDto updateProgress(Long enrollmentId, Long userId, EnrollmentProgressUpdateDto requestDto);
}
