package com.example.quizio.composite;

import com.example.quizio.models.QuestionFactory;
import com.example.quizio.models.QuestionModel;
import java.util.Arrays;
import java.util.List;

public class QuizManager {

    private CategoryComponent rootCategory;

    public QuizManager() {
        // Create root category
        rootCategory = new CategoryComponent("Quiz Categories");

        // Build category hierarchy
        initializeCategories();
    }

    private void initializeCategories() {
        // Create main categories
        CategoryComponent generalKnowledge = new CategoryComponent("General Knowledge");
        CategoryComponent science = new CategoryComponent("Science");
        CategoryComponent sports = new CategoryComponent("Sports");
        CategoryComponent technology = new CategoryComponent("Technology");

        // Add General Knowledge questions
        generalKnowledge.addComponent(new QuestionComponent(
                QuestionFactory.createQuestion("MCQ", "Capital of France?",
                        Arrays.asList("Paris", "Berlin", "London", "Madrid"), "Paris", "General Knowledge")
        ));
        generalKnowledge.addComponent(new QuestionComponent(
                QuestionFactory.createQuestion("MCQ", "Largest planet?",
                        Arrays.asList("Earth", "Jupiter", "Mars", "Saturn"), "Jupiter", "General Knowledge")
        ));
        generalKnowledge.addComponent(new QuestionComponent(
                QuestionFactory.createQuestion("TrueFalse", "The Earth is flat?",
                        null, "False", "General Knowledge")
        ));

        // Add Science questions
        science.addComponent(new QuestionComponent(
                QuestionFactory.createQuestion("MCQ", "Chemical symbol for water?",
                        Arrays.asList("H2O", "CO2", "O2", "NaCl"), "H2O", "Science")
        ));
        science.addComponent(new QuestionComponent(
                QuestionFactory.createQuestion("TrueFalse", "Humans have 206 bones?",
                        null, "True", "Science")
        ));

        // Add Sports questions
        sports.addComponent(new QuestionComponent(
                QuestionFactory.createQuestion("MCQ", "Players in football team?",
                        Arrays.asList("9", "10", "11", "12"), "11", "Sports")
        ));

        // Add Technology questions
        technology.addComponent(new QuestionComponent(
                QuestionFactory.createQuestion("MCQ", "Android developed by?",
                        Arrays.asList("Apple", "Microsoft", "Google", "Samsung"), "Google", "Technology")
        ));
        technology.addComponent(new QuestionComponent(
                QuestionFactory.createQuestion("TrueFalse", "HTML is a programming language?",
                        null, "False", "Technology")
        ));

        CategoryComponent programming = new CategoryComponent("Programming");
        programming.addComponent(new QuestionComponent(
                QuestionFactory.createQuestion("MCQ", "Which is not a programming language?",
                        Arrays.asList("Java", "Python", "HTML", "C++"), "HTML", "Technology")
        ));
        programming.addComponent(new QuestionComponent(
                QuestionFactory.createQuestion("MCQ", "Inventor of Java?",
                        Arrays.asList("James Gosling", "Bjarne Stroustrup", "Guido van Rossum", "Dennis Ritchie"),
                        "James Gosling", "Technology")
        ));

        technology.addComponent(programming);

        // Add all categories to root
        rootCategory.addComponent(generalKnowledge);
        rootCategory.addComponent(science);
        rootCategory.addComponent(sports);
        rootCategory.addComponent(technology);
    }

    public CategoryComponent getRootCategory() {
        return rootCategory;
    }

    public List<QuestionComponent> getAllQuestions() {
        return rootCategory.getAllQuestions();
    }

    public List<QuestionComponent> getQuestionsByCategory(String categoryName) {
        for (FileSystemComponent component : rootCategory.getChildren()) {
            if (component instanceof CategoryComponent && component.getName().equals(categoryName)) {
                return ((CategoryComponent) component).getAllQuestions();
            }
        }
        return null;
    }

    public void displayQuizStructure() {
        rootCategory.displayDetails();
    }

    public int getTotalQuestionCount() {
        return rootCategory.getQuestionCount();
    }
}