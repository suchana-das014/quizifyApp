package com.example.quizio.iterator;

import com.example.quizio.models.QuestionModel;

import java.util.Iterator;

public interface QuestionIterator extends Iterator {
    QuestionModel nextQuestion();
    QuestionModel currentQuestion();

    int currentPosition();

    void reset();

    int getTotalQuestions();
}