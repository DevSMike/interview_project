package old.interview;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Item {

    private int id;
    private int price;
    private BigDecimal finalPrice;
}
