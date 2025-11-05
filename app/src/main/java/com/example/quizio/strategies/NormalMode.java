package com.example.quizio.strategies;


public class NormalMode implements ScoringStrategy {
    @Override
    public int calculateScore(boolean isCorrect, int baseScore) {
        return isCorrect ? baseScore : 0;
    }
}