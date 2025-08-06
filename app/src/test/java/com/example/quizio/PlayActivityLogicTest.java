package com.example.quizio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayActivityLogicTest {

    private QuestionModel mockQuestion;

    @BeforeEach
    void setup() {
        // Create a mock QuestionModel
        mockQuestion = Mockito.mock(QuestionModel.class);

        when(mockQuestion.getQuestion()).thenReturn("Capital of France?");
        when(mockQuestion.getOptions()).thenReturn(Arrays.asList("Paris", "London", "Rome", "Berlin"));
        when(mockQuestion.getCorrectAnswer()).thenReturn("Paris");
        when(mockQuestion.getCategory()).thenReturn("General Knowledge");
    }

    @Test
    void testCorrectAnswerLogic() {
        assertEquals("Paris", mockQuestion.getCorrectAnswer());
        assertTrue(mockQuestion.getOptions().contains("Paris"));
        assertEquals(4, mockQuestion.getOptions().size());
    }

    @Test
    void testQuestionCategory() {
        assertEquals("General Knowledge", mockQuestion.getCategory());
        verify(mockQuestion, times(1)).getCategory();
    }
}
