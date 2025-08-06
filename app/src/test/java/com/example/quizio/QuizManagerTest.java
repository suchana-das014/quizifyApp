package com.example.quizio;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QuizManagerTest {

    private QuizManager quizManager;

    @Before
    public void setUp() {
        List<QuestionModel> questions = Arrays.asList(
                new QuestionModel("Capital of France?",
                        Arrays.asList("Paris", "London", "Rome", "Berlin"),
                        "Paris", "General Knowledge"),
                new QuestionModel("Largest planet?",
                        Arrays.asList("Mars", "Jupiter", "Saturn", "Venus"),
                        "Jupiter", "General Knowledge")
        );

        quizManager = new QuizManager(questions);
    }

    @Test
    public void testCorrectAnswerIncreasesScore() {
        assertTrue(quizManager.submitAnswer("Paris"));
        assertEquals(1, quizManager.getScore());
    }

    @Test
    public void testWrongAnswerDoesNotIncreaseScore() {
        assertFalse(quizManager.submitAnswer("London"));
        assertEquals(0, quizManager.getScore());
    }

    @Test
    public void testMoveToNextQuestion() {
        quizManager.moveToNext();
        assertEquals("Largest planet?", quizManager.getCurrentQuestion().getQuestion());
    }

    @Test
    public void testTotalQuestionCount() {
        assertEquals(2, quizManager.getTotalQuestions());
    }
}