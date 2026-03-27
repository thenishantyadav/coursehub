package com.coursehub.service;

import com.coursehub.dto.CourseRequestDto;
import com.coursehub.dto.CourseResponseDto;
import com.coursehub.entity.Course;
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
    public CourseResponseDto createCourse(CourseRequestDto requestDto) {
        Course course = Course.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .instructor(requestDto.getInstructor())
                .build();

        Course savedCourse = courseRepository.save(course);
        return mapToResponseDto(savedCourse);
    }

    private CourseResponseDto mapToResponseDto(Course course) {
        return CourseResponseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .instructor(course.getInstructor())
                .build();
    }
}
