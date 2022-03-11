package lab01.tdd;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {

    private final List<Integer> list;
    private int currentIdx;

    public CircularListImpl() {
        list = new LinkedList<>();
        currentIdx = 0;
    }

    @Override
    public void add(int element) {
        list.add(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        if(list.isEmpty()) {
            return Optional.empty();
        }
        
        int result = list.get(currentIdx);
        currentIdx = nextIndex(currentIdx);
        return Optional.of(result);
    }

    @Override
    public Optional<Integer> previous() {
        if(list.isEmpty()) {
            return Optional.empty();
        }

        currentIdx = previousIndex(currentIdx);
        return Optional.of(list.get(currentIdx));
    }

    @Override
    public void reset() {
        currentIdx = 0;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        if(list.isEmpty()) {
            return Optional.empty();
        }

        Optional<Integer> result = list.stream().filter(strategy::apply).findFirst();
        result.ifPresent(integer -> currentIdx = list.indexOf(integer));
        return result;
    }

    private int nextIndex(int idx) {
        return ((idx + 1) % size());
    }

    private int previousIndex(int idx) {
        return (idx - 1 + size()) % size();
    }
}
