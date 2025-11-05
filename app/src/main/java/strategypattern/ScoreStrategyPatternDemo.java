package strategypattern;

public class ScoreStrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new SimpleScore());
        System.out.println("Simple Score: " + context.executeStrategy(8, 10));

        context = new Context(new PercentageScore());
        System.out.println("Percentage Score: " + context.executeStrategy(8, 10));
    }
}

class Context {
    private IScoreStrategy strategy;
    public Context(IScoreStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int correct, int total) {
        return strategy.calculateScore(correct, total);
    }
}

interface IScoreStrategy {
    int calculateScore(int correct, int total);
}

class SimpleScore implements IScoreStrategy {
    public int calculateScore(int correct, int total) {
        return correct * 10;
    }
}

class PercentageScore implements IScoreStrategy {
    public int calculateScore(int correct, int total) {
        return (int) ((correct / (double) total) * 100);
    }
}
