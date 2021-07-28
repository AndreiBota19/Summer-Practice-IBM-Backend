package com.example.checkin.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlannerService {
    private final PlannerRepository plannerRepository;

    @Autowired
    public PlannerService(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    public List<Planner> findAllPlanners(){
        return plannerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

}
