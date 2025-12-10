package com.example.quizio.strategy;

import android.view.View;
import android.widget.Button;
import android.content.Context;
import androidx.core.content.ContextCompat;

import com.example.quizio.R;

public class TrueFalseStrategy implements QuestionStrategy {

    private Context context;
    private String selectedAnswer = "";

    public TrueFalseStrategy(Context context) {
        this.context = context;
    }

    @Override
    public void setupUI(Button[] buttons) {
        buttons[0].setVisibility(View.VISIBLE);
        buttons[0].setText("True");
        buttons[1].setVisibility(View.VISIBLE);
        buttons[1].setText("False");
        buttons[2].setVisibility(View.GONE);
        buttons[3].setVisibility(View.GONE);
    }

    @Override
    public void handleOptionClick(Button button) {
        selectedAnswer = button.getText().toString();
        button.setBackgroundColor(ContextCompat.getColor(context, R.color.accent_color));
    }

    @Override
    public boolean checkAnswer(String selectedAnswer, String correctAnswer) {
        return correctAnswer.equalsIgnoreCase(selectedAnswer.trim());
    }

    @Override
    public void highlightCorrectAnswer(Button[] buttons, String correctAnswer) {
        for (int i = 0; i < 2; i++) {
            if (buttons[i].getText().toString().equals(correctAnswer)) {
                buttons[i].setBackgroundColor(ContextCompat.getColor(context, R.color.correct_color));
                break;
            }
        }
    }

    @Override
    public void highlightWrongAnswer(Button[] buttons, String selectedAnswer, String correctAnswer) {
        for (int i = 0; i < 2; i++) {
            String btnText = buttons[i].getText().toString();
            if (btnText.equals(selectedAnswer) && !btnText.equals(correctAnswer)) {
                buttons[i].setBackgroundColor(ContextCompat.getColor(context, R.color.wrong_color));
            }
        }
    }

    @Override
    public void resetOptions(Button[] buttons) {
        selectedAnswer = "";
        for (int i = 0; i < 2; i++) {
            buttons[i].setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
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