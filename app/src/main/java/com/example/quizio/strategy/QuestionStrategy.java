package com.example.quizio.strategy;

import android.view.View;
import android.widget.Button;

public interface QuestionStrategy {
    void setupUI(Button[] buttons);
    void handleOptionClick(Button button);
    boolean checkAnswer(String selectedAnswer, String correctAnswer);
    void highlightCorrectAnswer(Button[] buttons, String correctAnswer);
    void highlightWrongAnswer(Button[] buttons, String selectedAnswer, String correctAnswer);
    void resetOptions(Button[] buttons);
    String getSelectedAnswer();
    boolean isAnswerSelected();
}