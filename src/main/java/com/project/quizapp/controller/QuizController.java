package com.project.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.quizapp.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/quiz")
public class QuizController {
        
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestParam String category, @RequestParam int noOfQues, @RequestParam String title){
        return quizService.createQuiz(category, noOfQues, title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable Integer id) {
        return quizService.getQuizById(id);
    }
    
}
