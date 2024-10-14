package yandex_coderun;

import java.util.ArrayList;
import java.util.List;

public class FindAllPrimesLinear {

    public static void main(String[] args) {

        // ищем все простые числа от 2 до n
        int n = 9;

        int[] lp = new int[n+1];
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (lp[i] == 0) {
                lp[i] = i;
                primes.add(i);
            }
            for (int p : primes) {
                int x = p * i;
                if (p > lp[i] || x > n) {
                    break;
                }
                lp[x] = p;
            }
        }
        System.out.println(primes);
    }
}
