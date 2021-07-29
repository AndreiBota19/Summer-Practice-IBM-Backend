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
    private Set<Long> enrolledUsers = new HashSet<>();
    private Integer capacity;

    public PlannerDTO() {
    }

    public PlannerDTO(Long id, String time, Long courseId, Long classroomId, Set<Long> enrolledUsers, Integer capacity) {
        this.id = id;
        this.time = time;
        this.courseId = courseId;
        this.classroomId = classroomId;
        this.enrolledUsers = enrolledUsers;
        this.capacity = capacity;
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

    public Set<Long> getEnrolledUsers() {
        return enrolledUsers;
    }

    public void setEnrolledUsers(Set<Long> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "PlannerDTO{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", courseId=" + courseId +
                ", classroomId=" + classroomId +
                ", enrolledUsers=" + enrolledUsers +
                ", capacity=" + capacity +
                '}';
    }
}
