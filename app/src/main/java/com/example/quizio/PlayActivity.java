package com.example.quizio;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    private boolean answered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

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

        // Back button click: finish activity to go back
        btnBack.setOnClickListener(v -> finish());

        // Get category from intent
        category = getIntent().getStringExtra("category");

        // Load questions based on category
        loadQuestions(category);

        // Show first question
        showQuestion();

        // Option button listeners
        View.OnClickListener optionClickListener = v -> {
            if (answered) return; // Ignore if already answered

            Button clickedButton = (Button) v;
            selectedAnswer = clickedButton.getText().toString();
            resetOptionColors();
            clickedButton.setBackgroundColor(getResources().getColor(R.color.accent_color));
            btnNext.setEnabled(true);
        };

        btnOption1.setOnClickListener(optionClickListener);
        btnOption2.setOnClickListener(optionClickListener);
        btnOption3.setOnClickListener(optionClickListener);
        btnOption4.setOnClickListener(optionClickListener);

        // Next button listener
        btnNext.setOnClickListener(v -> {
            if (!answered) {
                // Submit answer
                if (selectedAnswer.isEmpty()) {
                    // No option selected, ignore click
                    return;
                }
                checkAnswerAndShowColors();
                answered = true;

                if (currentQuestionIndex == questionList.size() - 1) {
                    btnNext.setText("Finish");
                } else {
                    btnNext.setText("Next Question");
                }
                setOptionsEnabled(false);
            } else {
                // Move to next question or finish
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
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void loadQuestions(String category) {
        questionList = new ArrayList<>();

        if (category == null) category = "";

        switch (category) {
            case "General Knowledge":
                questionList.add(new QuestionModel("Capital of France?",
                        Arrays.asList("Paris", "London", "Rome", "Berlin"), "Paris", category));
                questionList.add(new QuestionModel("Largest planet in Solar System?",
                        Arrays.asList("Mars", "Jupiter", "Saturn", "Venus"), "Jupiter", category));
                questionList.add(new QuestionModel("Who wrote 'Hamlet'?",
                        Arrays.asList("Mark Twain", "William Shakespeare", "Charles Dickens", "Jane Austen"), "William Shakespeare", category));
                questionList.add(new QuestionModel("The Great Wall is located in which country?",
                        Arrays.asList("India", "China", "Japan", "Korea"), "China", category));
                questionList.add(new QuestionModel("Which continent is Egypt in?",
                        Arrays.asList("Africa", "Asia", "Europe", "Australia"), "Africa", category));
                questionList.add(new QuestionModel("How many colors are in a rainbow?",
                        Arrays.asList("5", "6", "7", "8"), "7", category));
                questionList.add(new QuestionModel("The currency of Japan is?",
                        Arrays.asList("Yuan", "Yen", "Won", "Dollar"), "Yen", category));
                questionList.add(new QuestionModel("What is the tallest mountain in the world?",
                        Arrays.asList("K2", "Everest", "Kangchenjunga", "Lhotse"), "Everest", category));
                questionList.add(new QuestionModel("Who painted the Mona Lisa?",
                        Arrays.asList("Vincent Van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"), "Leonardo da Vinci", category));
                questionList.add(new QuestionModel("Which ocean is the largest?",
                        Arrays.asList("Atlantic", "Indian", "Arctic", "Pacific"), "Pacific", category));
                break;

            case "Science":
                questionList.add(new QuestionModel("H2O is chemical name for?",
                        Arrays.asList("Hydrogen", "Oxygen", "Water", "Helium"), "Water", category));
                questionList.add(new QuestionModel("Speed of light is approximately?",
                        Arrays.asList("300,000 km/s", "150,000 km/s", "1,000 km/s", "10,000 km/s"), "300,000 km/s", category));
                questionList.add(new QuestionModel("Which gas is most abundant in the Earth’s atmosphere?",
                        Arrays.asList("Oxygen", "Nitrogen", "Carbon dioxide", "Hydrogen"), "Nitrogen", category));
                questionList.add(new QuestionModel("What planet is known as the Red Planet?",
                        Arrays.asList("Venus", "Mars", "Jupiter", "Saturn"), "Mars", category));
                questionList.add(new QuestionModel("What is the human body's largest organ?",
                        Arrays.asList("Heart", "Liver", "Skin", "Kidney"), "Skin", category));
                questionList.add(new QuestionModel("The process by which plants make food is called?",
                        Arrays.asList("Photosynthesis", "Respiration", "Transpiration", "Fermentation"), "Photosynthesis", category));
                questionList.add(new QuestionModel("Atomic number of Hydrogen is?",
                        Arrays.asList("1", "2", "3", "4"), "1", category));
                questionList.add(new QuestionModel("Sound travels fastest in?",
                        Arrays.asList("Air", "Water", "Steel", "Vacuum"), "Steel", category));
                questionList.add(new QuestionModel("What vitamin is produced when skin is exposed to sunlight?",
                        Arrays.asList("Vitamin A", "Vitamin B", "Vitamin C", "Vitamin D"), "Vitamin D", category));
                questionList.add(new QuestionModel("Which particle has a negative charge?",
                        Arrays.asList("Proton", "Neutron", "Electron", "Photon"), "Electron", category));
                break;

            case "Sports":
                questionList.add(new QuestionModel("How many players in a football team?",
                        Arrays.asList("9", "10", "11", "12"), "11", category));
                questionList.add(new QuestionModel("In which sport is the term 'home run' used?",
                        Arrays.asList("Cricket", "Baseball", "Football", "Tennis"), "Baseball", category));
                questionList.add(new QuestionModel("Which country won the first FIFA World Cup?",
                        Arrays.asList("Brazil", "Germany", "Uruguay", "Italy"), "Uruguay", category));
                questionList.add(new QuestionModel("The Olympics are held every how many years?",
                        Arrays.asList("2", "3", "4", "5"), "4", category));
                questionList.add(new QuestionModel("Which sport uses a shuttlecock?",
                        Arrays.asList("Tennis", "Badminton", "Squash", "Table Tennis"), "Badminton", category));
                questionList.add(new QuestionModel("In tennis, what is a score of zero called?",
                        Arrays.asList("Love", "Zero", "Nil", "Duck"), "Love", category));
                questionList.add(new QuestionModel("Michael Jordan was famous in which sport?",
                        Arrays.asList("Football", "Basketball", "Baseball", "Golf"), "Basketball", category));
                questionList.add(new QuestionModel("How many points is a touchdown worth in American football?",
                        Arrays.asList("3", "6", "7", "1"), "6", category));
                questionList.add(new QuestionModel("Which sport is known as the 'king of sports'?",
                        Arrays.asList("Football", "Basketball", "Cricket", "Hockey"), "Football", category));
                questionList.add(new QuestionModel("What color card is shown for a foul in soccer?",
                        Arrays.asList("Red", "Blue", "Green", "Yellow"), "Red", category));
                break;

            case "Technology":
                questionList.add(new QuestionModel("Android is developed by?",
                        Arrays.asList("Apple", "Microsoft", "Google", "IBM"), "Google", category));
                questionList.add(new QuestionModel("What does CPU stand for?",
                        Arrays.asList("Central Process Unit", "Central Processing Unit", "Computer Personal Unit", "Central Processor Unit"), "Central Processing Unit", category));
                questionList.add(new QuestionModel("HTML stands for?",
                        Arrays.asList("HyperText Markup Language", "HyperText Makeup Language", "HyperText Mark Language", "HyperText Markdown Language"), "HyperText Markup Language", category));
                questionList.add(new QuestionModel("Which company created the iPhone?",
                        Arrays.asList("Apple", "Samsung", "Nokia", "Sony"), "Apple", category));
                questionList.add(new QuestionModel("RAM stands for?",
                        Arrays.asList("Random Access Memory", "Read Access Memory", "Run Access Memory", "Random Actual Memory"), "Random Access Memory", category));
                questionList.add(new QuestionModel("What is the main language used for Android development?",
                        Arrays.asList("Java", "Python", "Swift", "Kotlin"), "Kotlin", category));
                questionList.add(new QuestionModel("What does USB stand for?",
                        Arrays.asList("Universal Serial Bus", "Universal Service Bus", "Unified Serial Bus", "Universal System Bus"), "Universal Serial Bus", category));
                questionList.add(new QuestionModel("Which protocol is used to send emails?",
                        Arrays.asList("HTTP", "SMTP", "FTP", "POP3"), "SMTP", category));
                questionList.add(new QuestionModel("The first electronic computer was called?",
                        Arrays.asList("ENIAC", "UNIVAC", "EDSAC", "ABC"), "ENIAC", category));
                questionList.add(new QuestionModel("What is an IP address used for?",
                        Arrays.asList("Identifying a device on the internet", "Locating a file", "Programming a device", "Managing software"), "Identifying a device on the internet", category));
                break;

            default:
                // Fallback: add some default questions or empty list
                break;
        }
    }

    private void showQuestion() {
        resetOptionColors();
        btnNext.setEnabled(false);
        selectedAnswer = "";

        QuestionModel currentQuestion = questionList.get(currentQuestionIndex);

        tvQuestion.setText(currentQuestion.getQuestion());
        tvQuestionCounter.setText((currentQuestionIndex + 1) + "/" + questionList.size());
        progressBar.setProgress((int) (((float) (currentQuestionIndex + 1) / questionList.size()) * 100));

        btnOption1.setText(currentQuestion.getOptions().get(0));
        btnOption2.setText(currentQuestion.getOptions().get(1));
        btnOption3.setText(currentQuestion.getOptions().get(2));
        btnOption4.setText(currentQuestion.getOptions().get(3));

        btnNext.setText("Submit");
        setOptionsEnabled(true);
    }

    private void checkAnswerAndShowColors() {
        String correctAnswer = questionList.get(currentQuestionIndex).getCorrectAnswer();

        resetOptionColors();

        // Color correct answer green
        colorOptionButton(correctAnswer, Color.parseColor("#4CAF50"));

        // If selected answer is wrong, color it red
        if (!selectedAnswer.equals(correctAnswer)) {
            colorOptionButton(selectedAnswer, Color.parseColor("#F44336"));
        } else {
            score++;
        }
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

    private void resetOptionColors() {
        btnOption1.setBackgroundColor(Color.TRANSPARENT);
        btnOption2.setBackgroundColor(Color.TRANSPARENT);
        btnOption3.setBackgroundColor(Color.TRANSPARENT);
        btnOption4.setBackgroundColor(Color.TRANSPARENT);
    }
}
