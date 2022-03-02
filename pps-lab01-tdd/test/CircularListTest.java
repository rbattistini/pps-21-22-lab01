import lab01.tdd.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private final static int DUMMY_ELEMENT = 5;
    private CircularList circularList;

    @BeforeEach
    void beforeEach(){
        circularList = new CircularListImpl();
    }

    @Test
    void testAddElement() {
        circularList.add(DUMMY_ELEMENT);
        Optional<Integer> result = circularList.next();
        if(result.isPresent()) {
            assertEquals(result.get(), DUMMY_ELEMENT);
        } else {
            fail("No element has been added to the list");
        }
    }

    @Test
    void testListSize() {
        int actualSize = 1;
        circularList.add(DUMMY_ELEMENT);
        assertEquals(circularList.size(), actualSize);
    }

    @Test
    void testInitialEmptyList() {
        assertTrue(circularList.isEmpty());
    }

    @Test
    void testNextElementMissing() {
        assertFalse(circularList.next().isPresent());
    }

    @Test
    void testPreviousElementRetrieval() {
        circularList.add(DUMMY_ELEMENT);
        Optional<Integer> previousElement = circularList.previous();
        assertTrue(previousElement.isPresent());
        assertEquals(previousElement.get(), DUMMY_ELEMENT);
    }

    @Test
    void testPreviousElementMissing() {
        assertFalse(circularList.previous().isPresent());
    }

    @Test
    void testResetList() {
        for(int i : List.of(1,2,3)) {
            circularList.add(i);
        }
        Optional<Integer> tmpResult;

        tmpResult = circularList.next();
        assertTrue(tmpResult.isPresent());
        assertEquals(tmpResult.get(), 1);

        tmpResult = circularList.next();
        assertTrue(tmpResult.isPresent());
        assertEquals(tmpResult.get(), 2);

        circularList.reset();

        tmpResult = circularList.next();
        assertTrue(tmpResult.isPresent());
        assertEquals(tmpResult.get(), 1);
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
