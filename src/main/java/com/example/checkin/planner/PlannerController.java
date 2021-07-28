package com.example.checkin.planner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/planner")
public class PlannerController {

    private final PlannerService plannerService;

    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Planner>> getAllPlanners(){
        List<Planner> planners = plannerService.findAllPlanners();
        return new ResponseEntity<>(planners, HttpStatus.OK);
    }

}
