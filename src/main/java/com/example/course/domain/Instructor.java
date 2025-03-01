package com.example.course.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("INSTRUCTOR")
public class Instructor extends User {

    @ElementCollection
    @CollectionTable(name = "instructor_specializations", joinColumns = @JoinColumn(name = "instructor_id"))
    @Column(name = "specialization")
    private List<String> specializations;
    @OneToMany(mappedBy = "instructor")
    private Set<Course> courses = new HashSet<>();
    private Double rating;
    private Long totalStudents;
    private Long totalReviews;
    private Long totalCourses;

    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Long getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(Long totalReviews) {
        this.totalReviews = totalReviews;
    }

    public Long getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(Long totalCourses) {
        this.totalCourses = totalCourses;
    }
}
