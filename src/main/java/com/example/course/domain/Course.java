package com.example.course.domain;

import com.example.course.enums.CourseLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Course extends BaseEntity {

    private String title;
    private String description;
    private String thumbnailUrl;;
    private BigDecimal price;
    private String duration;
    private String language;
    private LocalDate publishedDate;
    private CourseLevel level;
    private Double rating;
    private Long totalStudents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


}























