package com.example.quizio.strategy;

import android.view.View;
import android.widget.Button;

public class QuestionContext {
    private QuestionStrategy strategy;

    public QuestionContext(QuestionStrategy strategy) {
        this.strategy = strategy;
    }

    public void setupUI(Button[] buttons) {
        strategy.setupUI(buttons);
    }

    public void handleOptionClick(Button button) {
        strategy.handleOptionClick(button);
    }

    public boolean checkAnswer(String selectedAnswer, String correctAnswer) {
        return strategy.checkAnswer(selectedAnswer, correctAnswer);
    }

    public void highlightCorrectAnswer(Button[] buttons, String correctAnswer) {
        strategy.highlightCorrectAnswer(buttons, correctAnswer);
    }

    public void highlightWrongAnswer(Button[] buttons, String selectedAnswer, String correctAnswer) {
        strategy.highlightWrongAnswer(buttons, selectedAnswer, correctAnswer);
    }

    public void resetOptions(Button[] buttons) {
        strategy.resetOptions(buttons);
    }

    public String getSelectedAnswer() {
        return strategy.getSelectedAnswer();
    }

    public boolean isAnswerSelected() {
        return strategy.isAnswerSelected();
    }
}