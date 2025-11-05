package com.example.quizio.models;

import java.util.Arrays;
import java.util.List;

public class FillBlankQuestion extends QuestionModel {

    public FillBlankQuestion(String questionText, String correctAnswer, String category) {
        super(questionText, correctAnswer, category);
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }

    @Override
    public List<String> getOptions() {
        return Arrays.asList("Type your answer...");
    }
}