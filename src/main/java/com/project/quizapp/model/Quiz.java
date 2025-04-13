package com.project.quizapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Quiz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToMany
    @JoinTable(
        name = "quiz_questions", // Name of the join table
        joinColumns = @JoinColumn(name = "quiz_id"), // Foreign key for Quiz
        inverseJoinColumns = @JoinColumn(name = "questions_id") // Foreign key for Question
    )
    @JsonIgnore
    private List<Question> questions;

    
}
