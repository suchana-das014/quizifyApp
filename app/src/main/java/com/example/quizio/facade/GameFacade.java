package com.example.quizio.facade;

import com.example.quizio.models.MCQQuestion;
import com.example.quizio.models.QuestionFactory;
import com.example.quizio.models.QuestionModel;
import com.example.quizio.strategies.HardMode;
import com.example.quizio.strategies.NormalMode;
import com.example.quizio.strategies.ScoringStrategy;
import com.example.quizio.strategies.TimedMode;
import com.example.quizio.memento.GameStateMemento;
import com.example.quizio.observer.GameSubject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameFacade extends GameSubject {

    private List<QuestionModel> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int score = 0;
    private ScoringStrategy scoringStrategy;
    private long questionStartTime;

    //  FACADE INITIALIZATION

    public GameFacade(String gameMode) {
        setMode(gameMode);
    }

    public void setMode(String mode) {
        switch (mode) {
            case "hard":
                scoringStrategy = new HardMode();
                break;

            case "timed":
                scoringStrategy = new TimedMode();
                break;

            default:
                scoringStrategy = new NormalMode();
                break;
        }
    }

    //  QUESTIONS HANDLING

    public void loadQuestions(String category) {
        questions.clear();

        switch (category) {
            case "General Knowledge":
                questions.add(QuestionFactory.createQuestion("MCQ", "Capital of France?",
                        new ArrayList<>(Arrays.asList("Paris", "London", "Rome", "Berlin")), "Paris", category));
                questions.add(QuestionFactory.createQuestion("MCQ", "Largest planet?",
                        new ArrayList<>(Arrays.asList("Mars", "Jupiter", "Saturn", "Venus")), "Jupiter", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Who wrote 'Romeo and Juliet'?",
                        new ArrayList<>(Arrays.asList("Shakespeare", "Dickens", "Twain", "Austen")), "Shakespeare", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Currency of Japan?",
                        new ArrayList<>(Arrays.asList("Yuan", "Yen", "Won", "Rupee")), "Yen", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Tallest mountain?",
                        new ArrayList<>(Arrays.asList("K2", "Everest", "Kangchenjunga", "Lhotse")), "Everest", category));
                questions.add(QuestionFactory.createQuestion("MCQ","How many continents?",
                        new ArrayList<>(Arrays.asList("5", "6", "7", "8")), "7", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Biggest ocean?",
                        new ArrayList<>(Arrays.asList("Atlantic", "Indian", "Arctic", "Pacific")), "Pacific", category));

                questions.add(QuestionFactory.createQuestion("MCQ","Inventor of telephone?",
                        new ArrayList<>(Arrays.asList("Edison", "Bell", "Tesla", "Einstein")), "Bell", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Color of emerald?",
                        new ArrayList<>(Arrays.asList("Red", "Blue", "Green", "Yellow")), "Green", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Smallest prime number?",
                        new ArrayList<>(Arrays.asList("0", "1", "2", "3")), "2", category));
                break;

            case "Science":
                questions.add(QuestionFactory.createQuestion("MCQ", "H2O is?",
                        Arrays.asList("Hydrogen", "Oxygen", "Water", "Helium"), "Water", category));
                questions.add(QuestionFactory.createQuestion("MCQ", "Speed of light?",
                        new ArrayList<>(Arrays.asList("300,000 km/s", "150,000 km/s", "500,000 km/s", "100,000 km/s")), "300,000 km/s", category));
                questions.add(QuestionFactory.createQuestion("MCQ", "Red planet?",
                        new ArrayList<>(Arrays.asList("Venus", "Mars", "Jupiter", "Saturn")), "Mars", category));
                questions.add(QuestionFactory.createQuestion("MCQ", "Largest organ in human body?",
                        new ArrayList<>(Arrays.asList("Heart", "Brain", "Skin", "Liver")), "Skin", category));
                questions.add(QuestionFactory.createQuestion("MCQ", "Photosynthesis makes?",
                        new ArrayList<>(Arrays.asList("Oxygen", "Carbon", "Nitrogen", "Hydrogen")), "Oxygen", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Atomic number of Hydrogen?",
                        new ArrayList<>(Arrays.asList("1", "2", "3", "4")), "1", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Sound fastest in?",
                        new ArrayList<>(Arrays.asList("Air", "Water", "Steel", "Vacuum")), "Steel", category));
                questions.add(QuestionFactory.createQuestion("MCQ", "Vitamin from sunlight?",
                        new ArrayList<>(Arrays.asList("A", "B", "C", "D")), "D", category));
                questions.add(QuestionFactory.createQuestion("MCQ", "DNA stands for?",
                        new ArrayList<>(Arrays.asList("Deoxyribonucleic Acid", "Data Nucleic Acid", "Double Nucleic Acid", "None")), "Deoxyribonucleic Acid", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Unit of force?",
                        new ArrayList<>(Arrays.asList("Joule", "Watt", "Newton", "Pascal")), "Newton", category));
                break;

            case "Sports":
                questions.add(QuestionFactory.createQuestion("MCQ","Players in football team?",
                        new ArrayList<>(Arrays.asList("9", "10", "11", "12")), "11", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Home run is used in?",
                        new ArrayList<>(Arrays.asList("Cricket", "Baseball", "Football", "Hockey")), "Baseball", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Olympics every how many years?",
                        new ArrayList<>(Arrays.asList("2", "3", "4", "5")), "4", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Shuttlecock used in?",
                        new ArrayList<>(Arrays.asList("Tennis", "Badminton", "Squash", "Golf")), "Badminton", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Zero score in tennis?",
                        new ArrayList<>(Arrays.asList("Nil", "Love", "Duck", "Zero")), "Love", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Michael Jordan played?",
                        new ArrayList<>(Arrays.asList("Football", "Basketball", "Baseball", "Tennis")), "Basketball", category));
                questions.add(QuestionFactory.createQuestion("MCQ","FIFA World Cup first won by?",
                        new ArrayList<>(Arrays.asList("Brazil", "Germany", "Uruguay", "Italy")), "Uruguay", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Cricket World Cup 2023 winner?",
                        new ArrayList<>(Arrays.asList("India", "England", "Australia", "New Zealand")), "Australia", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Wimbledon is for?",
                        new ArrayList<>(Arrays.asList("Football", "Cricket", "Tennis", "Golf")), "Tennis", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Fastest man on earth?",
                        new ArrayList<>(Arrays.asList("Bolt", "Gatlin", "Blake", "Powell")), "Bolt", category));
                break;

            case "Technology":
                questions.add(QuestionFactory.createQuestion("MCQ","Android developed by?",
                        new ArrayList<>(Arrays.asList("Apple", "Microsoft", "Google", "Amazon")), "Google", category));
                questions.add(QuestionFactory.createQuestion("MCQ","CPU stands for?",
                        new ArrayList<>(Arrays.asList("Central Processing Unit", "Computer Processing Unit", "Central Power Unit", "Core Processing Unit")), "Central Processing Unit", category));
                questions.add(QuestionFactory.createQuestion("MCQ","HTML is for?",
                        new ArrayList<>(Arrays.asList("Styling", "Structure", "Behavior", "Database")), "Structure", category));
                questions.add(QuestionFactory.createQuestion("MCQ","iPhone made by?",
                        new ArrayList<>(Arrays.asList("Samsung", "Apple", "Nokia", "OnePlus")), "Apple", category));
                questions.add(QuestionFactory.createQuestion("MCQ","RAM stands for?",
                        new ArrayList<>(Arrays.asList("Read Access Memory", "Random Access Memory", "Run Access Memory", "Real Access Memory")), "Random Access Memory", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Main language for Android now?",
                        new ArrayList<>(Arrays.asList("Java", "Kotlin", "Dart", "Swift")), "Kotlin", category));
                questions.add(QuestionFactory.createQuestion("MCQ","USB stands for?",
                        new ArrayList<>(Arrays.asList("Universal Serial Bus", "United Serial Bus", "Universal System Bus", "None")), "Universal Serial Bus", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Inventor of WWW?",
                        new ArrayList<>(Arrays.asList("Bill Gates", "Tim Berners-Lee", "Steve Jobs", "Elon Musk")), "Tim Berners-Lee", category));
                questions.add(QuestionFactory.createQuestion("MCQ","Bitcoin is a?",
                        new ArrayList<>(Arrays.asList("Stock", "Cryptocurrency", "Bond", "Fiat")), "Cryptocurrency", category));
                questions.add(QuestionFactory.createQuestion("MCQ","AI stands for?",
                        new ArrayList<>(Arrays.asList("Artificial Intelligence", "Automated Intelligence", "Advanced Internet", "None")), "Artificial Intelligence", category));

            default:
                questions.add(QuestionFactory.createQuestion("MCQ", "Sample?",
                        Arrays.asList("A", "B", "C", "D"), "A", category));
                break;
        }
    }

    public QuestionModel getCurrentQuestion() {
        questionStartTime = System.currentTimeMillis();
        return questions.get(currentQuestionIndex);
    }

    public boolean hasNext() {
        return currentQuestionIndex < questions.size() - 1;
    }

    public void moveToNext() {
        currentQuestionIndex++;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getCurrentIndex() {
        return currentQuestionIndex + 1;
    }

    public int getScore() {
        return score;
    }

    // -------------------- ANSWER HANDLING ---------------------

    public int submitAnswer(String userAnswer) {
        QuestionModel question = questions.get(currentQuestionIndex);
        if (question == null || userAnswer == null) return 0;

        // Normalize strings to avoid mismatch
        boolean isCorrect = question.getCorrectAnswer().trim().equalsIgnoreCase(userAnswer.trim());

        int points = isCorrect ? 1 : 0;  // 1 point for correct answer
        score += points;

        return points;
    }


    // ------------------ MEMENTO SUPPORT ------------------

    public GameStateMemento saveState() {
        return new GameStateMemento(currentQuestionIndex, score, questions);
    }

    public void restoreState(GameStateMemento memento) {
        if (memento == null) return;

        this.currentQuestionIndex = memento.getCurrentIndex();
        this.score = memento.getScore();
        this.questions = memento.getQuestions();

        notifyQuestionChanged(currentQuestionIndex + 1, questions.size());
    }

}
