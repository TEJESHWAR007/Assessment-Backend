package com.example.assessment.repository;

import com.example.assessment.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, String> {
}
