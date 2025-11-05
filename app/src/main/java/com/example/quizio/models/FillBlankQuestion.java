package com.example.quizio.models;


public class FillBlankQuestion extends QuestionModel {
    private String correctAnswer;

    public FillBlankQuestion(String questionText, String correctAnswer, int score) {
        super(questionText, score);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.trim().equalsIgnoreCase(correctAnswer);
    }
}