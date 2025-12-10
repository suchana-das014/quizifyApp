package com.example.quizio.composite;

import java.util.ArrayList;
import java.util.List;

public class CategoryComponent implements FileSystemComponent {

    private String name;
    private List<FileSystemComponent> children;

    public CategoryComponent(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addComponent(FileSystemComponent component) {
        children.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        children.remove(component);
    }

    public List<FileSystemComponent> getChildren() {
        return children;
    }

    @Override
    public void displayDetails() {
        System.out.println("Category: " + name);
        System.out.println("Total Questions: " + getQuestionCount());
        System.out.println("Sub-items: " + children.size());
        System.out.println("Contents:");
        for (FileSystemComponent component : children) {
            component.displayDetails();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getQuestionCount() {
        int count = 0;
        for (FileSystemComponent component : children) {
            count += component.getQuestionCount();
        }
        return count;
    }

    @Override
    public boolean isCategory() {
        return true;
    }

    public List<QuestionComponent> getAllQuestions() {
        List<QuestionComponent> allQuestions = new ArrayList<>();
        for (FileSystemComponent component : children) {
            if (component instanceof QuestionComponent) {
                allQuestions.add((QuestionComponent) component);
            } else if (component instanceof CategoryComponent) {
                allQuestions.addAll(((CategoryComponent) component).getAllQuestions());
            }
        }
        return allQuestions;
    }
}