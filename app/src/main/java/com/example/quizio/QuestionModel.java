package com.example.quizio;

import java.io.Serializable;
import java.util.List;

public class QuestionModel implements Serializable {
    private String question;
    private List<String> options;
    private String correctAnswer;

    public QuestionModel() {}

    public QuestionModel(String question, List<String> options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }
}
