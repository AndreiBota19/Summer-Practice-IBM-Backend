package com.example.checkin.planner;

import com.example.checkin.classroom.Classroom;
import com.example.checkin.course.Course;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity

public class Planner {
    @Id
    @Column(name = "planner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    @JsonBackReference(value = "reference1")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classroom_id")
    @JsonBackReference(value = "reference2")
    private Classroom classroom;

    public Planner() {
    }

    public Planner(String time, Course course, Classroom classroom) {
        this.time = time;
        this.course = course;
        this.classroom = classroom;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Long getCourseId(){ return course.getId();}

    public void setCourseId(Long id){ course.setId(id);}

    public Long getClassroomId(){ return classroom.getId();}

    public void setClassroomId(Long id){ classroom.setId(id);}

    @Override
    public String toString() {
        return "Planner{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", course=" + course +
                ", classroom=" + classroom +
                '}';
    }

}