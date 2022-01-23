package hello.core.discount;

import hello.core.model.User;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(User user, int price) {
        return discountFixAmount;
    }
}
