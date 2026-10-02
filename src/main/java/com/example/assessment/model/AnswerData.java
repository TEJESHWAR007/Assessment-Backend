package com.example.assessment.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "answers_data")
public class AnswerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String questionId;
    private String answer;
    private Boolean correct;
}
