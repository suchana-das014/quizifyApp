package com.example.quizio.memento;

import com.example.quizio.models.QuestionModel;
import java.util.List;

public class GameStateMemento {

    private final int currentIndex;
    private final int score;
    private final List<QuestionModel> questions;

    public GameStateMemento(int currentIndex, int score, List<QuestionModel> questions) {
        this.currentIndex = currentIndex;
        this.score = score;
        this.questions = questions;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getScore() {
        return score;
    }

    public List<QuestionModel> getQuestions() {
        return questions;
    }
}
