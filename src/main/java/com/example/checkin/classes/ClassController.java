package com.example.checkin.classes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Class> registerClass(@RequestBody Class cls){
        Class cl= classService.addClass(cls);
        return new ResponseEntity<>(cl , HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClass(@PathVariable("id") Long id)
    {
        classService.deleteClass(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Class> updateClass(@RequestBody Class cls)
    {
        Class cl=classService.updateClass(cls);
        return new ResponseEntity<>(cl,HttpStatus.OK);
    }

}
