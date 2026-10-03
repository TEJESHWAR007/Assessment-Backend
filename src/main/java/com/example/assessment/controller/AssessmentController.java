package com.example.assessment.controller;

import com.example.assessment.model.Assessment;
import com.example.assessment.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessments")
@CrossOrigin(origins = "*")
public class AssessmentController {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @GetMapping
    public List<Assessment> getAll() {
        return assessmentRepository.findAll();
    }

    
    @GetMapping("/{id}")
    public Assessment getById(@PathVariable String id) {
        return assessmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Assessment not found"));
    }

    @PostMapping
    public Assessment create(@RequestBody Assessment assessment) {
        return assessmentRepository.save(assessment);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        assessmentRepository.deleteById(id);
    }
}

