package com.project.quizapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.quizapp.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>{

    List<Question> findByCategory(String category);
    
    // @Query(value = "SELECT * FROM public.question WHERE category =:category ORDER BY RANDOM() LIMIT :noOfQues", nativeQuery = true)
    // List<Question> getQuiz(@Param("category") String category, @Param("noOfQues") int noOfQues);

    
    @Query(value = "SELECT * FROM public.question WHERE LOWER(category) = LOWER(:category) ORDER BY RANDOM() LIMIT :noOfQues", nativeQuery = true)
    List<Question> getQuiz(@Param("category") String category, @Param("noOfQues") int noOfQues);
} 