package com.example.quizio.models;

import java.util.List;

public class MCQQuestion extends QuestionModel {

    private List<String> options;

    public MCQQuestion(String questionText, List<String> options, String correctAnswer, String category) {
        super(questionText, correctAnswer, category);
        this.options = options;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return correctAnswer.equals(userAnswer);
    }

    @Override
    public List<String> getOptions() {
        return options;
    }
}