package com.example.checkin.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    ClassRepository classRepository;
    @Autowired
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }
    public void deleteClass(Long id)
    {
        classRepository.deleteClassById(id);
    }
    public Class addClass(Class cls)
    {
        return classRepository.save(cls);
    }
    public Class findClassById(Long id)
    {
        return classRepository.findClassById(id).orElseThrow(() -> new IllegalStateException("class with id: " + id +" was not found"));
    }
    public Class updateClass(Class cls)
    {
        return classRepository.save(cls);
    }


}
