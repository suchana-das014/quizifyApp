package com.example.quizio.iterator;

import com.example.quizio.models.QuestionModel;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository implements QuestionContainer {

    private List<QuestionModel> questions;
    private String category;

    public QuizRepository(String category, List<QuestionModel> questions) {
        this.category = category;
        this.questions = questions;
    }

    @Override
    public QuestionIterator getIterator() {
        return new QuestionListIterator(questions, false);
    }

    @Override
    public QuestionIterator getShuffledIterator() {
        return new QuestionListIterator(questions, true);
    }

    @Override
    public int getQuestionCount() {
        return questions.size();
    }

    public String getCategory() {
        return category;
    }

    public void addQuestion(QuestionModel question) {
        questions.add(question);
    }

    public void removeQuestion(QuestionModel question) {
        questions.remove(question);
    }

    public void clearQuestions() {
        questions.clear();
    }
}