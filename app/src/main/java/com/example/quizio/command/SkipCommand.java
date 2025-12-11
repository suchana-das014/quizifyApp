package com.example.quizio.command;

import com.example.quizio.models.QuestionModel;

public class SkipCommand implements Command {

    private QuestionModel question;
    private QuizReceiver receiver;
    private String description;

    public SkipCommand(QuestionModel question, QuizReceiver receiver) {
        this.question = question;
        this.receiver = receiver;
        this.description = "Skipped: " + question.getQuestion();
    }

    @Override
    public void execute() {
        receiver.skipQuestion(question);
    }

    @Override
    public void undo() {
        receiver.unskipQuestion(question);
    }

    @Override
    public String getDescription() {
        return description;
    }
}