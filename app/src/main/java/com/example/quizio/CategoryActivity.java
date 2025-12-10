package com.example.quizio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quizio.composite.CategoryComponent;
import com.example.quizio.composite.QuizManager;

public class CategoryActivity extends AppCompatActivity {

    private Button btnGK, btnScience, btnSports, btnTech, btnCompositeDemo;
    private QuizManager quizManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Initialize QuizManager with Composite Pattern
        quizManager = new QuizManager();

        btnGK = findViewById(R.id.btnCategoryGK);
        btnScience = findViewById(R.id.btnCategoryScience);
        btnSports = findViewById(R.id.btnCategorySports);
        btnTech = findViewById(R.id.btnCategoryTech);
        btnCompositeDemo = findViewById(R.id.btnCompositeDemo);

        btnGK.setOnClickListener(v -> startQuiz("General Knowledge"));
        btnScience.setOnClickListener(v -> startQuiz("Science"));
        btnSports.setOnClickListener(v -> startQuiz("Sports"));
        btnTech.setOnClickListener(v -> startQuiz("Technology"));


        // Show total questions using composite pattern
        showStatistics();
    }

    private void startQuiz(String category) {
        Intent intent = new Intent(CategoryActivity.this, PlayActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    private void showStatistics() {
        int totalQuestions = quizManager.getTotalQuestionCount();
        Toast.makeText(this, "Total questions in system: " + totalQuestions,
                Toast.LENGTH_SHORT).show();
    }
}