package com.example.assessment.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "submissions")
public class Submission {

    @Id
    private String id;
    
    private String assignmentId;
    private String userId;
    private String userName;
    private String assessmentTitle;
    private Integer score;
    private String submittedAt;
    private String status;
    private String feedback;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id")
    private List<AnswerData> answers;
}
