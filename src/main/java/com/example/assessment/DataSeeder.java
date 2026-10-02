package com.example.assessment;

import com.example.assessment.model.*;
import com.example.assessment.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.UUID;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   AssessmentRepository assessmentRepository,
                                   AssignmentRepository assignmentRepository,
                                   SubmissionRepository submissionRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User superAdmin = new User();
                superAdmin.setUsername("tejeshwar");
                superAdmin.setPassword(com.example.assessment.controller.UserController.hashPassword("tejeshwar"));
                superAdmin.setEmail("tejeshwar@gmail.com");
                superAdmin.setRole("Admin");
                userRepository.save(superAdmin);
                
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(com.example.assessment.controller.UserController.hashPassword("admin456"));
                admin.setEmail("admin@example.com");
                admin.setRole("Admin");
                userRepository.save(admin);
                
                User student = new User();
                student.setUsername("student");
                student.setPassword(com.example.assessment.controller.UserController.hashPassword("student456"));
                student.setEmail("student@example.com");
                student.setRole("student");
                User savedStudent = userRepository.save(student);

                User educator = new User();
                educator.setUsername("educator");
                educator.setPassword(com.example.assessment.controller.UserController.hashPassword("password123"));
                educator.setEmail("educator@example.com");
                educator.setRole("Educator");
                userRepository.save(educator);

                // Create Sample Assessment
                Assessment assessment = new Assessment();
                assessment.setTitle("Introduction to Programming");
                assessment.setCategory("Computer Science");
                assessment.setQuestions(2);
                assessment.setTimeLimit(30);
                assessment.setStatus("Published");

                QuestionData q1 = new QuestionData();
                q1.setId("q1-" + UUID.randomUUID().toString());
                q1.setText("What does HTML stand for?");
                q1.setOptions(Arrays.asList("Hyper Text Markup Language", "High Text Markup Language", "Hyper Tabular Markup Language", "None of these"));
                q1.setCorrect("Hyper Text Markup Language");

                QuestionData q2 = new QuestionData();
                q2.setId("q2-" + UUID.randomUUID().toString());
                q2.setText("Which language is used for styling web pages?");
                q2.setOptions(Arrays.asList("HTML", "JQuery", "CSS", "XML"));
                q2.setCorrect("CSS");

                assessment.setQuestionsData(Arrays.asList(q1, q2));
                Assessment savedAssessment = assessmentRepository.save(assessment);

                // Create Sample Assignment
                Assignment assignment = new Assignment();
                assignment.setId("assign-" + UUID.randomUUID().toString());
                assignment.setAssessmentId(savedAssessment.getId());
                assignment.setGroup("Class 2026");
                assignment.setDueDate("2026-12-31");
                assignment.setCompleted(1);
                assignment.setTotal(30);
                Assignment savedAssignment = assignmentRepository.save(assignment);

                // Create Sample Submission
                Submission submission = new Submission();
                submission.setId("sub-" + UUID.randomUUID().toString());
                submission.setAssignmentId(savedAssignment.getId());
                submission.setUserId(String.valueOf(savedStudent.getId()));
                submission.setUserName(savedStudent.getUsername());
                submission.setAssessmentTitle(savedAssessment.getTitle());
                submission.setScore(100);
                submission.setSubmittedAt("2026-10-01T10:00:00Z");
                submission.setStatus("Submitted");
                submission.setFeedback("Excellent work!");

                AnswerData a1 = new AnswerData();
                a1.setQuestionId(q1.getId());
                a1.setAnswer("Hyper Text Markup Language");
                a1.setCorrect(true);

                AnswerData a2 = new AnswerData();
                a2.setQuestionId(q2.getId());
                a2.setAnswer("CSS");
                a2.setCorrect(true);

                submission.setAnswers(Arrays.asList(a1, a2));
                submissionRepository.save(submission);
            }
        };
    }
}
