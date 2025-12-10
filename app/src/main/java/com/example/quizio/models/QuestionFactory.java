package com.example.quizio.models;

import java.util.List;

public class QuestionFactory {
    public static QuestionModel createQuestion(String type, String question,
                                               List<String> options, String correctAnswer,
                                               String category) {
        switch (type) {
            case "MCQ":
                return new MCQQuestion(question, options, correctAnswer, category);
            case "TrueFalse":

                return new TrueFalseQuestion(question, correctAnswer, category);
            case "FillBlank":

                return new FillBlankQuestion(question, correctAnswer, category);
            default:
                throw new IllegalArgumentException("Unknown question type: " + type);
        }
    }
}