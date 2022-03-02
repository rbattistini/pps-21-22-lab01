import lab01.tdd.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CircularListSelectStrategyTest {

    private CircularList circularList;

    @BeforeEach
    void beforeEach(){
        circularList = new CircularListImpl();
    }

    @Test
    void testNextWithEvenStrategy() {
        for(int i : List.of(1,2,3)) {
            circularList.add(i);
        }

        SelectStrategy evenStrategy = (x) -> x % 2 == 0;

        Optional<Integer> result = circularList.next(evenStrategy);
        assertTrue(result.isPresent());
        assertEquals(result.get(), 2);
    }

    @Test
    void testNextWithMultipleOfStrategy() {
        for(int i : List.of(1,3,4)) {
            circularList.add(i);
        }

        int givenNumber = 2;
        AbstractStrategyFactory strategyFactory = new MultipleOfStrategyFactory();
        SelectStrategy multipleOfStrategy = strategyFactory.createStrategy(givenNumber);

        Optional<Integer> result = circularList.next(multipleOfStrategy);
        assertTrue(result.isPresent());
        assertEquals(result.get(), 4);
    }

    @Test
    void testNextWithEqualsStrategy() {
        for(int i : List.of(1,1,0)) {
            circularList.add(i);
        }

        int givenNumber = 0;
        AbstractStrategyFactory strategyFactory = new EqualsStrategyFactory();
        SelectStrategy equalsStrategy = strategyFactory.createStrategy(givenNumber);

        Optional<Integer> result = circularList.next(equalsStrategy);
        assertTrue(result.isPresent());
        assertEquals(result.get(), 0);
    }
}
