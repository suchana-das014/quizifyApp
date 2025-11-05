package com.example.quizio.models;

public class TrueFalseQuestion extends QuestionModel {
    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText, boolean correctAnswer, int score) {
        super(questionText, score);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return Boolean.parseBoolean(userAnswer) == correctAnswer;
    }
}