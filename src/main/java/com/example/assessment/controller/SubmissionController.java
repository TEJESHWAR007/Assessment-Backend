package com.example.assessment.controller;

import com.example.assessment.model.Submission;
import com.example.assessment.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/submissions")
@CrossOrigin(origins = "*")
public class SubmissionController {

    @Autowired
    private SubmissionRepository submissionRepository;

    @GetMapping
    public List<Submission> getAll(@RequestParam(required = false) String userId) {
        if (userId != null && !userId.isEmpty()) {
            return submissionRepository.findByUserId(userId);
        }
        return submissionRepository.findAll();
    }

    @PostMapping
    public Submission create(@RequestBody Submission submission) {
        return submissionRepository.save(submission);
    }
    
    @PatchMapping("/{id}")
    public Submission updateFeedback(@PathVariable String id, @RequestBody Map<String, String> updates) {
        Submission sub = submissionRepository.findById(id).orElseThrow();
        if (updates.containsKey("feedback")) {
            sub.setFeedback(updates.get("feedback"));
        }
        return submissionRepository.save(sub);
    }
}
