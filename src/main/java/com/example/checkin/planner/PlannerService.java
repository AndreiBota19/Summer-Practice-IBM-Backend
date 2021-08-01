package com.example.checkin.planner;

import com.example.checkin.user.User;
import com.example.checkin.user.UserRepository;
import com.example.checkin.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlannerService {
    private final PlannerRepository plannerRepository;
    private final UserRepository userRepository;

    @Autowired
    public PlannerService(PlannerRepository plannerRepository, UserRepository userRepository) {
        this.plannerRepository = plannerRepository;
        this.userRepository = userRepository;
    }

    public List<Planner> findAllPlanners(){
        return plannerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public void assignStudentToPlanner(Long plannerId, Long userId) {
        Planner planner = plannerRepository.findPlannerById(plannerId).orElseThrow(
                () -> new IllegalStateException("Planner with id: " + plannerId + " not found!")
        );
        User user = userRepository.findUserById(userId).orElseThrow(
                () -> new IllegalStateException("User with id: " + userId + " not found!")
        );
        if ((user.getRole().equals(UserRole.STUDENT) &&
                planner.getRemainingPlaces()>0)){
            if (!(planner.getEnrolledStudents().contains(user.getId()))){
                planner.assignStudent(user);
                planner.setRemainingPlaces(planner.getRemainingPlaces()-1);
                plannerRepository.save(planner);
            }
        }
        else throw new IllegalStateException("Only students are allowed to enroll");
    }

    public PlannerDTO mapEntityToDto(Planner planner){
        PlannerDTO plannerDto = new PlannerDTO();
        plannerDto.setId(planner.getId());
        plannerDto.setTime(planner.getTime());
        plannerDto.setCourseId(planner.getCourse().getId());
        plannerDto.setClassroomId(planner.getClassroom().getId());
        plannerDto.setRemainingPlaces(planner.getRemainingPlaces());
        return plannerDto;
    }

    public List<PlannerDTO> mapEntitiesToDTO(List<Planner> planners){
        return planners.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

}
