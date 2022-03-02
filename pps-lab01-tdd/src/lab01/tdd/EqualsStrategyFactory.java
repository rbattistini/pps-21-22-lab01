package lab01.tdd;

public class EqualsStrategyFactory implements AbstractStrategyFactory {
    @Override
    public SelectStrategy createStrategy(int givenNumber) {
        return (x) -> x == givenNumber;
    }
}
