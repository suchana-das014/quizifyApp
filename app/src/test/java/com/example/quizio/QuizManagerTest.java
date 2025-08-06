package com.example.quizio;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // So @BeforeAll and @AfterAll can be non-static
class QuizManagerTest {

    private QuizManager quizManager;

    @BeforeAll
    void beforeAll() {
        System.out.println("Starting QuizManager tests...");
    }

    @AfterAll
    void afterAll() {
        System.out.println("Finished QuizManager tests.");
    }

    @BeforeEach
    void setUp() {
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

    @AfterEach
    void tearDown() {
        System.out.println("Test finished.");
    }

    @Test
    @DisplayName("Submitting correct answer increases score")
    void testCorrectAnswerIncreasesScore() {
        assertTrue(quizManager.submitAnswer("Paris"), "Correct answer should return true");
        assertEquals(1, quizManager.getScore(), "Score should increment after correct answer");
    }

    @Test
    @DisplayName("Submitting wrong answer does not increase score")
    void testWrongAnswerDoesNotIncreaseScore() {
        assertFalse(quizManager.submitAnswer("London"), "Wrong answer should return false");
        assertEquals(0, quizManager.getScore(), "Score should not increment after wrong answer");
    }

    @Test
    @DisplayName("Moving to next question updates current question")
    void testMoveToNextQuestion() {
        quizManager.moveToNext();
        assertEquals("Largest planet?", quizManager.getCurrentQuestion().getQuestion());
    }

    @Test
    @DisplayName("Total questions count is correct")
    void testTotalQuestionCount() {
        assertEquals(2, quizManager.getTotalQuestions());
    }

    @Test
    @DisplayName("Throws IndexOutOfBoundsException for invalid question index")
    void testThrowsExceptionForInvalidQuestionIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            quizManager.getQuestionAtIndex(100);
        });
    }

    @Test
    @DisplayName("Test string lines match example")
    void testAssertLinesMatch() {
        List<String> expected = Arrays.asList("Capital of France?", "Largest planet?");
        List<String> actual = Arrays.asList(
                quizManager.getQuestionAtIndex(0).getQuestion(),
                quizManager.getQuestionAtIndex(1).getQuestion()
        );
        assertLinesMatch(expected, actual, "Questions should match expected lines");
    }

    @ParameterizedTest(name = "Answer \"{1}\" to question \"{0}\" should be {2}")
    @CsvSource({
            "Capital of France?, Paris, true",
            "Capital of France?, London, false",
            "Largest planet?, Jupiter, true",
            "Largest planet?, Mars, false"
    })
    void testSubmitAnswerParameterized(String question, String answer, boolean expectedResult) {
        // Move to the question based on input string
        if (question.equals("Largest planet?")) {
            quizManager.moveToNext();
        }
        assertEquals(expectedResult, quizManager.submitAnswer(answer));
    }
}
