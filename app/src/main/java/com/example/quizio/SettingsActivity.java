package com.example.quizio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchSound, switchDarkMode;
    private Button buttonResetScore, buttonAbout, buttonBack;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences("QuizioPrefs", MODE_PRIVATE);
        editor = preferences.edit();

        switchSound = findViewById(R.id.switchSound);
        switchDarkMode = findViewById(R.id.switchDarkMode);
        buttonResetScore = findViewById(R.id.buttonResetScore);
        buttonAbout = findViewById(R.id.buttonAbout);
        buttonBack = findViewById(R.id.buttonBack);

        // Load saved settings
        switchSound.setChecked(preferences.getBoolean("soundEnabled", true));
        switchDarkMode.setChecked(preferences.getBoolean("darkModeEnabled", false));

        // Sound switch toggle
        switchSound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("soundEnabled", isChecked);
            editor.apply();
            Toast.makeText(this, "Sound " + (isChecked ? "enabled" : "disabled"), Toast.LENGTH_SHORT).show();
        });

        // Dark mode toggle
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("darkModeEnabled", isChecked);
            editor.apply();
            Toast.makeText(this, "Dark Mode " + (isChecked ? "enabled" : "disabled"), Toast.LENGTH_SHORT).show();
        });

        // Reset score button
        buttonResetScore.setOnClickListener(v -> {
            editor.putInt("highScore", 0);
            editor.apply();
            Toast.makeText(this, "Score reset successfully!", Toast.LENGTH_SHORT).show();
        });

        // About button (optional)
        buttonAbout.setOnClickListener(v -> {
            Toast.makeText(this, "Quizio App v1.0\nDeveloped by You!", Toast.LENGTH_LONG).show();
        });

        // Back button
        buttonBack.setOnClickListener(v -> finish());
    }
}
