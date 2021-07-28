package com.example.checkin.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {
    CourseRepository courseRepository;
    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public void deleteCourse(Long id) {
        if (courseRepository.existsById(id)){
            courseRepository.deleteCourseById(id);
        }
        else {
            throw new IllegalStateException("class with id: " + id + " was not found!");
        }
    }
    public Course addCourse(Course cls)
    {
        return courseRepository.save(cls);
    }
    public Course findCourseById(Long id)
    {
        return courseRepository.findCourseById(id).orElseThrow(() -> new IllegalStateException("class with id: " + id +" was not found"));
    }

    public void updateCourse(Long id, Course updatedCourse){
        Course cls = courseRepository.findCourseById(id).orElseThrow(
                () -> new IllegalStateException("class with id: " + id + " was not found!")
        );
        if (updatedCourse.getName().length() > 0 && !(updatedCourse.getName().equals(cls.getName()))){
            cls.setName(updatedCourse.getName());
        }
        if (updatedCourse.getTeacher().length() > 0 && !(updatedCourse.getTeacher().equals(cls.getTeacher()))){
            cls.setTeacher(updatedCourse.getTeacher());
        }
        if (updatedCourse.getYear() > 0 && updatedCourse.getYear() != cls.getYear()){
            cls.setYear(updatedCourse.getYear());
        }

    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
