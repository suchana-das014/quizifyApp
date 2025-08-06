package com.example.quizio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionBank {

    public static List<QuestionModel> getAllQuestions() {
        List<QuestionModel> all = new ArrayList<>();
        all.addAll(getGeneralKnowledge());
        all.addAll(getScience());
        all.addAll(getSports());
        all.addAll(getTechnology());
        return all;
    }

    public static List<QuestionModel> getGeneralKnowledge() {
        return Arrays.asList(
                new QuestionModel("Capital of France?",
                        Arrays.asList("Paris", "London", "Rome", "Berlin"), "Paris", "General Knowledge"),
                new QuestionModel("Largest planet in Solar System?",
                        Arrays.asList("Mars", "Jupiter", "Saturn", "Venus"), "Jupiter", "General Knowledge"),
                new QuestionModel("Who wrote 'Hamlet'?",
                        Arrays.asList("Mark Twain", "William Shakespeare", "Charles Dickens", "Jane Austen"), "William Shakespeare", "General Knowledge"),
                new QuestionModel("The Great Wall is located in which country?",
                        Arrays.asList("India", "China", "Japan", "Korea"), "China", "General Knowledge"),
                new QuestionModel("Which continent is Egypt in?",
                        Arrays.asList("Africa", "Asia", "Europe", "Australia"), "Africa", "General Knowledge"),
                new QuestionModel("How many colors are in a rainbow?",
                        Arrays.asList("5", "6", "7", "8"), "7", "General Knowledge"),
                new QuestionModel("The currency of Japan is?",
                        Arrays.asList("Yuan", "Yen", "Won", "Dollar"), "Yen", "General Knowledge"),
                new QuestionModel("What is the tallest mountain in the world?",
                        Arrays.asList("K2", "Everest", "Kangchenjunga", "Lhotse"), "Everest", "General Knowledge"),
                new QuestionModel("Who painted the Mona Lisa?",
                        Arrays.asList("Vincent Van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"), "Leonardo da Vinci", "General Knowledge"),
                new QuestionModel("Which ocean is the largest?",
                        Arrays.asList("Atlantic", "Indian", "Arctic", "Pacific"), "Pacific", "General Knowledge")
        );
    }

    public static List<QuestionModel> getScience() {
        return Arrays.asList(
                new QuestionModel("H2O is chemical name for?",
                        Arrays.asList("Hydrogen", "Oxygen", "Water", "Helium"), "Water", "Science"),
                new QuestionModel("Speed of light is approximately?",
                        Arrays.asList("300,000 km/s", "150,000 km/s", "1,000 km/s", "10,000 km/s"), "300,000 km/s", "Science"),
                new QuestionModel("Which gas is most abundant in the Earth’s atmosphere?",
                        Arrays.asList("Oxygen", "Nitrogen", "Carbon dioxide", "Hydrogen"), "Nitrogen", "Science"),
                new QuestionModel("What planet is known as the Red Planet?",
                        Arrays.asList("Venus", "Mars", "Jupiter", "Saturn"), "Mars", "Science"),
                new QuestionModel("What is the human body's largest organ?",
                        Arrays.asList("Heart", "Liver", "Skin", "Kidney"), "Skin", "Science"),
                new QuestionModel("The process by which plants make food is called?",
                        Arrays.asList("Photosynthesis", "Respiration", "Transpiration", "Fermentation"), "Photosynthesis", "Science"),
                new QuestionModel("Atomic number of Hydrogen is?",
                        Arrays.asList("1", "2", "3", "4"), "1", "Science"),
                new QuestionModel("Sound travels fastest in?",
                        Arrays.asList("Air", "Water", "Steel", "Vacuum"), "Steel", "Science"),
                new QuestionModel("What vitamin is produced when skin is exposed to sunlight?",
                        Arrays.asList("Vitamin A", "Vitamin B", "Vitamin C", "Vitamin D"), "Vitamin D", "Science"),
                new QuestionModel("Which particle has a negative charge?",
                        Arrays.asList("Proton", "Neutron", "Electron", "Photon"), "Electron", "Science")
        );
    }

    public static List<QuestionModel> getSports() {
        return Arrays.asList(
                new QuestionModel("How many players in a football team?",
                        Arrays.asList("9", "10", "11", "12"), "11", "Sports"),
                new QuestionModel("In which sport is the term 'home run' used?",
                        Arrays.asList("Cricket", "Baseball", "Football", "Tennis"), "Baseball", "Sports"),
                new QuestionModel("Which country won the first FIFA World Cup?",
                        Arrays.asList("Brazil", "Germany", "Uruguay", "Italy"), "Uruguay", "Sports"),
                new QuestionModel("The Olympics are held every how many years?",
                        Arrays.asList("2", "3", "4", "5"), "4", "Sports"),
                new QuestionModel("Which sport uses a shuttlecock?",
                        Arrays.asList("Tennis", "Badminton", "Squash", "Table Tennis"), "Badminton", "Sports"),
                new QuestionModel("In tennis, what is a score of zero called?",
                        Arrays.asList("Love", "Zero", "Nil", "Duck"), "Love", "Sports"),
                new QuestionModel("Michael Jordan was famous in which sport?",
                        Arrays.asList("Football", "Basketball", "Baseball", "Golf"), "Basketball", "Sports"),
                new QuestionModel("How many points is a touchdown worth in American football?",
                        Arrays.asList("3", "6", "7", "1"), "6", "Sports"),
                new QuestionModel("Which sport is known as the 'king of sports'?",
                        Arrays.asList("Football", "Basketball", "Cricket", "Hockey"), "Football", "Sports"),
                new QuestionModel("What color card is shown for a foul in soccer?",
                        Arrays.asList("Red", "Blue", "Green", "Yellow"), "Red", "Sports")
        );
    }

    public static List<QuestionModel> getTechnology() {
        return Arrays.asList(
                new QuestionModel("Android is developed by?",
                        Arrays.asList("Apple", "Microsoft", "Google", "IBM"), "Google", "Technology"),
                new QuestionModel("What does CPU stand for?",
                        Arrays.asList("Central Process Unit", "Central Processing Unit", "Computer Personal Unit", "Central Processor Unit"), "Central Processing Unit", "Technology"),
                new QuestionModel("HTML stands for?",
                        Arrays.asList("HyperText Markup Language", "HyperText Makeup Language", "HyperText Mark Language", "HyperText Markdown Language"), "HyperText Markup Language", "Technology"),
                new QuestionModel("Which company created the iPhone?",
                        Arrays.asList("Apple", "Samsung", "Nokia", "Sony"), "Apple", "Technology"),
                new QuestionModel("RAM stands for?",
                        Arrays.asList("Random Access Memory", "Read Access Memory", "Run Access Memory", "Random Actual Memory"), "Random Access Memory", "Technology"),
                new QuestionModel("What is the main language used for Android development?",
                        Arrays.asList("Java", "Python", "Swift", "Kotlin"), "Kotlin", "Technology"),
                new QuestionModel("What does USB stand for?",
                        Arrays.asList("Universal Serial Bus", "Universal Service Bus", "Unified Serial Bus", "Universal System Bus"), "Universal Serial Bus", "Technology"),
                new QuestionModel("Which protocol is used to send emails?",
                        Arrays.asList("HTTP", "SMTP", "FTP", "POP3"), "SMTP", "Technology"),
                new QuestionModel("The first electronic computer was called?",
                        Arrays.asList("ENIAC", "UNIVAC", "EDSAC", "ABC"), "ENIAC", "Technology"),
                new QuestionModel("What is an IP address used for?",
                        Arrays.asList("Identifying a device on the internet", "Locating a file", "Programming a device", "Managing software"), "Identifying a device on the internet", "Technology")
        );
    }
}
