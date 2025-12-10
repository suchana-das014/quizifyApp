package com.example.quizio.iterator;

public interface QuestionContainer {
    QuestionIterator getIterator();
    QuestionIterator getShuffledIterator();
    int getQuestionCount();
}