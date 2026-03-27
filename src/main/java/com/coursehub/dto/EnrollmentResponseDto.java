package com.coursehub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseDto {

    private Long id;
    private Long userId;
    private Long courseId;
    private String courseTitle;
    private String courseDescription;
    private String instructorName;
    private Integer progress;
}
