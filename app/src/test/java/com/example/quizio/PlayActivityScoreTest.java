package com.example.quizio;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayActivityScoreTest {

    private List<QuestionModel> questions;

    @BeforeAll
    void beforeAll() {
        System.out.println("=== Starting PlayActivityScoreTest Suite ===");
    }

    @AfterAll
    void afterAll() {
        System.out.println("=== Finished PlayActivityScoreTest Suite ===");
    }

    @BeforeEach
    void setup() {
        System.out.println("Setting up test data...");
        questions = Arrays.asList(
                new QuestionModel("Capital of France?", Arrays.asList("Paris", "London", "Rome", "Berlin"), "Paris", "GK"),
                new QuestionModel("Largest planet?", Arrays.asList("Mars", "Jupiter", "Venus", "Earth"), "Jupiter", "Science")
        );
    }

    @AfterEach
    void tearDown() {
        System.out.println("Cleaning up after test...");
        questions = null; // Explicitly clear reference
    }

    @Test
    void testScoreIncrement() {
        int score = 0;
        if ("Paris".equals(questions.get(0).getCorrectAnswer())) score++;
        if ("Jupiter".equals(questions.get(1).getCorrectAnswer())) score++;

        assertEquals(2, score, "Score should increment correctly for correct answers");
    }

    @Test
    void testProgressCalculation() {
        int progress = (1 * 100) / questions.size(); // After answering 1 question
        assertEquals(50, progress, "Progress should be 50% after one question answered");
    }
}
