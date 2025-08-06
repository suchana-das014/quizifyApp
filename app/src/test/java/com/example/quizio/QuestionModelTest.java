package com.example.quizio;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionModelTest {

    private List<String> options;

    @BeforeAll
    void beforeAll() {
        System.out.println(">>> Starting QuestionModel tests...");
    }

    @BeforeEach
    void setUp() {
        options = Arrays.asList("Option1", "Option2", "Option3", "Option4");
        System.out.println("-> Running a test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("-> Finished a test.");
    }

    @AfterAll
    void afterAll() {
        System.out.println(">>> All QuestionModel tests completed.");
    }

    @Test
    public void testQuestionModelConstructorAndGetters() {
        String question = "What is the capital of France?";
        String correctAnswer = "Option1";
        String category = "General Knowledge";

        QuestionModel model = new QuestionModel(question, options, correctAnswer, category);

        assertEquals(question, model.getQuestion());
        assertEquals(options, model.getOptions());
        assertEquals(correctAnswer, model.getCorrectAnswer());
        assertEquals(category, model.getCategory());

        assertNotNull(model.getOptions());
        assertTrue(model.getOptions().contains("Option1"));
        assertFalse(model.getOptions().contains("Madrid"));
    }

    @ParameterizedTest
    @CsvSource({
            "'What is 2 + 2?', '4', 'Math'",
            "'What color is the sky?', 'Blue', 'Science'",
            "'Which planet is known as Red Planet?', 'Mars', 'Space'"
    })
    public void testQuestionModelParameterized(String question, String correctAnswer, String category) {
        QuestionModel model = new QuestionModel(question, options, correctAnswer, category);

        assertEquals(question, model.getQuestion());
        assertEquals(correctAnswer, model.getCorrectAnswer());
        assertEquals(category, model.getCategory());
        assertTrue(model.getOptions().size() == 4);
    }

    @Test
    public void testEmptyModel() {
        QuestionModel model = new QuestionModel("", Arrays.asList("", "", "", ""), "", "");

        assertEquals("", model.getQuestion());
        assertEquals("", model.getCorrectAnswer());
        assertEquals("", model.getCategory());
        assertEquals(4, model.getOptions().size());
    }
}
