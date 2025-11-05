package com.example.quizio.models;

public class QuestionFactory {

    public static QuestionModel createQuestion(String type, String questionText, String[] options, String correctAnswer, int score) {
        switch (type.toLowerCase()) {
            case "mcq":
                return new MCQQuestion(questionText, options, correctAnswer, score);
            case "truefalse":
                boolean ans = correctAnswer.equalsIgnoreCase("true");
                return new TrueFalseQuestion(questionText, ans, score);
            default:
                throw new IllegalArgumentException("Unknown question type: " + type);
        }
    }
}