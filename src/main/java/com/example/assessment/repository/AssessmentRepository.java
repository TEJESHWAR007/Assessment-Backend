package com.example.assessment.repository;

import com.example.assessment.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, String> {
}
