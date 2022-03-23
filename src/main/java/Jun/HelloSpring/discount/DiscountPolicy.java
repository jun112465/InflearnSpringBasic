package Jun.HelloSpring.discount;

import Jun.HelloSpring.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
