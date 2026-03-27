package com.coursehub.repository;

import com.coursehub.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    //<List<Course> findByTitleContainingIgnoreCase(String title);
}
