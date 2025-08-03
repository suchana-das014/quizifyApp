package com.example.quizio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Button buttonPlay, buttonSettings, buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        buttonPlay = findViewById(R.id.buttonPlay);
        buttonSettings = findViewById(R.id.buttonSettings);
        buttonExit = findViewById(R.id.buttonExit);

        buttonPlay.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, PlayActivity.class);
            startActivity(intent);
        });

        buttonSettings.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        buttonExit.setOnClickListener(v -> {
            finishAffinity(); // close app
        });
    }
}
