package com.example.checkin.classroom;

import com.example.checkin.feature.Feature;
import com.example.checkin.feature.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final FeatureRepository featureRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository, FeatureRepository featureRepository) {
        this.classroomRepository = classroomRepository;
        this.featureRepository = featureRepository;
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

    public Classroom findClassroomById(Long id){
        return classroomRepository.findClassroomById(id).orElseThrow(
                () -> new IllegalStateException("Classroom with id: " + id + " not found!")
        );
    }

    public List<Classroom> findAllClassrooms(){
        return classroomRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
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
        Optional<Classroom> classroomOptional = classroomRepository.findClassroomById(classroomId);
        Optional<Feature> featureOptional =  featureRepository.findFeatureById(featureId);
        if (classroomOptional.isPresent() && featureOptional.isPresent()){
            Classroom classroom = classroomRepository.getById(classroomId);
            Feature feature = featureRepository.getById(featureId);
            feature.assignClassroom(classroom);
        }
    }



}
