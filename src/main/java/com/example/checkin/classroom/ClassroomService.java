package com.example.checkin.classroom;

import com.example.checkin.course.Course;
import com.example.checkin.course.CourseRepository;
import com.example.checkin.feature.Feature;
import com.example.checkin.feature.FeatureRepository;
import com.example.checkin.planner.Planner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final FeatureRepository featureRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository, FeatureRepository featureRepository, CourseRepository courseRepository) {
        this.classroomRepository = classroomRepository;
        this.featureRepository = featureRepository;
        this.courseRepository = courseRepository;
    }

    public Classroom addClassroom(Classroom classroom){
        Optional<Classroom> classroomOptional = classroomRepository.findClassroomByName(classroom.getName());
        if (classroomOptional.isPresent()){
            throw new IllegalStateException("Classroom with name: " + classroom.getName() + " already exists");
        }

        return  classroomRepository.save(classroom);
    }

    public void deleteClassroom(Long id){
        if (classroomRepository.existsById(id)){
            classroomRepository.deleteClassroomById(id);
        }
        else {
            throw new IllegalStateException("Classroom with id: " + id + " not found!");
        }
    }

    public ClassroomDTO findClassroomById(Long id){
        Classroom classroom = classroomRepository.findClassroomById(id).orElseThrow(
                () -> new IllegalStateException("Classroom with id: " + id + " not found!")
        );
        return mapEntityToDto(classroom);
    }

    public List<ClassroomDTO> findAllClassrooms(){
        List<Classroom> classrooms = classroomRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return mapEntitiesToDTO(classrooms);
    }

    public void updateClassroom(Long id, Classroom updatedClassroom){
        Classroom classroom = classroomRepository.findClassroomById(id).orElseThrow(
                () -> new IllegalStateException("Classroom with id: " + id + " not found!")
        );
        if (updatedClassroom.getName().length() > 0 && !(updatedClassroom.getName().equals(classroom.getName()))){
            classroom.setName(updatedClassroom.getName());
        }
        if (updatedClassroom.getLocation().length() > 0 && !(updatedClassroom.getLocation().equals(classroom.getLocation()))){
            classroom.setLocation(updatedClassroom.getLocation());
        }
        if (updatedClassroom.getCapacity() > 0 && !updatedClassroom.getCapacity().equals(classroom.getCapacity())){
            classroom.setCapacity(updatedClassroom.getCapacity());
        }

    }

    public void assignFeatureToClassroom(Long classroomId, Long featureId){
        Classroom classroom = classroomRepository.findClassroomById(classroomId).orElseThrow(
                () -> new IllegalStateException("Classroom with id: " + classroomId + " not found!")
        );
        Feature feature = featureRepository.findFeatureById(featureId).orElseThrow(
                () -> new IllegalStateException("Classroom with id: " + featureId + " not found!")
        );
        classroom.assignFeature(feature);
        classroomRepository.save(classroom);
    }

    public Set<Feature> getClassroomFeatures(Long classroomId){
        Classroom classroom = classroomRepository.findClassroomById(classroomId).orElseThrow(
                () -> new IllegalStateException("Classroom with id: "+ classroomId + " not found!")
        );
        return classroom.getFeatures();
    }

    public ClassroomDTO mapEntityToDto(Classroom classroom){
        ClassroomDTO classroomDto = new ClassroomDTO();
        classroomDto.setId(classroom.getId());
        classroomDto.setName(classroom.getName());
        classroomDto.setLocation(classroom.getLocation());
        classroomDto.setCapacity(classroom.getCapacity());
        classroomDto.setFeatures(getFeatureIds(classroom.getFeatures()));
        return classroomDto;
    }

    public List<ClassroomDTO> mapEntitiesToDTO(List<Classroom> classrooms){
        return classrooms.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    public Set<Long> getFeatureIds(Set<Feature> features){
        Set<Long> featureIds = new HashSet<>();
        for (Feature feature: features){
            featureIds.add(feature.getId());
        }
        return featureIds;
    }

    public void assignCourseToClassroom(String time, Long classroomId, Long courseId) {
        Classroom classroom = classroomRepository.findClassroomById(classroomId).orElseThrow(
                () -> new IllegalStateException("Classroom with id: "+ classroomId + " not found!")
        );
        Course course = courseRepository.findCourseById(courseId).orElseThrow(
                () -> new IllegalStateException("Course with id: "+ courseId + " not found!")
        );
        Planner planner = new Planner(time, course, classroom);

        classroom.addPlanner(planner);
        classroomRepository.save(classroom);

    }
}
