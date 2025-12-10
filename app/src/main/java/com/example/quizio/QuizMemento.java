package com.example.quizio;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// =========================
//     MEMENTO (Snapshot)
// =========================
class QuizMemento {
    private final int currentIndex;
    private final int score;
    private final List<String> selectedAnswers;

    public QuizMemento(int currentIndex, int score, List<String> selectedAnswers) {
        this.currentIndex = currentIndex;
        this.score = score;
        this.selectedAnswers = new ArrayList<>(selectedAnswers); // deep copy
    }

    public int getCurrentIndex() { return currentIndex; }
    public int getScore() { return score; }
    public List<String> getSelectedAnswers() {
        return new ArrayList<>(selectedAnswers);
    }
}

// =========================
//      CARETAKER
// =========================
class QuizCareTaker {
    private final Stack<QuizMemento> history = new Stack<>();

    public static QuizCareTaker getInstance() {
        return null;
    }

    public void save(QuizMemento memento) {
        history.push(memento);
    }

    public QuizMemento undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }

    public QuizMemento getMemento() {
        return null;
    }
}

// =========================
//      ORIGINATOR
// =========================
class QuizOriginator {
    private int currentIndex;
    private int score;
    private List<String> selectedAnswers = new ArrayList<>();

    public void setState(int index, int score, List<String> answers) {
        this.currentIndex = index;
        this.score = score;
        this.selectedAnswers = new ArrayList<>(answers);
    }

    public QuizMemento saveState() {
        return new QuizMemento(currentIndex, score, selectedAnswers);
    }

    public void restoreState(QuizMemento m) {
        this.currentIndex = m.getCurrentIndex();
        this.score = m.getScore();
        this.selectedAnswers = m.getSelectedAnswers();
    }

    public int getIndex() { return currentIndex; }
    public int getScore() { return score; }
    public List<String> getAnswers() { return selectedAnswers; }

    public void restore(QuizMemento lastSaved) {
    }

    public String getCurrentIndex() {
        return "";
    }
}
