package commandpattern;

import java.util.ArrayList;
import java.util.List;


public class QuizCommandPatternDemo {
    public static void main(String[] args) {
        QuizSystem quizSystem = new QuizSystem();

        Command startQuiz = new StartQuizCommand(quizSystem);
        Command submitQuiz = new SubmitQuizCommand(quizSystem);

        User user = new User();
        user.takeCommand(startQuiz);
        user.takeCommand(submitQuiz);
        user.executeCommands();
    }
}


class QuizSystem {
    public void startQuiz() {
        System.out.println("Quiz started. Good luck!");
    }

    public void submitQuiz() {
        System.out.println("Quiz submitted. Well done!");
    }
}


interface Command {
    void execute();
}


class StartQuizCommand implements Command {
    private QuizSystem quizSystem;
    public StartQuizCommand(QuizSystem quizSystem) {
        this.quizSystem = quizSystem;
    }

    public void execute() {
        quizSystem.startQuiz();
    }
}

class SubmitQuizCommand implements Command {
    private QuizSystem quizSystem;
    public SubmitQuizCommand(QuizSystem quizSystem) {
        this.quizSystem = quizSystem;
    }

    public void execute() {
        quizSystem.submitQuiz();
    }
}


class User {
    private List<Command> commandList = new ArrayList<>();

    public void takeCommand(Command command) {
        commandList.add(command);
    }

    public void executeCommands() {
        for (Command c : commandList) {
            c.execute();
        }
        commandList.clear();
    }
}
