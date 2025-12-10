package com.example.quizio.observer;

import java.util.ArrayList;
import java.util.List;

public class GameSubject {

    private final List<GameObserver> observers = new ArrayList<>();

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    protected void notifyQuestionChanged(int currentIndex, int total) {
        for (GameObserver obs : observers) {
            obs.onQuestionChanged(currentIndex, total);
        }
    }
}
