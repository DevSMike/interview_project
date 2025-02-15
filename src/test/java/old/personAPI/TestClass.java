package old.personAPI;

import interview.ATM;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {
    private final ATM testClass = new ATM();

    @Test
    public void test1() {
        int balance = 20000;
        Map<Integer, Integer> atm = new HashMap<>(Map.of(
                5000, 3,
                1000, 2,
                500, 4,
                100, 5,
                50, 10));
        testClass.setAtm(atm);
        testClass.setBalance(balance);

        var result = testClass.getSumFromATM(5500);
        System.out.println(result);
        assertEquals(2, result.size());

    }

    @Test
    public void test2() {
        int balance = 20000;
        Map<Integer, Integer> atm = new HashMap<>(Map.of(
                5000, 3,
                1000, 2,
                500, 4,
                100, 5,
                50, 10));

        testClass.setAtm(atm);
        testClass.setBalance(balance);

        var result = testClass.getSumFromATM(5575);
        System.out.println(result);
        assertEquals(testClass.getBalance(), balance);
        assertEquals(atm.get(5000), testClass.getAtm().get(5000));
    }

    @Test
    public void test3() {
        int balance = 20000;
        Map<Integer, Integer> atm = new HashMap<>(Map.of(
                5000, 3,
                1000, 2,
                500, 4,
                100, 5,
                50, 10));
        testClass.setAtm(atm);
        testClass.setBalance(balance);

        var result = testClass.getSumFromATM(100000);
        System.out.println(result);
        assertEquals(testClass.getBalance(), balance);
        assertEquals(testClass.getAtm().get(5000), atm.get(5000));
    }

    @Test
    public void test4() {
        int balance = 20000;
        Map<Integer, Integer> atm = new HashMap<>(Map.of(
                5000, 2,
                1000, 5,
                500, 8,
                100, 5,
                50, 10));
        testClass.setAtm(atm);
        testClass.setBalance(balance);

        var result = testClass.getSumFromATM(20000);
        System.out.println(result);
        assertEquals(5, result.size());
    }

}
