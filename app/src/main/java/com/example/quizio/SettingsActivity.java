package com.example.quizio;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {

    private Button buttonAddQuestion, buttonResetScore, buttonAbout, buttonBack;
    private Switch switchDarkMode;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getSharedPreferences("quiz_prefs", MODE_PRIVATE);

        buttonAddQuestion = findViewById(R.id.buttonAddQuestion);
        buttonResetScore = findViewById(R.id.buttonResetScore);
        buttonAbout = findViewById(R.id.buttonAbout);
        buttonBack = findViewById(R.id.buttonBack);
        switchDarkMode = findViewById(R.id.switchDarkMode);

        // Load saved preference
        switchDarkMode.setChecked(prefs.getBoolean("dark_mode_enabled", false));

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("dark_mode_enabled", isChecked).apply();

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            Toast.makeText(this, "Dark mode " + (isChecked ? "enabled" : "disabled"), Toast.LENGTH_SHORT).show();

            // Recreate activity to apply theme change immediately
            recreate();
        });

        buttonAddQuestion.setOnClickListener(v -> {
            Toast.makeText(this, "Add Question clicked (feature coming soon)", Toast.LENGTH_SHORT).show();
        });

        buttonResetScore.setOnClickListener(v -> {
            prefs.edit().remove("user_score").apply();
            Toast.makeText(this, "Score reset!", Toast.LENGTH_SHORT).show();
        });

        buttonAbout.setOnClickListener(v -> {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("About Quizio")
                    .setMessage("Quizio v1.0\nA Simple QuizApp\nEnjoy the quiz !")
                    .setPositiveButton("OK", null)
                    .show();
        });

        buttonBack.setOnClickListener(v -> finish());
    }
}