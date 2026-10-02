package com.example.assessment.repository;

import com.example.assessment.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, String> {
    List<Submission> findByUserId(String userId);
}
