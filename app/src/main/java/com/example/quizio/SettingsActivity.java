package com.example.quizio;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Button buttonAddQuestion, buttonResetScore, buttonAbout, buttonBack;
    private Switch switchSound, switchDarkMode;

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
        switchSound = findViewById(R.id.switchSound);
        switchDarkMode = findViewById(R.id.switchDarkMode);

        // Load saved preferences for switches
        switchSound.setChecked(prefs.getBoolean("sound_enabled", true));
        switchDarkMode.setChecked(prefs.getBoolean("dark_mode_enabled", false));

        switchSound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("sound_enabled", isChecked).apply();
            Toast.makeText(this, "Sound " + (isChecked ? "enabled" : "disabled"), Toast.LENGTH_SHORT).show();
        });

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("dark_mode_enabled", isChecked).apply();
            Toast.makeText(this, "Dark mode " + (isChecked ? "enabled" : "disabled"), Toast.LENGTH_SHORT).show();
            // TODO: Implement actual theme switching here if needed
        });

        buttonAddQuestion.setOnClickListener(v -> {
            // TODO: Implement opening AddQuestionActivity or dialog here
            Toast.makeText(this, "Add Question clicked (feature coming soon)", Toast.LENGTH_SHORT).show();
        });

        buttonResetScore.setOnClickListener(v -> {
            prefs.edit().remove("user_score").apply();
            Toast.makeText(this, "Score reset!", Toast.LENGTH_SHORT).show();
        });

        buttonAbout.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("About Quizio")
                    .setMessage("Quizio v1.0\nA Simple QuizApp\nEnjoy the quiz !")
                    .setPositiveButton("OK", null)
                    .show();
        });

        buttonBack.setOnClickListener(v -> finish());
    }
}
