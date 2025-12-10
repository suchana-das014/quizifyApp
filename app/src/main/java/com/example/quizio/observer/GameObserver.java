package com.example.quizio.observer;

public interface GameObserver {
    void onQuestionChanged(int currentIndex, int totalQuestions);
}
