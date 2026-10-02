package com.example.assessment.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "questions_data")
public class QuestionData {

    @Id
    private String id;
    
    private String text;

    @ElementCollection
    @CollectionTable(name="question_options", joinColumns=@JoinColumn(name="question_id"))
    @Column(name="option_val")
    private List<String> options;

    private String correct;
}
