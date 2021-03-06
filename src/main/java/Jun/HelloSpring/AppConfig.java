package Jun.HelloSpring;

import Jun.HelloSpring.discount.DiscountPolicy;
import Jun.HelloSpring.discount.FixDiscountPolicy;
import Jun.HelloSpring.discount.RateDiscountPolicy;
import Jun.HelloSpring.member.MemberRepository;
import Jun.HelloSpring.member.MemberService;
import Jun.HelloSpring.member.MemberServiceImpl;
import Jun.HelloSpring.member.MemoryMemberRepository;
import Jun.HelloSpring.order.Order;
import Jun.HelloSpring.order.OrderService;
import Jun.HelloSpring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(
                memberRepository()
        );
    }
    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }

    //Bean이 붙은 것들은 자동으로 스프링 컨테이너에 등록된다.
}
