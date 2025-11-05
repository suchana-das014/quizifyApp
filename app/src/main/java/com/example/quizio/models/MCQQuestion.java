package com.example.quizio.models;

public class MCQQuestion extends QuestionModel {
    private String[] options;
    private String correctAnswer;

    public MCQQuestion(String questionText, String[] options, String correctAnswer, int score) {
        super(questionText, score);
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }

    public String[] getOptions() { return options; }
}
