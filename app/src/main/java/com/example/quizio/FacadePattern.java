package com.example.quizio;

import com.example.quizio.models.QuestionModel;
import com.example.quizio.strategies.ScoringStrategy;

import java.util.List;

public class FacadePattern {

    public void start() {
    }

    public void nextQuestion() {
    }

    public List<String> getSelectedAnswers() {

        return java.util.Collections.emptyList();
    }

    public interface QuizListener {
        void onQuestionChanged(QuestionModel question, int index, int total);

        void onQuestionLoaded(QuestionModel question, int index, int total);

        void onAnswerChecked(boolean isCorrect, int pointsEarned);

        void onQuestionChanged(com.example.quizio.QuestionModel question, int index, int total);

        void onQuizFinished(int finalScore);
    }

    private static List<QuestionModel> loadedQuestions;
    private ScoringStrategy scoringStrategy;

    private int currentQuestionIndex = 0;
    private int score = 0;
    private long questionStartTime;

    private QuizListener listener;

    // Constructor you need
    public FacadePattern(List<QuestionModel> loadedQuestions, ScoringStrategy scoringStrategy) {
        this.loadedQuestions = loadedQuestions;
        this.scoringStrategy = scoringStrategy;
    }

    // Listener setter called from PlayActivity
    public void setListener(QuizListener listener) {
        this.listener = listener;
    }

    // Start Quiz
    public void startQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        questionStartTime = System.currentTimeMillis();

        if (listener != null)
            listener.onQuestionChanged(getCurrentQuestion(), currentQuestionIndex, loadedQuestions.size());
    }

    public QuestionModel getCurrentQuestion() {
        return loadedQuestions.get(currentQuestionIndex);
    }

    public static int getTotalQuestions() {
        return loadedQuestions.size();
    }

    public int getCurrentIndex() {
        return currentQuestionIndex;
    }

    public int getScore() {
        return score;
    }

    // Handle answer selection
    public void submitAnswer(String selectedOption) {

        QuestionModel current = getCurrentQuestion();
        boolean isCorrect = selectedOption.equals(current.getCorrectAnswer());

        int timeTaken = (int) (System.currentTimeMillis() - questionStartTime);
        int points = scoringStrategy.calculateScore(isCorrect, timeTaken);

        score += points;

        if (listener != null)
            listener.onAnswerChecked(isCorrect, points);
    }

    // Move to next question
    public void goToNextQuestion() {

        currentQuestionIndex++;
        questionStartTime = System.currentTimeMillis();

        if (currentQuestionIndex < loadedQuestions.size()) {
            if (listener != null)
                listener.onQuestionChanged(getCurrentQuestion(), currentQuestionIndex, loadedQuestions.size());
        } else {
            if (listener != null)
                listener.onQuizFinished(score);
        }
    }
}
