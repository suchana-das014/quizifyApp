package com.example.quizio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.quizio.facade.GameFacade;
import com.example.quizio.models.MCQQuestion;
import com.example.quizio.models.QuestionModel;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class PlayActivity extends AppCompatActivity {

    private TextView tvQuestionCounter, tvQuestion;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnNext, btnBack;
    private LinearProgressIndicator progressBar;

    private GameFacade gameFacade;

    private boolean answered = false;
    private String selectedAnswer = "";

    private String category, gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Bind UI components
        tvQuestionCounter = findViewById(R.id.tvQuestionCounter);
        tvQuestion = findViewById(R.id.tvQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);
        progressBar = findViewById(R.id.progressBar);

        btnBack.setOnClickListener(v -> finish());

        // Get data from intent extras
        category = getIntent().getStringExtra("category");
        gameMode = getIntent().getStringExtra("gameMode");

        if (category == null || category.isEmpty()) category = "General Knowledge";
        if (gameMode == null || gameMode.isEmpty()) gameMode = "normal";

        // Initialize Facade and load questions
        gameFacade = new GameFacade(gameMode);
        gameFacade.loadQuestions(category);

        // Safety: if there are no questions, finish gracefully
        if (gameFacade.getTotalQuestions() == 0) {
            Toast.makeText(this, "No questions available.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        showQuestion();

        // Option button listeners
        Button[] optionButtons = {btnOption1, btnOption2, btnOption3, btnOption4};
        for (Button btn : optionButtons) {
            btn.setOnClickListener(v -> {
                if (answered) return;
                selectedAnswer = btn.getText().toString();
                resetOptionColors();
                btn.setBackgroundColor(ContextCompat.getColor(this, R.color.accent_color));
                btnNext.setEnabled(true);
            });
        }

        // Next / Submit button logic
        btnNext.setOnClickListener(v -> {
            if (!answered) {
                if (selectedAnswer.isEmpty()) return;

                checkAnswerAndShowColors();
                answered = true;

                btnNext.setText(gameFacade.hasNext() ? "Next Question" : "Finish");
                setOptionsEnabled(false);

            } else {
                if (gameFacade.hasNext()) {
                    gameFacade.moveToNext();
                    showQuestion();
                    answered = false;
                    btnNext.setText("Submit");
                    setOptionsEnabled(true);

                } else {
                    // End of quiz -> go to results
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("score", gameFacade.getScore());
                    intent.putExtra("total", gameFacade.getTotalQuestions());
                    intent.putExtra("gameMode", gameMode);
                    intent.putExtra("category", category);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void showQuestion() {
        resetOptionColors();
        btnNext.setEnabled(false);
        selectedAnswer = "";

        // Get current question from facade (may be null if facade not properly populated)
        QuestionModel currentQuestion = gameFacade.getCurrentQuestion();
        if (currentQuestion == null) {
            // Something went wrong: bail out instead of crashing
            Toast.makeText(this, "Unable to load question.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Update counters and progress safely (avoid divide by zero)
        int total = gameFacade.getTotalQuestions();
        int index = gameFacade.getCurrentIndex();
        if (total <= 0) total = 1; // avoid division by zero

        tvQuestionCounter.setText(index + "/" + gameFacade.getTotalQuestions());

        // Use non-animated update to reduce chance of UI stalls
        int progressValue = index * 100 / total;
        try {
            progressBar.setProgress(progressValue);
        } catch (Exception e) {
            // If progress indicator causes layout/animation issues on some devices,
            // ignore and continue (prevents freeze).
            e.printStackTrace();
        }

        // Note: use getQuestion() (your original model used this name). If your model
        // uses a different getter (like getQuestionText()), change it accordingly.
        String questionText;
        try {
            questionText = currentQuestion.getQuestion();
        } catch (NoSuchMethodError | Exception ex) {
            // fallback if your model uses a different method name
            try {
                questionText = (String) currentQuestion.getClass().getMethod("getQuestionText").invoke(currentQuestion);
            } catch (Exception e) {
                questionText = "Question";
            }
        }
        tvQuestion.setText(questionText);

        if (currentQuestion instanceof MCQQuestion) {
            MCQQuestion mcq = (MCQQuestion) currentQuestion;

            // Guard against malformed option lists
            if (mcq.getOptions() != null && mcq.getOptions().size() >= 4) {
                btnOption1.setText(mcq.getOptions().get(0));
                btnOption2.setText(mcq.getOptions().get(1));
                btnOption3.setText(mcq.getOptions().get(2));
                btnOption4.setText(mcq.getOptions().get(3));
            } else {
                // If options are missing, disable option buttons to avoid crashes
                btnOption1.setText("-");
                btnOption2.setText("-");
                btnOption3.setText("-");
                btnOption4.setText("-");
                setOptionsEnabled(false);
            }

            setOptionButtonsVisibility(true);

        } else {
            // non-MCQ question type: hide option buttons (but don't trigger heavy relayouts)
            setOptionButtonsVisibility(false);
        }
    }

    private void checkAnswerAndShowColors() {
        QuestionModel question = gameFacade.getCurrentQuestion();
        if (question == null) {
            Toast.makeText(this, "Question missing.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Normalize strings
        String correctAnswer = question.getCorrectAnswer().trim();
        int pointsEarned = gameFacade.submitAnswer(selectedAnswer.trim());
        boolean isCorrect = pointsEarned > 0;

        resetOptionColors();

        // Color the correct option green
        colorOptionButton(correctAnswer, ContextCompat.getColor(this, R.color.correct_color));

        // If user was wrong, color their selection red
        if (!isCorrect && selectedAnswer != null && !selectedAnswer.isEmpty()) {
            colorOptionButton(selectedAnswer.trim(), ContextCompat.getColor(this, R.color.wrong_color));
        }

        Toast.makeText(this,
                (isCorrect ? "Correct +1 point" : "Wrong +0 points"),
                Toast.LENGTH_SHORT
        ).show();
    }


    private void colorOptionButton(String answer, int color) {
        if (answer == null) return;
        if (btnOption1.getText() != null && btnOption1.getText().toString().equals(answer))
            btnOption1.setBackgroundColor(color);
        if (btnOption2.getText() != null && btnOption2.getText().toString().equals(answer))
            btnOption2.setBackgroundColor(color);
        if (btnOption3.getText() != null && btnOption3.getText().toString().equals(answer))
            btnOption3.setBackgroundColor(color);
        if (btnOption4.getText() != null && btnOption4.getText().toString().equals(answer))
            btnOption4.setBackgroundColor(color);
    }

    private void setOptionsEnabled(boolean enabled) {
        btnOption1.setEnabled(enabled);
        btnOption2.setEnabled(enabled);
        btnOption3.setEnabled(enabled);
        btnOption4.setEnabled(enabled);
    }

    private void setOptionButtonsVisibility(boolean visible) {
        int visibility = visible ? android.view.View.VISIBLE : android.view.View.GONE;
        btnOption1.setVisibility(visibility);
        btnOption2.setVisibility(visibility);
        btnOption3.setVisibility(visibility);
        btnOption4.setVisibility(visibility);
    }

    private void resetOptionColors() {
        int defaultColor = ContextCompat.getColor(this, android.R.color.transparent);
        btnOption1.setBackgroundColor(defaultColor);
        btnOption2.setBackgroundColor(defaultColor);
        btnOption3.setBackgroundColor(defaultColor);
        btnOption4.setBackgroundColor(defaultColor);
    }
}
