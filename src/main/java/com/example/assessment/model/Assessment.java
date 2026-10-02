package com.example.assessment.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "assessments")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String title;
    private String category;
    private Integer questions; // number of questions
    private Integer timeLimit;
    private String status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id")
    private List<QuestionData> questionsData;
}
