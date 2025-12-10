package com.example.quizio.observer;

import java.util.ArrayList;
import java.util.List;

// Observable class
public class QuizObservable {
    public interface QuizObserver {
        void onScoreUpdated(int newScore);
        void onTimeUpdated(int secondsLeft);
        void onQuestionChanged(int questionIndex);
    }
    private final List<QuizObserver> observers = new ArrayList<>();

    public void registerObserver(QuizObserver observer) { observers.add(observer); }
    public void removeObserver(QuizObserver observer) { observers.remove(observer); }

    public void notifyScoreChanged(int newScore) {
        for (QuizObserver obs : observers) obs.onScoreUpdated(newScore);
    }

    public void notifyTimeChanged(int secondsLeft) {
        for (QuizObserver obs : observers) obs.onTimeUpdated(secondsLeft);
    }

    public void notifyQuestionChanged(int questionIndex) {
        for (QuizObserver obs : observers) obs.onQuestionChanged(questionIndex);
    }
}
