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
    private int balance;


}
