package com.example.quizio;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResultUtilsTest {

    @Test
    public void testPerfectScoreMessage() {
        String message = ResultUtils.getResultMessage(10, 10);
        assertEquals("Perfect! You scored 100%", message);
    }

    @Test
    public void testGreatJobMessage() {
        String message = ResultUtils.getResultMessage(8, 10);
        assertEquals("Great job! You scored 80%", message);
    }

    @Test
    public void testGoodTryMessage() {
        String message = ResultUtils.getResultMessage(6, 10);
        assertEquals("Good try! You scored 60%", message);
    }

    @Test
    public void testKeepPracticingMessage() {
        String message = ResultUtils.getResultMessage(3, 10);
        assertEquals("Keep practicing! You scored 30%", message);
    }
}