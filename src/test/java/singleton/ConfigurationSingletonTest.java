package singleton;

import Jun.HelloSpring.AppConfig;
import Jun.HelloSpring.member.MemberRepository;
import Jun.HelloSpring.member.MemberServiceImpl;
import Jun.HelloSpring.order.OrderService;
import Jun.HelloSpring.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl ms = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl os = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository m1 = ac.getBean("memberRepository", MemberRepository.class);
        MemberRepository m2 = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println("memberService -> memberRepository = " + ms.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + os.getMemberRepository());
        System.out.println("memberRepository = " + m1);

        Assertions.assertThat(ms.getMemberRepository()).isEqualTo(os.getMemberRepository());
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
    }
}
