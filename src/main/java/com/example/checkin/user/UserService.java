package com.example.checkin.user;

import com.example.checkin.feature.Feature;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<UserDTO> findAllUsers(){
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return mapEntitiesToDTO(users);
    }

    public User findUserById(Long id){
        return userRepository.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id +" was not found"));
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

    public UserDTO mapEntityToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }

    public List<UserDTO> mapEntitiesToDTO(List<User> users){
        return users.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

}
