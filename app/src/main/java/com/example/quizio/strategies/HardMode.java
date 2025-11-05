package com.example.quizio.strategies;

public class HardMode implements ScoringStrategy {
    @Override
    public int calculateScore(boolean isCorrect, int baseScore) {
        // Double score for correct answers, minus penalty for wrong ones
        return isCorrect ? baseScore * 2 : -baseScore;
    }
}