package com.example.quizio.strategy;

import android.view.View;
import android.widget.Button;
import android.content.Context;
import androidx.core.content.ContextCompat;

import com.example.quizio.R;

public class MCQStrategy implements QuestionStrategy {

    private Context context;
    private String selectedAnswer = "";

    public MCQStrategy(Context context) {
        this.context = context;
    }

    @Override
    public void setupUI(Button[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void handleOptionClick(Button button) {
        selectedAnswer = button.getText().toString();
        button.setBackgroundColor(ContextCompat.getColor(context, R.color.accent_color));
    }

    @Override
    public boolean checkAnswer(String selectedAnswer, String correctAnswer) {
        return correctAnswer.equals(selectedAnswer);
    }

    @Override
    public void highlightCorrectAnswer(Button[] buttons, String correctAnswer) {
        for (Button btn : buttons) {
            if (btn.getText().toString().equals(correctAnswer)) {
                btn.setBackgroundColor(ContextCompat.getColor(context, R.color.correct_color));
                break;
            }
        }
    }

    @Override
    public void highlightWrongAnswer(Button[] buttons, String selectedAnswer, String correctAnswer) {
        for (Button btn : buttons) {
            String btnText = btn.getText().toString();
            if (btnText.equals(selectedAnswer) && !btnText.equals(correctAnswer)) {
                btn.setBackgroundColor(ContextCompat.getColor(context, R.color.wrong_color));
            }
        }
    }

    @Override
    public void resetOptions(Button[] buttons) {
        selectedAnswer = "";
        for (Button btn : buttons) {
            btn.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        }
    }

    @Override
    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    @Override
    public boolean isAnswerSelected() {
        return !selectedAnswer.isEmpty();
    }
}