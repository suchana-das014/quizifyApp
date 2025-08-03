package com.example.quizio;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QuizManagerTest {

    private QuizManager quizManager;

    @Before
    public void setup() {
        List<QuestionModel> questions = Arrays.asList(
                new QuestionModel("Capital of France?", Arrays.asList("Paris", "London", "Berlin", "Rome"), "Paris", "General Knowledge"),
                new QuestionModel("Largest planet?", Arrays.asList("Mars", "Jupiter", "Saturn", "Venus"), "Jupiter", "General Knowledge")
        );
        quizManager = new QuizManager(questions);
    }

    @Test
    public void testFirstQuestionCorrect() {
        assertEquals("Capital of France?", quizManager.getCurrentQuestion().getQuestion());
        assertTrue(quizManager.submitAnswer("Paris"));
        assertEquals(1, quizManager.getScore());
    }

    @Test
    public void testWrongAnswerNoScore() {
        assertFalse(quizManager.submitAnswer("London"));
        assertEquals(0, quizManager.getScore());
    }

    @Test
    public void testMoveToNextQuestion() {
        quizManager.moveToNext();
        assertEquals("Largest planet?", quizManager.getCurrentQuestion().getQuestion());
    }

    @Test
    public void testTotalQuestionsCount() {
        assertEquals(2, quizManager.getTotalQuestions());
    }
}
