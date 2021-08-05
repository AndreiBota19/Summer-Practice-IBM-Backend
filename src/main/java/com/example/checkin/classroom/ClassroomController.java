package com.example.checkin.classroom;

import com.example.checkin.feature.Feature;
import com.example.checkin.feature.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(path = "/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping()
    public ResponseEntity<Classroom> registerClassroom(@RequestBody Classroom classroom){
        classroomService.addClassroom(classroom);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ClassroomDTO>> getAllClassrooms(){
        List<ClassroomDTO> classrooms = classroomService.findAllClassrooms();
        return new ResponseEntity<>(classrooms, HttpStatus.OK);
    }

    @GetMapping(path = "/{classroomId}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable("classroomId") Long classroomId){
        Classroom classroom = classroomService.findClassroomById(classroomId);
        return new ResponseEntity<>(classroom, HttpStatus.OK);
    }

    @PutMapping(path = "/{classroomId}")
    public void editClassroom(
            @PathVariable("classroomId") Long classroomId,
            @RequestBody Classroom classroom){
        classroomService.updateClassroom(classroomId, classroom);
    }

    @DeleteMapping(path = "/{classroomId}")
    public void deleteClassroom(@PathVariable("classroomId") Long classroomId){
        classroomService.deleteClassroom(classroomId);
    }

    @PutMapping(path = "/{classroomId}/feature/{featureId}")
    public void assignFeatureToClassroom(
            @PathVariable Long classroomId,
            @PathVariable Long featureId){
        classroomService.assignFeatureToClassroom(classroomId, featureId);
    }

    @GetMapping(path = "/{classroomId}/features")
    public ResponseEntity<Set<Long>> getFeatures(@PathVariable Long classroomId){
        Set<Long> features = classroomService.getClassroomFeatures(classroomId);
        return new ResponseEntity<>(features, HttpStatus.OK);
    }

    @PatchMapping(path = "/{classroomId}")
    public void partialUpdateClassroom(
            @PathVariable("classroomId") Long classroomId,
            @RequestBody Map<String, Object> request){
        classroomService.partialUpdateClassroom(classroomId, request);
    }

}