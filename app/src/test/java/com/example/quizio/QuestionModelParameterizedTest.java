package com.example.quizio;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionModelParameterizedTest {

    @ParameterizedTest(name = "[{index}] {0} ({6})")
    @CsvFileSource(resources = "/questions.csv", numLinesToSkip = 1)
    public void testQuestionDataFromCSV(String question, String op1, String op2, String op3, String op4, String correct, String category) {
        List<String> options = Arrays.asList(op1, op2, op3, op4);

        QuestionModel q = new QuestionModel(question, options, correct, category);

        assertEquals(question, q.getQuestion());
        assertEquals(correct, q.getCorrectAnswer());
        assertEquals(category, q.getCategory());
        assertTrue(q.getOptions().contains(correct));
    }
}
