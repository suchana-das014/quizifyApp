package com.example.quizio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quizio.utils.HighScoreManager;

public class ResultActivity extends AppCompatActivity {

    private TextView tvScore, tvHighScore;
    private Button btnRestart, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvScore = findViewById(R.id.tvScore);
        tvHighScore = findViewById(R.id.tvHighScore);
        btnRestart = findViewById(R.id.btnRestart);
        btnExit = findViewById(R.id.btnExit);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 10);
        String category = getIntent().getStringExtra("category");
        if (category == null || category.isEmpty()) category = "General Knowledge";


        tvScore.setText("Your Score: " + score + " / " + total);

        // HighScore save (Singleton Pattern)
        HighScoreManager.getInstance(this).saveHighScore(category, score);

        // Best Score
        int best = HighScoreManager.getInstance(this).getHighScore(category);
        tvHighScore.setText("Best in " + category + ": " + best + " / " + total);

        btnRestart.setOnClickListener(v -> {
            startActivity(new Intent(this, CategoryActivity.class));
            finish();
        });

        btnExit.setOnClickListener(v -> finishAffinity());
    }
}