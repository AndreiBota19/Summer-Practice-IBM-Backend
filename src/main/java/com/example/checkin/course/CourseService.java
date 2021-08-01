package com.example.checkin.course;

import com.example.checkin.user.User;
import com.example.checkin.user.UserRepository;
import com.example.checkin.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }
    public void deleteCourse(Long id) {
        if (courseRepository.existsById(id)){
            courseRepository.deleteCourseById(id);
        }
        else {
            throw new IllegalStateException("Course with id: " + id + " was not found!");
        }
    }
    public Course addCourse(Course course) {
        Optional<Course> courseOptional = courseRepository.findCourseByName(course.getName());
        if (courseOptional.isPresent()){
            throw new IllegalStateException("Course with name: " + course.getName() + " already exists");
        }
        return courseRepository.save(course);
    }
    public Course findCourseById(Long id)
    {
        return courseRepository.findCourseById(id).orElseThrow(() -> new IllegalStateException("Course with id: " + id +" was not found"));
    }

    public void updateCourse(Long id, Course updatedCourse){
        Course cls = courseRepository.findCourseById(id).orElseThrow(
                () -> new IllegalStateException("Course with id: " + id + " was not found!")
        );
        if (updatedCourse.getName().length() > 0 && !(updatedCourse.getName().equals(cls.getName()))){
            cls.setName(updatedCourse.getName());
        }
        if (updatedCourse.getYear() > 0 && updatedCourse.getYear() != cls.getYear()){
            cls.setYear(updatedCourse.getYear());
        }
    }

    public List<CourseDTO> findAllCourses() {
        List<Course> courses = courseRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return mapEntitiesToDTO(courses);
    }

    public CourseDTO mapEntityToDto(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        return courseDTO;
    }

    public List<CourseDTO> mapEntitiesToDTO(List<Course> courses){
        return courses.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    public void assignTeacherToCourse(Long courseId, Long teacherId) {
        Course course = courseRepository.findCourseById(courseId).orElseThrow(
                () -> new IllegalStateException("Course with id: "+ courseId + " not found!")
        );
        User teacher = userRepository.findUserById(teacherId).orElseThrow(
                () -> new IllegalStateException("Teacher with id: "+ teacherId + " not found!")
        );
        if (teacher.getRole().equals(UserRole.TEACHER)) {
            course.setTeacher(teacher);
        }
        else {
            throw new IllegalStateException("User with id: " + teacherId + " is not a teacher");
        }
        courseRepository.save(course);
    }
}
