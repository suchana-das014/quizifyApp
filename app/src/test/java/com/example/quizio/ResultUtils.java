package com.example.quizio;

public class ResultUtils {

    public static String getResultMessage(int score, int total) {
        int percentage = (score * 100) / total;

        if (percentage == 100) {
            return "Perfect! You scored 100%";
        } else if (percentage >= 80) {
            return "Great job! You scored " + percentage + "%";
        } else if (percentage >= 50) {
            return "Good try! You scored " + percentage + "%";
        } else {
            return "Keep practicing! You scored " + percentage + "%";
        }
    }
}