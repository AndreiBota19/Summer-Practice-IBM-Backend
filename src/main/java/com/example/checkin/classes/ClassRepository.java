package com.example.checkin.classes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class,Long> {

    void deleteClassById(Long id);

    Optional<Class> findClassById(Long id);
}
