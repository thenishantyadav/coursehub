package com.coursehub.service;

import com.coursehub.dto.CourseRequestDto;
import com.coursehub.dto.CourseResponseDto;
import java.util.List;

public interface CourseService {

    List<CourseResponseDto> getAllCourses();

    CourseResponseDto getCourseById(Long id);

    CourseResponseDto createCourse(CourseRequestDto requestDto);
}
