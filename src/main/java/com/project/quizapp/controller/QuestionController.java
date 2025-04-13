package com.project.quizapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.quizapp.model.Question;
import com.project.quizapp.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("question")
public class QuestionController {
    
    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions(){

        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public List<Question> getMethodName(@RequestParam String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@RequestParam Integer id) {
        return questionService.getQuestionById(id);
    }
    
    
    @PostMapping("/add")
    public String addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Question> putMethodName(@PathVariable Integer id, @RequestBody Question entity) {
        
        return questionService.updateQuestion(id, entity);
        
    }
    
}
