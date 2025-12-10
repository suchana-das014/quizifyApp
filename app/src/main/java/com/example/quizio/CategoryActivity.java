package com.example.quizio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {
//buttons
    private Button btnGK, btnScience, btnSports, btnTech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        btnGK = findViewById(R.id.btnCategoryGK);
        btnScience = findViewById(R.id.btnCategoryScience);
        btnSports = findViewById(R.id.btnCategorySports);
        btnTech = findViewById(R.id.btnCategoryTech);

        btnGK.setOnClickListener(v -> startQuiz("General Knowledge"));
        btnScience.setOnClickListener(v -> startQuiz("Science"));
        btnSports.setOnClickListener(v -> startQuiz("Sports"));
        btnTech.setOnClickListener(v -> startQuiz("Technology"));
    }

    private void startQuiz(String category) {
        Intent intent = new Intent(CategoryActivity.this, PlayActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}