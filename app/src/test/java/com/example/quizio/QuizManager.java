package com.example.quizio;

import java.util.List;

public class QuizManager {
    private final List<QuestionModel> questionList;
    private int currentIndex = 0;
    private int score = 0;

    public QuizManager(List<QuestionModel> questionList) {
        this.questionList = questionList;
    }

    public QuestionModel getCurrentQuestion() {
        return questionList.get(currentIndex);
    }

    public boolean submitAnswer(String answer) {
        boolean correct = getCurrentQuestion().getCorrectAnswer().equals(answer);
        if (correct) score++;
        return correct;
    }

    public boolean moveToNext() {
        if (currentIndex < questionList.size() - 1) {
            currentIndex++;
            return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questionList.size();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}
