package com.example.quizio;

import org.junit.Test;

import static org.junit.Assert.fail;

public class QuestionValidatorTest {

    @Test
    public void testAllQuestionsFromQuestionBank() {
        for (QuestionModel question : QuestionBank.getAllQuestions()) {
            try {
                QuestionValidator.validate(question);
            } catch (IllegalArgumentException e) {
                fail("Failed validation: " + question.getQuestion() + "\nReason: " + e.getMessage());
            }
        }
    }
}
