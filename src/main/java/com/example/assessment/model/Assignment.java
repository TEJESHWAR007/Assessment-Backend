package com.example.assessment.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "assignments")
public class Assignment {

    @Id
    private String id;

    private String assessmentId;
    
    @Column(name = "`group`") // group is a reserved keyword in MySQL
    private String group;
    
    private String dueDate;
    private Integer completed;
    private Integer total;
}
