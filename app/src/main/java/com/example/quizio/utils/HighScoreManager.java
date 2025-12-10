package com.example.quizio.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class HighScoreManager {
    private static HighScoreManager instance;
    private final SharedPreferences prefs;

    private HighScoreManager(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences("quizio_scores", Context.MODE_PRIVATE);
    }

    public static HighScoreManager getInstance(Context context) {
        if (instance == null) {
            instance = new HighScoreManager(context);
        }
        return instance;
    }

    public void saveHighScore(String category, int score) {
        int currentBest = prefs.getInt(category, 0);
        if (score > currentBest) {
            prefs.edit().putInt(category, score).apply();
        }
    }

    public int getHighScore(String category) {
        return prefs.getInt(category, 0);
    }
}