package com.project.quizapp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.quizapp.exception.GlobalExceptionHandler.ResourceNotFoundException;
import com.project.quizapp.model.Question;
import com.project.quizapp.repo.QuestionRepo;

@Service
public class QuestionService {
    
    @Autowired
    QuestionRepo questionRepo;

    public List<Question> getAllQuestions(){
        return questionRepo.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
       
       return questionRepo.findByCategory(category);
    }

    public String addQuestion(Question question) {
        try{
            questionRepo.save(question);
            return "Question added successfully!";

        } catch(Exception e){
            throw new RuntimeException("Failed to add question: "+e);
        }
    }

    public ResponseEntity<Question> getQuestionById(Integer id) {
        Question result = questionRepo.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Question not found with the given id: " + id));
        return ResponseEntity.ok(result);
    
    }

    public ResponseEntity<Question> updateQuestion(Integer id, Question entity) {
        Question result = questionRepo.findById(id).orElseThrow(()->new RuntimeException("Question not found with give id: "+id));
        
        result.setCategory(entity.getCategory());
        result.setDifficultyLevel(entity.getDifficultyLevel());
        result.setOption1(entity.getOption1());
        result.setOption2(entity.getOption2());
        result.setOption3(entity.getOption3());
        result.setOption4(entity.getOption4());
        result.setQuestionTitle(entity.getQuestionTitle());
        
        questionRepo.save(result);

        return ResponseEntity.ok(entity);
    
    }
}
