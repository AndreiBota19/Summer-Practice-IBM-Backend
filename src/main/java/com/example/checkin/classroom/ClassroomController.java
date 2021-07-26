package com.example.checkin.classroom;

import com.example.checkin.feature.Feature;
import com.example.checkin.feature.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService, ClassroomRepository classroomRepository, FeatureRepository featureRepository) {
        this.classroomService = classroomService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Classroom> registerClassroom(@RequestBody Classroom classroom){
        Classroom cr = classroomService.addClassroom(classroom);
        return new ResponseEntity<>(cr, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Classroom>> getAllClassrooms(){
        List<Classroom> classroomList = classroomService.findAllClassrooms();
        return new ResponseEntity<>(classroomList, HttpStatus.OK);
    }

    @GetMapping(path = "/{classroomId}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable("classroomId") Long classroomId){
        Classroom classroom = classroomService.findClassroomById(classroomId);
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

}
