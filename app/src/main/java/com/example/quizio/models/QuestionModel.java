package com.example.quizio.models;

import java.util.List;

public abstract class QuestionModel {
    protected String questionText;
    protected int score;
    protected String correctAnswer;
    protected String category;

    public QuestionModel(String questionText, String correctAnswer, String category) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.score = 0; // Default score, can be set by scoring strategy
    }

    public QuestionModel(String questionText, String correctAnswer, String category, int score) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.score = score;
    }

    public QuestionModel(String questionText, int score) {

    }

    public QuestionModel() {

    }

    public abstract boolean checkAnswer(String userAnswer);

    // Getters
    public String getQuestionText() {
        return questionText;
    }

    public String getQuestion() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getScore() {
        return score;
    }

    public String getCategory() {
        return category;
    }

    // Setters
    public void setScore(int score) {
        this.score = score;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public abstract List<String> getOptions();

  
}