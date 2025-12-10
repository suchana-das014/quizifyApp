package com.example.quizio.models;

import java.util.Collections;
import java.util.List;

public class FillBlankQuestion extends QuestionModel {
    public FillBlankQuestion(String questionText, String correctAnswer, String category) {
        super(questionText, correctAnswer, category);
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return false;
    }


    @Override
    public List<String> getOptions() {
        return Collections.emptyList();
    }
}