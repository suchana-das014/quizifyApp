package com.example.quizio;

class QuizManager {
    private static QuizManager instance;
    private QuizManager() {}
    public static QuizManager getInstance() {
        if (instance == null) {
            instance = new QuizManager();
        }
        return instance;
    }

    public void startQuiz() {
        System.out.println("Quiz has started!");
    }
}

public class SingletonPatternDemo {
    public static void main(String[] args) {

        QuizManager manager1 = QuizManager.getInstance();
        manager1.startQuiz();

        QuizManager manager2 = QuizManager.getInstance();

        System.out.println(manager1 == manager2);
    }
}
