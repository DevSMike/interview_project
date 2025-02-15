package interview;

import leetcode.Item;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/*
Интернет магазин хочет повысить лояльность покупателей. Для этого начнет давать им персональные скидки.

Нужно сделать простую систему лояльности, которая дает % скидку на корзину.
Процент скидки зависит от пользователя.

Написать класс, который:
- на вход получает id пользователя и корзину
- вычисляет и применяет скидки
- возвращает корзину, в которой учтены скидки
- скидка учитывается в стоимости покупки

Корзина - список покупок пользователя.

Покупка:
- id товара
- цена
- итоговая стоимость c учетом скидки

Скидка. Для пользователя может быть задан % скидки (целое число).
*/
@Data
public class InterviewClass {

    private final Map<Integer, Integer> loyaltyUsers;
    private List<Item> bucket;

    public InterviewClass(Map<Integer, Integer> users) {
        this.loyaltyUsers = users;
    }

    public String method() {
        return "Test";
    }

    public List<Item> getBucket(int userId, List<Item> bucket) {
        int discount = loyaltyUsers.getOrDefault(userId, -1);
        if (discount == -1) {
            return bucket;
        }

        for (Item item : bucket) {
            BigDecimal percent = BigDecimal.valueOf(discount / 100.0);   // 0.2
            BigDecimal price = BigDecimal.valueOf(item.getPrice());
            BigDecimal disc = price.multiply(percent);
            item.setFinalPrice(price.subtract(disc));  // 100 - 100 * 0.2 => 100 - 20 = 80
        }

        return bucket;
    }
}
