package com.example.quizio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvScore, tvHighScore, tvPerformance;
    private Button btnBack, btnRestart, btnExit;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize views
        tvScore = findViewById(R.id.tvScore);
        tvHighScore = findViewById(R.id.tvHighScore);
        tvPerformance = findViewById(R.id.tvPerformance);
        btnBack = findViewById(R.id.btnBack);
        btnRestart = findViewById(R.id.btnRestart);
        btnExit = findViewById(R.id.btnExit);

        // Initialize SharedPreferences
        prefs = getSharedPreferences("quiz_prefs", MODE_PRIVATE);

        // Get data from intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int total = intent.getIntExtra("total", 10);
        String category = intent.getStringExtra("category");

        if (category == null) {
            category = "General Knowledge";
        }

        // Display current score
        tvScore.setText("Your Score: " + score + " / " + total);

        // Calculate percentage
        int percentage = (score * 100) / total;

        // Set performance message
        String performanceMessage;
        if (percentage >= 90) {
            performanceMessage = "Excellent! 🎉";
        } else if (percentage >= 70) {
            performanceMessage = "Great Job! 👍";
        } else if (percentage >= 50) {
            performanceMessage = "Good! 😊";
        } else {
            performanceMessage = "Keep Practicing! 💪";
        }
        tvPerformance.setText(performanceMessage);

        // Update and display high score
        updateHighScore(category, score, total);

        // Button click listeners
        btnBack.setOnClickListener(v -> {
            // Go back to CategoryActivity
            Intent categoryIntent = new Intent(ResultActivity.this, CategoryActivity.class);
            categoryIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear back stack
            startActivity(categoryIntent);
            finish();
        });

        String finalCategory = category;
        btnRestart.setOnClickListener(v -> {
            // Restart quiz with same category
            Intent restartIntent = new Intent(ResultActivity.this, PlayActivity.class);
            restartIntent.putExtra("category", finalCategory);
            restartIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(restartIntent);
            finish();
        });

        btnExit.setOnClickListener(v -> {
            // Exit to MainActivity
            Intent mainIntent = new Intent(ResultActivity.this, MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);
            finishAffinity(); // Close all activities
        });
    }

    private void updateHighScore(String category, int currentScore, int total) {
        // Create a unique key for each category
        String key = "high_score_" + category.replace(" ", "_");
        int previousHighScore = prefs.getInt(key, 0);

        if (currentScore > previousHighScore) {
            // Save new high score
            prefs.edit().putInt(key, currentScore).apply();

            // Show congratulatory message
            Toast.makeText(this, "🎉 New High Score in " + category + "!",
                    Toast.LENGTH_LONG).show();

            // Update display
            tvHighScore.setText("Best Score: " + currentScore + "/" + total);
        } else {
            // Show existing high score
            tvHighScore.setText("Best Score: " + previousHighScore + "/" + total);
        }
    }

    @Override
    public void onBackPressed() {
        // Override back button to go to CategoryActivity
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}