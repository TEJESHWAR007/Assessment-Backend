package com.example.assessment.controller;

import com.example.assessment.model.Assignment;
import com.example.assessment.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@CrossOrigin(origins = "*")
public class AssignmentController {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @GetMapping
    public List<Assignment> getAll() {
        return assignmentRepository.findAll();
    }

    @PostMapping
    public Assignment create(@RequestBody Assignment assignment) {
        return assignmentRepository.save(assignment);
    }
    
    @PatchMapping("/{id}")
    public Assignment updateProgress(@PathVariable String id, @RequestBody java.util.Map<String, Object> updates) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow();
        if (updates.containsKey("completed")) {
            assignment.setCompleted(((Number) updates.get("completed")).intValue());
        }
        if (updates.containsKey("total")) {
            assignment.setTotal(((Number) updates.get("total")).intValue());
        }
        return assignmentRepository.save(assignment);
    }
}
