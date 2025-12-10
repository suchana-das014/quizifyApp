package com.example.quizio.models;

import java.util.Arrays;
import java.util.List;

public class TrueFalseQuestion extends QuestionModel {
    public TrueFalseQuestion(String questionText, String correctAnswer, String category) {
        super(questionText, correctAnswer, category);
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return correctAnswer.equalsIgnoreCase(userAnswer.trim());
    }

    @Override
    public List<String> getOptions() {
        return Arrays.asList("True", "False");
    }
}