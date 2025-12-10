package com.example.quizio.composite;

import com.example.quizio.models.QuestionModel;

public class QuestionComponent implements FileSystemComponent {

    private QuestionModel question;
    private String name;

    public QuestionComponent(QuestionModel question) {
        this.question = question;
        this.name = question.getQuestion();
    }

    @Override
    public void displayDetails() {
        System.out.println("Question: " + name);
        System.out.println("  Category: " + question.getCategory());
        System.out.println("  Type: " + (question instanceof com.example.quizio.models.MCQQuestion ? "MCQ" : "True/False"));
        System.out.println("  Correct Answer: " + question.getCorrectAnswer());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getQuestionCount() {
        return 1; // Each question counts as 1
    }

    @Override
    public boolean isCategory() {
        return false; // This is a leaf (question), not a category
    }

    public QuestionModel getQuestion() {
        return question;
    }
}