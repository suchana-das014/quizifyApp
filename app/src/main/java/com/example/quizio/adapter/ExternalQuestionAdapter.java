package com.example.quizio.adapter;

public class ExternalQuestionAdapter implements QuestionInterface {

    private ExternalQuestion externalQuestion;

    public ExternalQuestionAdapter(ExternalQuestion q) {
        this.externalQuestion = q;
    }

    @Override
    public String getQuestion() {
        return externalQuestion.title;
    }

    @Override
    public String getCorrectAnswer() {
        return externalQuestion.rightOption;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return externalQuestion.rightOption.trim().equalsIgnoreCase(answer.trim());
    }
}
