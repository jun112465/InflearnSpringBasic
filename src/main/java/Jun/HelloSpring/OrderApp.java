package Jun.HelloSpring;

import Jun.HelloSpring.member.Grade;
import Jun.HelloSpring.member.Member;
import Jun.HelloSpring.member.MemberService;
import Jun.HelloSpring.member.MemberServiceImpl;
import Jun.HelloSpring.order.Order;
import Jun.HelloSpring.order.OrderService;
import Jun.HelloSpring.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);


        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order "+ order);
    }
}
