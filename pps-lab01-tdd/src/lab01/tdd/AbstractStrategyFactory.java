package lab01.tdd;

public interface AbstractStrategyFactory {
    SelectStrategy createStrategy(int givenNumber);
}
