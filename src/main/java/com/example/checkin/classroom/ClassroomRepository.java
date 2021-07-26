package com.example.checkin.classroom;

import com.example.checkin.classroom.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    void deleteClassroomById(Long id);

    Optional<Classroom> findClassroomById(Long id);

    Optional<Classroom> findClassroomByName(String name);

}
