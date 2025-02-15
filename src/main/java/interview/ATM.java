package interview;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Банкомат.
 * Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 * При выдаче купюры списываются с баланса банкомата.
 * Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 */
 @Setter
@Getter
public class ATM {
    // место для кода
    private int balance;

    private static final int[] NOMINALS = {5000, 1000, 500, 100, 50};
    private Map<Integer, Integer> atm; // 5000 -> 10; 1000 -> 3

    public Map<Integer, Integer> getSumFromATM(int target) {
        Map<Integer, Integer> result = new HashMap<>();

        for (int num : NOMINALS) {
            if (!atm.containsKey(num)) {
                continue;
            }
            int count = Math.min(target / num, atm.get(num));

            if (count != 0) {
                result.put(num, count);
                atm.put(num, atm.get(num) - count);
                if (atm.get(num) == 0) {
                    atm.remove(num);
                }
                balance -= num * count;
                target -= num * count;
            }

        }

        if (target != 0) {
            for (Integer key : result.keySet()) {
                atm.put(key, atm.getOrDefault(key, 0) + result.get(key));
                balance += result.get(key) * key;
            }
            return null;
        }
        return result;
    }

}
