package com.project.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.exception.GlobalExceptionHandler;
import com.project.quizapp.exception.GlobalExceptionHandler.ResourceNotFoundException;
import com.project.quizapp.model.Question;
import com.project.quizapp.model.Quiz;
import com.project.quizapp.repo.QuestionRepo;
import com.project.quizapp.repo.QuizRepo;

@Service
public class QuizService {
    
    @Autowired
    QuestionRepo questionRepo;
    
    @Autowired
    QuizRepo quizRepo;

    public ResponseEntity<?> createQuiz(String category, int noOfQues, String title) {
       
        List<Question> questionList = new ArrayList<>();
        try{
            questionList = questionRepo.getQuiz(category, noOfQues);

        Quiz quiz = new Quiz();

        quiz.setQuestions(questionList);
        quiz.setTitle(title);

        quizRepo.save(quiz);
        
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
        } catch (GlobalExceptionHandler.ResourceNotFoundException e) {
        return new ResponseEntity<>("Resource not found!", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
        throw new GlobalExceptionHandler.DatabaseOperationException("Error occurred while creating quiz!", e);
    }
    }

    public ResponseEntity<?> getQuizById(Integer id) {
       
        Quiz quizSearch = quizRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Question not found with the given id: " + id));
        List<QuestionDto> quesForUsers = new ArrayList<>();

        for(Question q : quizSearch.getQuestions()){
            QuestionDto questionDto = new QuestionDto(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            quesForUsers.add(questionDto);
        }
        return new ResponseEntity<>(quesForUsers, HttpStatus.OK);
    }

}
