package com.example.quizio.models;

import java.util.Collections;
import java.util.List;

public class TrueFalseQuestion extends QuestionModel {
    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText, boolean correctAnswer, int score) {
        super(questionText, score);
        this.correctAnswer = correctAnswer;
    }

    public TrueFalseQuestion(String question, String correctAnswer, String category) {

    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return Boolean.parseBoolean(userAnswer) == correctAnswer;
    }

    @Override
    public List<String> getOptions() {
        return Collections.emptyList();
    }
}