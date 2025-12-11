package com.example.quizio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

// Command Pattern imports for Skip only
import com.example.quizio.command.SkipCommand;
import com.example.quizio.command.QuizReceiver;

import com.example.quizio.models.MCQQuestion;
import com.example.quizio.models.QuestionModel;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayActivity extends AppCompatActivity {

    private TextView tvQuestionCounter, tvQuestion;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnNext, btnBack, btnSkip;
    private LinearProgressIndicator progressBar;

    private ArrayList<QuestionModel> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String selectedAnswer = "";
    private String category = "General Knowledge";
    private boolean answered = false;

    // Command Pattern for Skip
    private QuizReceiver quizReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Initialize Command Pattern receiver
        quizReceiver = new QuizReceiver();

        // Bind views
        tvQuestionCounter = findViewById(R.id.tvQuestionCounter);
        tvQuestion = findViewById(R.id.tvQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);
        progressBar = findViewById(R.id.progressBar);

        // Skip button (add this to your XML)
        btnSkip = findViewById(R.id.btnSkip);

        btnBack.setOnClickListener(v -> finish());

        // Get category only
        category = getIntent().getStringExtra("category");
        if (category == null || category.isEmpty()) category = "General Knowledge";

        loadQuestions(category);

        if (questionList.isEmpty()) {
            Toast.makeText(this, "No questions loaded!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        showQuestion();

        // Option selection
        Button[] options = {btnOption1, btnOption2, btnOption3, btnOption4};
        for (Button btn : options) {
            btn.setOnClickListener(v -> {
                if (answered) return;
                selectedAnswer = btn.getText().toString();
                resetOptionColors();
                btn.setBackgroundColor(ContextCompat.getColor(this, R.color.accent_color));
                btnNext.setEnabled(true);
            });
        }

        // Skip button using Command Pattern
        btnSkip.setOnClickListener(v -> {
            QuestionModel currentQuestion = questionList.get(currentQuestionIndex);

            // Create and execute Skip Command
            SkipCommand skipCommand = new SkipCommand(currentQuestion, quizReceiver);
            skipCommand.execute();

            Toast.makeText(this, "Question skipped!", Toast.LENGTH_SHORT).show();

            // Move to next question automatically
            currentQuestionIndex++;
            if (currentQuestionIndex < questionList.size()) {
                showQuestion();
                answered = false;
                selectedAnswer = "";
                btnNext.setText("Submit");
                setOptionsEnabled(true);
            } else {
                // Go to Result
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("total", questionList.size());
                intent.putExtra("category", category);
                startActivity(intent);
                finish();
            }
        });

        // Next button logic
        btnNext.setOnClickListener(v -> {
            if (!answered) {
                if (selectedAnswer.isEmpty()) return;

                checkAnswerAndShowColors();
                answered = true;
                btnNext.setText(currentQuestionIndex == questionList.size() - 1 ? "Finish" : "Next Question");
                setOptionsEnabled(false);
            } else {
                currentQuestionIndex++;
                if (currentQuestionIndex < questionList.size()) {
                    showQuestion();
                    answered = false;
                    selectedAnswer = "";
                    btnNext.setText("Submit");
                    setOptionsEnabled(true);
                } else {
                    // Go to Result
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("total", questionList.size());
                    intent.putExtra("category", category);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void loadQuestions(String category) {
        questionList = new ArrayList<>();

        if (category.equals("General Knowledge")) {
            questionList.add(new MCQQuestion("Capital of France?", new ArrayList<>(Arrays.asList("Paris", "London", "Berlin", "Madrid")), "Paris", category));
            questionList.add(new MCQQuestion("Largest planet?", new ArrayList<>(Arrays.asList("Earth", "Jupiter", "Saturn", "Mars")), "Jupiter", category));
            questionList.add(new MCQQuestion("Who wrote 'Romeo and Juliet'?", new ArrayList<>(Arrays.asList("Shakespeare", "Dickens", "Twain", "Austen")), "Shakespeare", category));
            questionList.add(new MCQQuestion("Currency of Japan?", new ArrayList<>(Arrays.asList("Yuan", "Yen", "Won", "Rupee")), "Yen", category));
            questionList.add(new MCQQuestion("Tallest mountain?", new ArrayList<>(Arrays.asList("K2", "Everest", "Kangchenjunga", "Lhotse")), "Everest", category));
            questionList.add(new MCQQuestion("How many continents?", new ArrayList<>(Arrays.asList("5", "6", "7", "8")), "7", category));
            questionList.add(new MCQQuestion("Biggest ocean?", new ArrayList<>(Arrays.asList("Atlantic", "Indian", "Arctic", "Pacific")), "Pacific", category));
            questionList.add(new MCQQuestion("Inventor of telephone?", new ArrayList<>(Arrays.asList("Edison", "Bell", "Tesla", "Einstein")), "Bell", category));
            questionList.add(new MCQQuestion("Color of emerald?", new ArrayList<>(Arrays.asList("Red", "Blue", "Green", "Yellow")), "Green", category));
            questionList.add(new MCQQuestion("Smallest prime number?", new ArrayList<>(Arrays.asList("0", "1", "2", "3")), "2", category));
        }
        else if (category.equals("Science")) {
            questionList.add(new MCQQuestion("Chemical symbol for water?", new ArrayList<>(Arrays.asList("O2", "H2O", "CO2", "NaCl")), "H2O", category));
            questionList.add(new MCQQuestion("Speed of light?", new ArrayList<>(Arrays.asList("300,000 km/s", "150,000 km/s", "500,000 km/s", "100,000 km/s")), "300,000 km/s", category));
            questionList.add(new MCQQuestion("Red planet?", new ArrayList<>(Arrays.asList("Venus", "Mars", "Jupiter", "Saturn")), "Mars", category));
            questionList.add(new MCQQuestion("Largest organ in human body?", new ArrayList<>(Arrays.asList("Heart", "Brain", "Skin", "Liver")), "Skin", category));
            questionList.add(new MCQQuestion("Photosynthesis makes?", new ArrayList<>(Arrays.asList("Oxygen", "Carbon", "Nitrogen", "Hydrogen")), "Oxygen", category));
            questionList.add(new MCQQuestion("Atomic number of Hydrogen?", new ArrayList<>(Arrays.asList("1", "2", "3", "4")), "1", category));
            questionList.add(new MCQQuestion("Sound fastest in?", new ArrayList<>(Arrays.asList("Air", "Water", "Steel", "Vacuum")), "Steel", category));
            questionList.add(new MCQQuestion("Vitamin from sunlight?", new ArrayList<>(Arrays.asList("A", "B", "C", "D")), "D", category));
            questionList.add(new MCQQuestion("DNA stands for?", new ArrayList<>(Arrays.asList("Deoxyribonucleic Acid", "Data Nucleic Acid", "Double Nucleic Acid", "None")), "Deoxyribonucleic Acid", category));
            questionList.add(new MCQQuestion("Unit of force?", new ArrayList<>(Arrays.asList("Joule", "Watt", "Newton", "Pascal")), "Newton", category));
        }
        else if (category.equals("Sports")) {
            questionList.add(new MCQQuestion("Players in football team?", new ArrayList<>(Arrays.asList("9", "10", "11", "12")), "11", category));
            questionList.add(new MCQQuestion("Home run is used in?", new ArrayList<>(Arrays.asList("Cricket", "Baseball", "Football", "Hockey")), "Baseball", category));
            questionList.add(new MCQQuestion("Olympics every how many years?", new ArrayList<>(Arrays.asList("2", "3", "4", "5")), "4", category));
            questionList.add(new MCQQuestion("Shuttlecock used in?", new ArrayList<>(Arrays.asList("Tennis", "Badminton", "Squash", "Golf")), "Badminton", category));
            questionList.add(new MCQQuestion("Zero score in tennis?", new ArrayList<>(Arrays.asList("Nil", "Love", "Duck", "Zero")), "Love", category));
            questionList.add(new MCQQuestion("Michael Jordan played?", new ArrayList<>(Arrays.asList("Football", "Basketball", "Baseball", "Tennis")), "Basketball", category));
            questionList.add(new MCQQuestion("FIFA World Cup first won by?", new ArrayList<>(Arrays.asList("Brazil", "Germany", "Uruguay", "Italy")), "Uruguay", category));
            questionList.add(new MCQQuestion("Cricket World Cup 2023 winner?", new ArrayList<>(Arrays.asList("India", "England", "Australia", "New Zealand")), "Australia", category));
            questionList.add(new MCQQuestion("Wimbledon is for?", new ArrayList<>(Arrays.asList("Football", "Cricket", "Tennis", "Golf")), "Tennis", category));
            questionList.add(new MCQQuestion("Fastest man on earth?", new ArrayList<>(Arrays.asList("Bolt", "Gatlin", "Blake", "Powell")), "Bolt", category));
        }
        else if (category.equals("Technology")) {
            questionList.add(new MCQQuestion("Android developed by?", new ArrayList<>(Arrays.asList("Apple", "Microsoft", "Google", "Amazon")), "Google", category));
            questionList.add(new MCQQuestion("CPU stands for?", new ArrayList<>(Arrays.asList("Central Processing Unit", "Computer Processing Unit", "Central Power Unit", "Core Processing Unit")), "Central Processing Unit", category));
            questionList.add(new MCQQuestion("HTML is for?", new ArrayList<>(Arrays.asList("Styling", "Structure", "Behavior", "Database")), "Structure", category));
            questionList.add(new MCQQuestion("iPhone made by?", new ArrayList<>(Arrays.asList("Samsung", "Apple", "Nokia", "OnePlus")), "Apple", category));
            questionList.add(new MCQQuestion("RAM stands for?", new ArrayList<>(Arrays.asList("Read Access Memory", "Random Access Memory", "Run Access Memory", "Real Access Memory")), "Random Access Memory", category));
            questionList.add(new MCQQuestion("Main language for Android now?", new ArrayList<>(Arrays.asList("Java", "Kotlin", "Dart", "Swift")), "Kotlin", category));
            questionList.add(new MCQQuestion("USB stands for?", new ArrayList<>(Arrays.asList("Universal Serial Bus", "United Serial Bus", "Universal System Bus", "None")), "Universal Serial Bus", category));
            questionList.add(new MCQQuestion("Inventor of WWW?", new ArrayList<>(Arrays.asList("Bill Gates", "Tim Berners-Lee", "Steve Jobs", "Elon Musk")), "Tim Berners-Lee", category));
            questionList.add(new MCQQuestion("Bitcoin is a?", new ArrayList<>(Arrays.asList("Stock", "Cryptocurrency", "Bond", "Fiat")), "Cryptocurrency", category));
            questionList.add(new MCQQuestion("AI stands for?", new ArrayList<>(Arrays.asList("Artificial Intelligence", "Automated Intelligence", "Advanced Internet", "None")), "Artificial Intelligence", category));
        }
    }

    private void showQuestion() {
        resetOptionColors();
        btnNext.setEnabled(false);
        selectedAnswer = "";
        answered = false;

        QuestionModel q = questionList.get(currentQuestionIndex);
        tvQuestion.setText(q.getQuestion());
        tvQuestionCounter.setText((currentQuestionIndex + 1) + "/" + questionList.size());
        progressBar.setProgressCompat((currentQuestionIndex + 1) * 100 / questionList.size(), true);

        if (q instanceof MCQQuestion) {
            MCQQuestion mcq = (MCQQuestion) q;
            btnOption1.setText(mcq.getOptions().get(0));
            btnOption2.setText(mcq.getOptions().get(1));
            btnOption3.setText(mcq.getOptions().get(2));
            btnOption4.setText(mcq.getOptions().get(3));
        }

        // Check if this question was skipped
        if (quizReceiver.isQuestionSkipped(q)) {
            Toast.makeText(this, "This question was previously skipped", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkAnswerAndShowColors() {
        QuestionModel q = questionList.get(currentQuestionIndex);
        boolean isCorrect = q.checkAnswer(selectedAnswer);

        resetOptionColors();
        colorOptionButton(q.getCorrectAnswer(), ContextCompat.getColor(this, R.color.correct_color));
        if (!isCorrect) {
            colorOptionButton(selectedAnswer, ContextCompat.getColor(this, R.color.wrong_color));
        }

        if (isCorrect) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    private void colorOptionButton(String text, int color) {
        if (btnOption1.getText().toString().equals(text)) btnOption1.setBackgroundColor(color);
        if (btnOption2.getText().toString().equals(text)) btnOption2.setBackgroundColor(color);
        if (btnOption3.getText().toString().equals(text)) btnOption3.setBackgroundColor(color);
        if (btnOption4.getText().toString().equals(text)) btnOption4.setBackgroundColor(color);
    }

    private void setOptionsEnabled(boolean enabled) {
        btnOption1.setEnabled(enabled);
        btnOption2.setEnabled(enabled);
        btnOption3.setEnabled(enabled);
        btnOption4.setEnabled(enabled);
    }

    private void resetOptionColors() {
        int trans = ContextCompat.getColor(this, android.R.color.transparent);
        btnOption1.setBackgroundColor(trans);
        btnOption2.setBackgroundColor(trans);
        btnOption3.setBackgroundColor(trans);
        btnOption4.setBackgroundColor(trans);
    }
}