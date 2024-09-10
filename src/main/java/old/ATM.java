package old;

import lombok.Data;

import java.util.*;

/**
 * Банкомат.
 * Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 * При выдаче купюры списываются с баланса банкомата.
 * Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 */

@Data
class ATM {
    // место для кода

    private static final List<Integer> BANKNOTE_LIST = List.of(50, 100, 1000, 5000);
    private TreeMap<Integer, Integer> atmBalance;

    private int balance;

    /*
     50 - 10 Шт
     100 - 2шт
     500 - 3 шт
     1000 - 1 шт
     5000 - 2 шт


     Выдать 5000 : 5к
     Выдать 6000 : 5000 + 1000
     1575 - ошибка ()
     100000 - ошибка
     */

    public void fillAtm(Map<Integer, Integer> banknotesMap) {
        this.balance = banknotesMap.entrySet().stream()
                .mapToInt(entry -> {
                    int key = entry.getKey();
                    int value = entry.getValue();
                    return key * value;
                }).sum();
        // 5000 1000 500 10 50
        TreeMap<Integer, Integer> atmBalance = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        atmBalance.putAll(banknotesMap);
        this.atmBalance = atmBalance;
    }

    public void getAtmResult(int banknoteRequest) {
        if (banknoteRequest > balance) {
            throw new RuntimeException("Больше баланса old.ATM");
        }
        for (Map.Entry<Integer, Integer> entry : atmBalance.entrySet()) {
            Integer coinCount = entry.getValue();
            Integer coin = entry.getKey();

            while (banknoteRequest >= coin && balance > 0) {
                int count = Math.min(banknoteRequest / coin, coinCount);
                banknoteRequest -= coin * count;
                balance -= count * coin;
                --coinCount;
            }
        }
        if (banknoteRequest != 0) {
            throw new RuntimeException(banknoteRequest + " не может быть выдан");
        }

        System.out.println("Деньги :" + banknoteRequest + " были успешно выведены");
    }
}
