package com.example.quizio.models;

import java.util.Arrays;
import java.util.List;

public class MCQQuestion extends QuestionModel {
    private String[] options;
    private String correctAnswer;

    public MCQQuestion(String questionText, String[] options, String correctAnswer, int score) {
        super(questionText, score);
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public MCQQuestion(String question, List<String> options, String correctAnswer, String category) {
        super();


    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }

    public List<String> getOptions() { return Arrays.asList(options); }
}
