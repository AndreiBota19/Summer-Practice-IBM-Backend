package com.example.checkin.user;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public User findUserById(Long id){
        return userRepository.findUserById(id).orElseThrow(() -> new IllegalStateException("user with id: " + id +" was not found"));
    }

    public List<User> findAllGuests(){
        return userRepository.findAllByRole(UserRole.GUEST);
    }

    public List<User> findAllStudents(){
        return userRepository.findAllByRole(UserRole.STUDENT);
    }

    public List<User> findAllTeachers(){
        return userRepository.findAllByRole(UserRole.TEACHER);
    }

    public List<User> findAllAdmins(){
        return userRepository.findAllByRole(UserRole.ADMIN);
    }


}
