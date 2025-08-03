package com.example.quizio;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResultUtilsTest {

    @Test
    public void testPerfectScoreMessage() {
        String message = ResultUtilsTest.getResultMessage(10, 10);
        assertEquals("Perfect! You scored 100%", message);
    }

    private static String getResultMessage(int i, int i1) {
    }

    @Test
    public void testGreatJobMessage() {
        String message = ResultUtilsTest.getResultMessage(8, 10);
        assertEquals("Great job! You scored 80%", message);
    }

    @Test
    public void testGoodTryMessage() {
        String message = ResultUtilsTest.getResultMessage(6, 10);
        assertEquals("Good try! You scored 60%", message);
    }

    @Test
    public void testKeepPracticingMessage() {
        String message = ResultUtilsTest.getResultMessage(3, 10);
        assertEquals("Keep practicing! You scored 30%", message);
    }
}