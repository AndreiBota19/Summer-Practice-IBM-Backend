package com.example.checkin.classroom;

import com.example.checkin.feature.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Classroom> registerClassroom(@RequestBody Classroom classroom){
        Classroom cr = classroomService.addClassroom(classroom);
        return new ResponseEntity<>(cr, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ClassroomDTO>> getAllClassrooms(){
        List<ClassroomDTO> classrooms = classroomService.findAllClassrooms();
        return new ResponseEntity<>(classrooms, HttpStatus.OK);
    }

    @GetMapping(path = "/{classroomId}")
    public ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable("classroomId") Long classroomId){
        ClassroomDTO classroom = classroomService.findClassroomById(classroomId);
        return new ResponseEntity<>(classroom, HttpStatus.OK);
    }

    @PutMapping(path = "/edit/{classroomId}")
    public void editClassroom(@PathVariable("classroomId") Long classroomId, @RequestBody Classroom classroom){
        classroomService.updateClassroom(classroomId, classroom);
    }

    @DeleteMapping(path = "/delete/{classroomId}")
    public void deleteClassroom(@PathVariable("classroomId") Long classroomId){
        classroomService.deleteClassroom(classroomId);
    }

    @PutMapping(path = "{classroomId}/feature/{featureId}")
    public void assignFeatureToClassroom(@PathVariable Long classroomId, @PathVariable Long featureId){
        classroomService.assignFeatureToClassroom(classroomId, featureId);
    }

    @GetMapping(path = "{classroomId}/features")
    public ResponseEntity<Set<Feature>> getFeatures(@PathVariable Long classroomId){
        Set<Feature> features = classroomService.getClassroomFeatures(classroomId);
        return new ResponseEntity<>(features, HttpStatus.OK);
    }

    @PostMapping(path = "{classroomId}/course/{courseId}")
    public void assignClassToClassroom(
            @PathVariable("classroomId") Long classroomId,
            @PathVariable("courseId") Long courseId,
            @RequestBody String time){
        classroomService.assignCourseToClassroom(time, classroomId, courseId);
    }

}
