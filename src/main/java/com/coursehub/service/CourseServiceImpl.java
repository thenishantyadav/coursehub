package com.coursehub.service;

import com.coursehub.dto.CourseRequestDto;
import com.coursehub.dto.CourseResponseDto;
import com.coursehub.entity.Course;
import com.coursehub.exception.ResourceNotFoundException;
import com.coursehub.repository.CourseRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<CourseResponseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public CourseResponseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        return mapToResponseDto(course);
    }

    @Override
    public CourseResponseDto createCourse(CourseRequestDto requestDto) {
        Course course = Course.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .instructorName(requestDto.getInstructorName())
                .build();

        Course savedCourse = courseRepository.save(course);
        return mapToResponseDto(savedCourse);
    }

    private CourseResponseDto mapToResponseDto(Course course) {
        return CourseResponseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .instructorName(course.getInstructorName())
                .build();
    }
}
