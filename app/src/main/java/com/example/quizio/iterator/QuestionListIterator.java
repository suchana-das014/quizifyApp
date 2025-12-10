package com.example.quizio.iterator;

import com.example.quizio.models.QuestionModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionListIterator implements QuestionIterator {

    private List<QuestionModel> questions;
    private int currentIndex;
    private boolean isShuffled;

    public QuestionListIterator(List<QuestionModel> questions, boolean shuffle) {
        this.questions = new ArrayList<>(questions);
        this.currentIndex = 0;
        this.isShuffled = shuffle;

        if (shuffle) {
            Collections.shuffle(this.questions);
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < questions.size();
    }

    @Override
    public Object next() {
        return nextQuestion();
    }

    @Override
    public QuestionModel nextQuestion() {
        if (hasNext()) {
            QuestionModel question = questions.get(currentIndex);
            currentIndex++;
            return question;
        }
        return null;
    }

    @Override
    public QuestionModel currentQuestion() {
        if (currentIndex > 0 && currentIndex <= questions.size()) {
            return questions.get(currentIndex - 1);
        }
        return null;
    }

    @Override
    public int currentPosition() {
        return currentIndex;
    }

    @Override
    public void reset() {
        currentIndex = 0;
        if (isShuffled) {
            Collections.shuffle(questions);
        }
    }

    @Override
    public int getTotalQuestions() {
        return questions.size();
    }
}