package Jun.HelloSpring.discount;

import Jun.HelloSpring.member.Grade;
import Jun.HelloSpring.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }
}
