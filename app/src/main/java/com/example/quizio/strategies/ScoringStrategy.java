package com.example.quizio.strategies;

public interface ScoringStrategy {
    int calculateScore(boolean isCorrect, int baseScore);
}