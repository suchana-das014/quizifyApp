package com.example.quizio.adapter;

public interface QuestionInterface {
    String getQuestion();
    String getCorrectAnswer();
    boolean checkAnswer(String answer);
}
