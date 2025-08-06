package com.example.quizio;

import java.util.List;

public class QuizManager {
    private List<QuestionModel> questions;
    private int currentIndex = 0;
    private int score = 0;

    public QuizManager(List<QuestionModel> questions) {
        this.questions = questions;
    }

    public QuestionModel getCurrentQuestion() {
        return questions.get(currentIndex);
    }

    // Add this method for test purposes
    public QuestionModel getQuestionAtIndex(int index) {
        return questions.get(index);  // throws IndexOutOfBoundsException if index invalid
    }

    public boolean submitAnswer(String answer) {
        if (getCurrentQuestion().getCorrectAnswer().equals(answer)) {
            score++;
            return true;
        }
        return false;
    }

    public void moveToNext() {
        if (currentIndex < questions.size() - 1) {
            currentIndex++;
        }
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}
