package com.example.quizio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private Button play;
    private Button setting;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        play = findViewById(R.id.buttonPlay);
        setting = findViewById(R.id.buttonSettings);
        exit = findViewById(R.id.buttonExit);

        // Play button - Go to PlayActivity
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, PlayActivity.class);
                startActivity(intent);
            }
        });

        // Settings button - Go to SettingsActivity
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        // Exit button - Close app
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }
}
