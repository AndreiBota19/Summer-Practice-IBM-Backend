package com.example.checkin.planner;

import com.example.checkin.classroom.Classroom;
import com.example.checkin.course.Course;
import com.example.checkin.user.User;

import java.util.HashSet;
import java.util.Set;

public class PlannerDTO {

    private Long id;
    private String time;
    private Long courseId;
    private Long classroomId;
    private Integer remainingPlaces;

    public PlannerDTO() {
    }

    public PlannerDTO(Long id, String time, Long courseId, Long classroomId, Integer remainingPlaces) {
        this.id = id;
        this.time = time;
        this.courseId = courseId;
        this.classroomId = classroomId;
        this.remainingPlaces = remainingPlaces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Integer getRemainingPlaces() {
        return remainingPlaces;
    }

    public void setRemainingPlaces(Integer remainingPlaces) {
        this.remainingPlaces = remainingPlaces;
    }

    @Override
    public String toString() {
        return "PlannerDTO{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", courseId=" + courseId +
                ", classroomId=" + classroomId +
                ", remainingPlaces=" + remainingPlaces +
                '}';
    }
}
