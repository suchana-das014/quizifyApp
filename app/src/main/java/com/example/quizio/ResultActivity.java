package com.example.quizio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvScore;
    private Button btnRestart, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvScore = findViewById(R.id.tvScore);
        btnRestart = findViewById(R.id.btnRestart);
        btnExit = findViewById(R.id.btnExit);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 10);

        tvScore.setText("Your Score: " + score + " / " + total);

        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, CategoryActivity.class);
            startActivity(intent);
            finish();
        });

        btnExit.setOnClickListener(v -> finishAffinity());
    }
}
