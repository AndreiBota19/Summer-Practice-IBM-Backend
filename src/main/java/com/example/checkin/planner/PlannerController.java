package com.example.checkin.planner;

import com.example.checkin.user.User;
import com.example.checkin.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/planner")
public class PlannerController {

    private final PlannerService plannerService;

    public PlannerController(PlannerService plannerService, PlannerRepository plannerRepository, UserRepository userRepository) {
        this.plannerService = plannerService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<PlannerDTO>> getAllPlanners(){
        List<PlannerDTO> planners = plannerService.findAllPlanners();
        return new ResponseEntity<>(planners, HttpStatus.OK);
    }

    @PutMapping(path = "{plannerId}/user/{userId}")
    public void assignUserToPlanner(@PathVariable Long plannerId, @PathVariable Long userId){
        plannerService.assignUserToPlanner(plannerId, userId);
    }

}
