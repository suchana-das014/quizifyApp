package com.example.quizio.models;

public abstract class QuestionModel {
    protected String questionText;
    protected int score;

    public QuestionModel(String questionText, int score) {
        this.questionText = questionText;
        this.score = score;
    }

    public abstract boolean checkAnswer(String userAnswer);
    public String getQuestionText() { return questionText; }
}