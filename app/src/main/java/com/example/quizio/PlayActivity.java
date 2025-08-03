package com.example.quizio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayActivity extends AppCompatActivity {

    private TextView tvQuestionCounter, tvQuestion;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnNext;

    private List<QuestionModel> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private boolean answered = false;

    private static final String PREFS_NAME = "QuizAppPrefs";
    private static final String KEY_CUSTOM_QUESTIONS = "CustomQuestions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Initialize views with correct IDs
        tvQuestionCounter = findViewById(R.id.tvQuestionCounter);
        tvQuestion = findViewById(R.id.tvQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnNext = findViewById(R.id.btnNext);

        loadQuestions();

        setupListeners();

        loadQuestionUI();
    }

    private void loadQuestions() {
        // Load default questions
        questionList = getDefaultQuestions();

        // Load custom questions from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String json = prefs.getString(KEY_CUSTOM_QUESTIONS, null);
        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<QuestionModel>>() {}.getType();
            List<QuestionModel> customQuestions = gson.fromJson(json, type);
            if (customQuestions != null) {
                questionList.addAll(customQuestions);
            }
        }
    }

    private List<QuestionModel> getDefaultQuestions() {
        List<QuestionModel> defaults = new ArrayList<>();

        defaults.add(new QuestionModel("What is the capital of France?",
                Arrays.asList("Paris", "London", "Rome", "Berlin"),
                "Paris"));

        defaults.add(new QuestionModel("Which planet is known as the Red Planet?",
                Arrays.asList("Venus", "Mars", "Jupiter", "Saturn"),
                "Mars"));

        defaults.add(new QuestionModel("What is the largest ocean on Earth?",
                Arrays.asList("Indian", "Pacific", "Atlantic", "Arctic"),
                "Pacific"));

        defaults.add(new QuestionModel("Who wrote 'Romeo and Juliet'?",
                Arrays.asList("Shakespeare", "Dickens", "Twain", "Hemingway"),
                "Shakespeare"));

        defaults.add(new QuestionModel("What is the smallest prime number?",
                Arrays.asList("0", "1", "2", "3"),
                "2"));

        // Add 5 more questions to make total 10
        defaults.add(new QuestionModel("Which gas do plants absorb from the atmosphere?",
                Arrays.asList("Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"),
                "Carbon Dioxide"));

        defaults.add(new QuestionModel("What is H2O commonly known as?",
                Arrays.asList("Oxygen", "Water", "Hydrogen", "Salt"),
                "Water"));

        defaults.add(new QuestionModel("Which country is known as the Land of the Rising Sun?",
                Arrays.asList("China", "South Korea", "Japan", "Thailand"),
                "Japan"));

        defaults.add(new QuestionModel("How many continents are there on Earth?",
                Arrays.asList("5", "6", "7", "8"),
                "7"));

        defaults.add(new QuestionModel("Who painted the Mona Lisa?",
                Arrays.asList("Van Gogh", "Michelangelo", "Leonardo da Vinci", "Picasso"),
                "Leonardo da Vinci"));

        return defaults;
    }

    private void setupListeners() {
        View.OnClickListener optionClickListener = v -> {
            if (!answered) {
                Button clicked = (Button) v;
                checkAnswer(clicked);
                answered = true;
                btnNext.setEnabled(true);
            }
        };

        btnOption1.setOnClickListener(optionClickListener);
        btnOption2.setOnClickListener(optionClickListener);
        btnOption3.setOnClickListener(optionClickListener);
        btnOption4.setOnClickListener(optionClickListener);

        btnNext.setOnClickListener(v -> {
            if (!answered) {
                Toast.makeText(PlayActivity.this, "Please select an answer first!", Toast.LENGTH_SHORT).show();
                return;
            }
            currentQuestionIndex++;
            if (currentQuestionIndex >= questionList.size()) {
                // Go to Result Activity
                Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("total", questionList.size());
                startActivity(intent);
                finish();
            } else {
                loadQuestionUI();
                answered = false;
                btnNext.setEnabled(false);
            }
        });

        // Disable Next button initially
        btnNext.setEnabled(false);
    }

    private void loadQuestionUI() {
        QuestionModel question = questionList.get(currentQuestionIndex);

        tvQuestionCounter.setText((currentQuestionIndex + 1) + "/" + questionList.size());
        tvQuestion.setText(question.getQuestion());

        List<String> opts = question.getOptions();
        btnOption1.setText(opts.get(0));
        btnOption2.setText(opts.get(1));
        btnOption3.setText(opts.get(2));
        btnOption4.setText(opts.get(3));

        resetOptionButtons();
    }

    private void checkAnswer(Button selectedButton) {
        QuestionModel question = questionList.get(currentQuestionIndex);
        String selectedAnswer = selectedButton.getText().toString();
        String correctAnswer = question.getCorrectAnswer();

        if (selectedAnswer.equals(correctAnswer)) {
            selectedButton.setBackgroundColor(getResources().getColor(R.color.correct_color));
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            selectedButton.setBackgroundColor(getResources().getColor(R.color.wrong_color));
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();

            // Highlight correct answer button
            highlightCorrectAnswer(correctAnswer);
        }
    }

    private void highlightCorrectAnswer(String correctAnswer) {
        if (btnOption1.getText().toString().equals(correctAnswer)) {
            btnOption1.setBackgroundColor(getResources().getColor(R.color.correct_color));
        } else if (btnOption2.getText().toString().equals(correctAnswer)) {
            btnOption2.setBackgroundColor(getResources().getColor(R.color.correct_color));
        } else if (btnOption3.getText().toString().equals(correctAnswer)) {
            btnOption3.setBackgroundColor(getResources().getColor(R.color.correct_color));
        } else if (btnOption4.getText().toString().equals(correctAnswer)) {
            btnOption4.setBackgroundColor(getResources().getColor(R.color.correct_color));
        }
    }

    private void resetOptionButtons() {
        int defaultColor = getResources().getColor(android.R.color.transparent);
        btnOption1.setBackgroundColor(defaultColor);
        btnOption2.setBackgroundColor(defaultColor);
        btnOption3.setBackgroundColor(defaultColor);
        btnOption4.setBackgroundColor(defaultColor);
    }
}
