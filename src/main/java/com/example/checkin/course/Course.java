package com.example.checkin.course;

import com.example.checkin.planner.Planner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({ "planners" })
public class Course {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String teacher;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private String section;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Planner> planners = new HashSet<>();

    public Course() {
    }

    public Course(String name, String teacher, int year, String section) {
        this.name = name;
        this.teacher = teacher;
        this.year = year;
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Set<Planner> getPlanners() {
        return planners;
    }

    public void setPlanners(Set<Planner> planners) {
        this.planners = planners;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", year=" + year +
                ", section='" + section + '\'' +
                ", planners=" + planners +
                '}';
    }
}
