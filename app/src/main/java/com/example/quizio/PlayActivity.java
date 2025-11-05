package com.example.quizio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.quizio.models.MCQQuestion;
import com.example.quizio.models.QuestionFactory;
import com.example.quizio.models.QuestionModel;
import com.example.quizio.strategies.HardMode;
import com.example.quizio.strategies.NormalMode;
import com.example.quizio.strategies.ScoringStrategy;
import com.example.quizio.strategies.TimedMode;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayActivity extends AppCompatActivity {

    private TextView tvQuestionCounter, tvQuestion;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnNext, btnBack;
    private LinearProgressIndicator progressBar;

    private List<QuestionModel> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String selectedAnswer = "";
    private String category;
    private String gameMode;
    private boolean answered = false;
    private ScoringStrategy scoringStrategy;
    private long questionStartTime;

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

        // Back button finishes activity
        btnBack.setOnClickListener(v -> finish());

        // Get category and game mode from intent extras
        category = getIntent().getStringExtra("category");
        gameMode = getIntent().getStringExtra("gameMode");

        if (category == null || category.isEmpty()) {
            category = "General Knowledge";
        }
        if (gameMode == null || gameMode.isEmpty()) {
            gameMode = "normal";
        }

        // Initialize scoring strategy based on game mode
        setupScoringStrategy(gameMode);

        loadQuestions(category);
        showQuestion();

        // Option buttons click
        Button[] optionButtons = {btnOption1, btnOption2, btnOption3, btnOption4};
        for (Button btn : optionButtons) {
            btn.setOnClickListener(v -> {
                if (answered) return;
                selectedAnswer = btn.getText().toString();
                resetOptionColors();
                // Use ContextCompat instead of deprecated getResources().getColor()
                btn.setBackgroundColor(ContextCompat.getColor(PlayActivity.this, R.color.accent_color));
                btnNext.setEnabled(true);
            });
        }

        // Next button click logic
        btnNext.setOnClickListener(v -> {
            if (!answered) {
                if (selectedAnswer.isEmpty()) return;

                checkAnswerAndShowColors();
                answered = true;

                if (currentQuestionIndex == questionList.size() - 1) {
                    btnNext.setText("Finish");
                } else {
                    btnNext.setText("Next Question");
                }
                setOptionsEnabled(false);
            } else {
                currentQuestionIndex++;
                if (currentQuestionIndex < questionList.size()) {
                    showQuestion();
                    answered = false;
                    btnNext.setText("Submit");
                    setOptionsEnabled(true);
                } else {
                    Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("total", questionList.size());
                    intent.putExtra("gameMode", gameMode);
                    intent.putExtra("category", category);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setupScoringStrategy(String mode) {
        switch (mode) {
            case "hard":
                scoringStrategy = new HardMode();
                break;
            case "timed":
                scoringStrategy = new TimedMode();
                break;
            case "normal":
            default:
                scoringStrategy = new NormalMode();
                break;
        }
    }

    private void loadQuestions(String category) {
        questionList = new ArrayList<>();

        switch (category) {
            case "General Knowledge":
                questionList.add(QuestionFactory.createQuestion("MCQ", "Capital of France?",
                        new ArrayList<>(Arrays.asList("Paris", "London", "Rome", "Berlin")), "Paris", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Largest planet in Solar System?",
                        new ArrayList<>(Arrays.asList("Mars", "Jupiter", "Saturn", "Venus")), "Jupiter", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Who wrote 'Hamlet'?",
                        new ArrayList<>(Arrays.asList("Mark Twain", "William Shakespeare", "Charles Dickens", "Jane Austen")), "William Shakespeare", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "The Great Wall is located in which country?",
                        new ArrayList<>(Arrays.asList("India", "China", "Japan", "Korea")), "China", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Which continent is Egypt in?",
                        new ArrayList<>(Arrays.asList("Africa", "Asia", "Europe", "Australia")), "Africa", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "How many colors are in a rainbow?",
                        new ArrayList<>(Arrays.asList("5", "6", "7", "8")), "7", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "The currency of Japan is?",
                        new ArrayList<>(Arrays.asList("Yuan", "Yen", "Won", "Dollar")), "Yen", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "What is the tallest mountain in the world?",
                        new ArrayList<>(Arrays.asList("K2", "Everest", "Kangchenjunga", "Lhotse")), "Everest", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Who painted the Mona Lisa?",
                        new ArrayList<>(Arrays.asList("Vincent Van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet")), "Leonardo da Vinci", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Which ocean is the largest?",
                        new ArrayList<>(Arrays.asList("Atlantic", "Indian", "Arctic", "Pacific")), "Pacific", category));
                break;

            case "Science":
                questionList.add(QuestionFactory.createQuestion("MCQ", "H2O is chemical name for?",
                        new ArrayList<>(Arrays.asList("Hydrogen", "Oxygen", "Water", "Helium")), "Water", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Speed of light is approximately?",
                        new ArrayList<>(Arrays.asList("300,000 km/s", "150,000 km/s", "1,000 km/s", "10,000 km/s")), "300,000 km/s", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Which gas is most abundant in the Earth's atmosphere?",
                        new ArrayList<>(Arrays.asList("Oxygen", "Nitrogen", "Carbon dioxide", "Hydrogen")), "Nitrogen", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "What planet is known as the Red Planet?",
                        new ArrayList<>(Arrays.asList("Venus", "Mars", "Jupiter", "Saturn")), "Mars", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "What is the human body's largest organ?",
                        new ArrayList<>(Arrays.asList("Heart", "Liver", "Skin", "Kidney")), "Skin", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "The process by which plants make food is called?",
                        new ArrayList<>(Arrays.asList("Photosynthesis", "Respiration", "Transpiration", "Fermentation")), "Photosynthesis", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Atomic number of Hydrogen is?",
                        new ArrayList<>(Arrays.asList("1", "2", "3", "4")), "1", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Sound travels fastest in?",
                        new ArrayList<>(Arrays.asList("Air", "Water", "Steel", "Vacuum")), "Steel", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "What vitamin is produced when skin is exposed to sunlight?",
                        new ArrayList<>(Arrays.asList("Vitamin A", "Vitamin B", "Vitamin C", "Vitamin D")), "Vitamin D", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Which particle has a negative charge?",
                        new ArrayList<>(Arrays.asList("Proton", "Neutron", "Electron", "Photon")), "Electron", category));
                break;

            case "Sports":
                questionList.add(QuestionFactory.createQuestion("MCQ", "How many players in a football team?",
                        new ArrayList<>(Arrays.asList("9", "10", "11", "12")), "11", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "In which sport is the term 'home run' used?",
                        new ArrayList<>(Arrays.asList("Cricket", "Baseball", "Football", "Tennis")), "Baseball", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Which country won the first FIFA World Cup?",
                        new ArrayList<>(Arrays.asList("Brazil", "Germany", "Uruguay", "Italy")), "Uruguay", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "The Olympics are held every how many years?",
                        new ArrayList<>(Arrays.asList("2", "3", "4", "5")), "4", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Which sport uses a shuttlecock?",
                        new ArrayList<>(Arrays.asList("Tennis", "Badminton", "Squash", "Table Tennis")), "Badminton", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "In tennis, what is a score of zero called?",
                        new ArrayList<>(Arrays.asList("Love", "Zero", "Nil", "Duck")), "Love", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Michael Jordan was famous in which sport?",
                        new ArrayList<>(Arrays.asList("Football", "Basketball", "Baseball", "Golf")), "Basketball", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "How many points is a touchdown worth in American football?",
                        new ArrayList<>(Arrays.asList("3", "6", "7", "1")), "6", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Which sport is known as the 'king of sports'?",
                        new ArrayList<>(Arrays.asList("Football", "Basketball", "Cricket", "Hockey")), "Football", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "What color card is shown for a foul in soccer?",
                        new ArrayList<>(Arrays.asList("Red", "Blue", "Green", "Yellow")), "Red", category));
                break;

            case "Technology":
                questionList.add(QuestionFactory.createQuestion("MCQ", "Android is developed by?",
                        new ArrayList<>(Arrays.asList("Apple", "Microsoft", "Google", "IBM")), "Google", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "What does CPU stand for?",
                        new ArrayList<>(Arrays.asList("Central Process Unit", "Central Processing Unit", "Computer Personal Unit", "Central Processor Unit")), "Central Processing Unit", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "HTML stands for?",
                        new ArrayList<>(Arrays.asList("HyperText Markup Language", "HyperText Makeup Language", "HyperText Mark Language", "HyperText Markdown Language")), "HyperText Markup Language", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Which company created the iPhone?",
                        new ArrayList<>(Arrays.asList("Apple", "Samsung", "Nokia", "Sony")), "Apple", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "RAM stands for?",
                        new ArrayList<>(Arrays.asList("Random Access Memory", "Read Access Memory", "Run Access Memory", "Random Actual Memory")), "Random Access Memory", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "What is the main language used for Android development?",
                        new ArrayList<>(Arrays.asList("Java", "Python", "Swift", "Kotlin")), "Kotlin", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "What does USB stand for?",
                        new ArrayList<>(Arrays.asList("Universal Serial Bus", "Universal Service Bus", "Unified Serial Bus", "Universal System Bus")), "Universal Serial Bus", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "Which protocol is used to send emails?",
                        new ArrayList<>(Arrays.asList("HTTP", "SMTP", "FTP", "POP3")), "SMTP", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "The first electronic computer was called?",
                        new ArrayList<>(Arrays.asList("ENIAC", "UNIVAC", "EDSAC", "ABC")), "ENIAC", category));
                questionList.add(QuestionFactory.createQuestion("MCQ", "What is an IP address used for?",
                        new ArrayList<>(Arrays.asList("Identifying a device on the internet", "Locating a file", "Programming a device", "Managing software")), "Identifying a device on the internet", category));
                break;

            default:
                questionList.add(QuestionFactory.createQuestion("MCQ", "Sample Question?",
                        new ArrayList<>(Arrays.asList("A", "B", "C", "D")), "A", "Default"));
                break;
        }
    }

    private void showQuestion() {
        resetOptionColors();
        btnNext.setEnabled(false);
        selectedAnswer = "";
        questionStartTime = System.currentTimeMillis(); // Start timing for this question

        QuestionModel currentQuestion = questionList.get(currentQuestionIndex);

        tvQuestion.setText(currentQuestion.getQuestion());
        tvQuestionCounter.setText((currentQuestionIndex + 1) + "/" + questionList.size());

        progressBar.setProgressCompat((currentQuestionIndex + 1) * 100 / questionList.size(), true);

        // Handle different question types polymorphically
        if (currentQuestion instanceof MCQQuestion) {
            MCQQuestion mcqQuestion = (MCQQuestion) currentQuestion;
            btnOption1.setText(mcqQuestion.getOptions().get(0));
            btnOption2.setText(mcqQuestion.getOptions().get(1));
            btnOption3.setText(mcqQuestion.getOptions().get(2));
            btnOption4.setText(mcqQuestion.getOptions().get(3));

            // Make sure option buttons are visible
            setOptionButtonsVisibility(true);
        } else {
            // Handle other question types (TrueFalse, FillBlank)
            // For now, hide option buttons for non-MCQ questions
            setOptionButtonsVisibility(false);
        }
    }

    private void checkAnswerAndShowColors() {
        QuestionModel currentQuestion = questionList.get(currentQuestionIndex);
        String correctAnswer = currentQuestion.getCorrectAnswer();
        boolean isCorrect = selectedAnswer.equals(correctAnswer);

        // Calculate time taken for this question (for timed mode)
        long timeTaken = System.currentTimeMillis() - questionStartTime;

        resetOptionColors();
        colorOptionButton(correctAnswer, ContextCompat.getColor(this, R.color.correct_color));

        if (!isCorrect) {
            colorOptionButton(selectedAnswer, ContextCompat.getColor(this, R.color.wrong_color));
        }

        // Use scoring strategy instead of simple increment
        int pointsEarned = scoringStrategy.calculateScore(isCorrect, (int) timeTaken);
        score += pointsEarned;

        // Show points earned for this question
        showPointsEarned(pointsEarned, isCorrect);
    }

    private void showPointsEarned(int points, boolean isCorrect) {
        String message = isCorrect ?
                "Correct! +" + points + " points" :
                "Wrong! +" + points + " points";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void colorOptionButton(String answerText, int color) {
        if (btnOption1.getText().toString().equals(answerText)) {
            btnOption1.setBackgroundColor(color);
        } else if (btnOption2.getText().toString().equals(answerText)) {
            btnOption2.setBackgroundColor(color);
        } else if (btnOption3.getText().toString().equals(answerText)) {
            btnOption3.setBackgroundColor(color);
        } else if (btnOption4.getText().toString().equals(answerText)) {
            btnOption4.setBackgroundColor(color);
        }
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