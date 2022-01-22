package hello.core.discount;

import hello.model.User;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountRate = 10;

    @Override
    public int discount(User user, int price) {
        return price * discountRate / 100;
    }
}
