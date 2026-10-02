package com.example.assessment.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    
    private String username;
    private String password;
    private String email;
    private String role;
}
