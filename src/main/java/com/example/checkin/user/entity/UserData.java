package com.example.checkin.user.entity;

import com.example.checkin.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserData implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public UserData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAllInBatch();

        User u1 = new User(UserRole.GUEST, "George", "Parker", 0, null, null, 0);
        User u2 = new User(UserRole.STUDENT, "John", "Adams", 2, "Computer Science", "English", 2);
        User u3 = new User(UserRole.TEACHER, "Thomas", "Jefferson", 0, "Computer Science", "English", 0);
        User u4 = new User(UserRole.ADMIN, "James", "Madison", 0, null, null, 0);

        List<User> userList = List.of(u1, u2, u3, u4);

        userRepository.saveAll(userList);

    }
}
