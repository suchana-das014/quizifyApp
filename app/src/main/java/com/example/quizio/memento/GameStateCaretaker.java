package com.example.quizio.memento;

public class GameStateCaretaker {

    private GameStateMemento savedState;

    public void save(GameStateMemento memento) {
        this.savedState = memento;
    }

    public GameStateMemento restore() {
        return savedState;
    }
}
