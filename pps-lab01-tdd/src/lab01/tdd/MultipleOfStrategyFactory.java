package lab01.tdd;

public class MultipleOfStrategyFactory implements AbstractStrategyFactory{
    @Override
    public SelectStrategy createStrategy(int givenNumber) {
        return (x) -> x % givenNumber == 0;
    }
}
