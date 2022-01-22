package hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {

    private Long userId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public int calculatePrice() { // 할인가 계산
        return itemPrice - discountPrice;
    }
}
