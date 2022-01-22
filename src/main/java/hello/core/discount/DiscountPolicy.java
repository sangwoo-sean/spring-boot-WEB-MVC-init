package hello.core.discount;

import hello.model.User;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int discount(User user, int price);
}
