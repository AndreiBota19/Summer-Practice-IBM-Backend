package com.example.checkin.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Course> registerCourse(@RequestBody Course course){
        Course course1= courseService.addCourse(course);
        return new ResponseEntity<>(course1 , HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseService.findAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId){
        Course course = courseService.findCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<?> deleteClass(@PathVariable("courseId") Long courseId)
    {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(path = "/edit/{courseId}")
    public void editCourse(@PathVariable("courseId") Long courseId, @RequestBody Course course){
        courseService.updateCourse(courseId, course);
    }

}
