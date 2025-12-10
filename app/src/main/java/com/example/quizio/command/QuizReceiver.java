package com.example.quizio.command;

import com.example.quizio.models.QuestionModel;
import java.util.HashSet;
import java.util.Set;

public class QuizReceiver {

    private Set<QuestionModel> skippedQuestions;

    public QuizReceiver() {
        skippedQuestions = new HashSet<>();
    }

    public void skipQuestion(QuestionModel question) {
        skippedQuestions.add(question);
    }

    public void unskipQuestion(QuestionModel question) {
        skippedQuestions.remove(question);
    }

    public boolean isQuestionSkipped(QuestionModel question) {
        return skippedQuestions.contains(question);
    }

    public Set<QuestionModel> getSkippedQuestions() {
        return new HashSet<>(skippedQuestions);
    }
}