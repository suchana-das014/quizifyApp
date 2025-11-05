package com.example.quizio.strategies;

public class TimedMode implements ScoringStrategy {
    private long timeTaken;

    public TimedMode() {
        this.timeTaken = timeTaken;
    }

    @Override
    public int calculateScore(boolean isCorrect, int baseScore) {
        if (!isCorrect) return 0;
        // Bonus for answering under 10 seconds
        return (timeTaken < 10) ? baseScore * 2 : baseScore;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }
}