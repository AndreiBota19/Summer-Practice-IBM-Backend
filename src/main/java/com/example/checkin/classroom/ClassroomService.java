package com.example.checkin.classroom;

import com.example.checkin.feature.Feature;
import com.example.checkin.feature.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void addClassroom(Classroom classroom){
        Optional<Classroom> classroomOptional = classroomRepository.findClassroomByName(classroom.getName());
        if (classroomOptional.isPresent()){
            throw new IllegalStateException("Classroom with name: " + classroom.getName() + " already exists");
        }
        classroomRepository.save(classroom);
    }

    public void deleteClassroom(Long id){
        if (classroomRepository.existsById(id)){
            classroomRepository.deleteClassroomById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom with id: " + id + " not found!");
        }
    }

    public Classroom findClassroomById(Long id){
        return  classroomRepository.findClassroomById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom with id: " + id + " not found!")
        );
    }

    public List<ClassroomDTO> findAllClassrooms(){
        List<Classroom> classrooms = classroomRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return mapEntitiesToDTO(classrooms);
    }


    public void updateClassroom(Long id, Classroom updatedClassroom){
        Classroom classroom = classroomRepository.findClassroomById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom with id: " + id + " not found!")
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

    public Set<Long> getClassroomFeatures(Long classroomId){
        Classroom classroom = classroomRepository.findClassroomById(classroomId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom with id: "+ classroomId + " not found!")
        );
        return classroom.getFeatures();
    }

    public ClassroomDTO mapEntityToDto(Classroom classroom){
        ClassroomDTO classroomDto = new ClassroomDTO();
        classroomDto.setId(classroom.getId());
        classroomDto.setName(classroom.getName());
        return classroomDto;
    }

    public List<ClassroomDTO> mapEntitiesToDTO(List<Classroom> classrooms){
        return classrooms.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    public void assignFeatureToClassroom(Long classroomId, Long featureId){
        Classroom classroom = classroomRepository.findClassroomById(classroomId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom with id: " + classroomId + " not found!")
        );
        Feature feature = featureRepository.findFeatureById(featureId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feature with id: " + featureId + " not found!")
        );

        classroom.assignFeature(feature);
        classroomRepository.save(classroom);
    }

    public void partialUpdateClassroom(Long classroomId, Map<String, Object> request) {
        Classroom classroom = classroomRepository.findClassroomById(classroomId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom with id: " + classroomId + " not found!")
        );
        request.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Classroom.class, k);
            if (field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, classroom, v);
            }
        });
        classroomRepository.save(classroom);
    }
}