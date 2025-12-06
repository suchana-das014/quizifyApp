package com.example.quizio;

import com.example.quizio.QuestionModel;
import java.util.ArrayList;
import java.util.List;

// ===== Base Component =====
interface QuizComponent {
    void display(); // Display category or question
}

// ===== Composite Class =====
class QuizCategory implements QuizComponent {
    private final String name;
    private final List<QuizComponent> children = new ArrayList<>();

    public QuizCategory(String name) {
        this.name = name;
    }

    public void addComponent(QuizComponent component) {
        children.add(component);
    }

    public void removeComponent(QuizComponent component) {
        children.remove(component);
    }

    @Override
    public void display() {
        System.out.println("Category: " + name);
        for (QuizComponent child : children) {
            child.display();
        }
    }

    public List<QuizComponent> getChildren() {
        return children;
    }
}

// ===== Leaf Class =====
class QuizQuestionLeaf implements QuizComponent {
    private final QuestionModel question;

    public QuizQuestionLeaf(QuestionModel question) {
        this.question = question;
    }

    @Override
    public void display() {
        System.out.println("Question: " + question.getQuestion());
    }

    public QuestionModel getQuestion() {
        return question;
    }
}
