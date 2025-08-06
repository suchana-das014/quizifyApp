package com.example.quizio;

import java.util.List;

public class QuestionValidator {

    public static void validate(QuestionModel question) {
        if (question == null) {
            throw new IllegalArgumentException("QuestionModel cannot be null");
        }

        String q = question.getQuestion();
        List<String> options = question.getOptions();

        if (q == null || q.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be empty");
        }

        if (options == null || options.size() != 4) {
            throw new IllegalArgumentException("There must be exactly 4 options");
        }

        if (!options.contains(question.getCorrectAnswer())) {
            throw new IllegalArgumentException("Correct answer must be one of the options");
        }
    }
}
