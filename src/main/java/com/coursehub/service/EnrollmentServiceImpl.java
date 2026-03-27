package com.coursehub.service;

import com.coursehub.dto.EnrollmentProgressUpdateDto;
import com.coursehub.dto.EnrollmentRequestDto;
import com.coursehub.dto.EnrollmentResponseDto;
import com.coursehub.entity.Course;
import com.coursehub.entity.Enrollment;
import com.coursehub.entity.User;
import com.coursehub.exception.DuplicateResourceException;
import com.coursehub.exception.ResourceNotFoundException;
import com.coursehub.repository.CourseRepository;
import com.coursehub.repository.EnrollmentRepository;
import com.coursehub.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public EnrollmentResponseDto enrollStudent(Long userId, EnrollmentRequestDto requestDto) {
        if (enrollmentRepository.existsByUserIdAndCourseId(userId, requestDto.getCourseId())) {
            throw new DuplicateResourceException("Student is already enrolled in this course");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Course course = courseRepository.findById(requestDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + requestDto.getCourseId()));

        Enrollment enrollment = Enrollment.builder()
                .user(user)
                .course(course)
                .progress(0)
                .build();

        return mapToResponseDto(enrollmentRepository.save(enrollment));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponseDto> getEnrolledCoursesByUserId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return enrollmentRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public EnrollmentResponseDto updateProgress(Long enrollmentId, Long userId, EnrollmentProgressUpdateDto requestDto) {
        Enrollment enrollment = enrollmentRepository.findByIdAndUserId(enrollmentId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));

        enrollment.setProgress(requestDto.getProgress());
        return mapToResponseDto(enrollmentRepository.save(enrollment));
    }

    private EnrollmentResponseDto mapToResponseDto(Enrollment enrollment) {
        return EnrollmentResponseDto.builder()
                .id(enrollment.getId())
                .userId(enrollment.getUser().getId())
                .courseId(enrollment.getCourse().getId())
                .courseTitle(enrollment.getCourse().getTitle())
                .courseDescription(enrollment.getCourse().getDescription())
                .instructorName(enrollment.getCourse().getInstructorName())
                .progress(enrollment.getProgress())
                .build();
    }
}
