package scan;

import Jun.HelloSpring.member.MemberRepository;
import Jun.HelloSpring.member.MemberService;
import Jun.HelloSpring.member.MemberServiceImpl;
import Jun.HelloSpring.AutoAppConfig;
import Jun.HelloSpring.member.MemoryMemberRepository;
import Jun.HelloSpring.order.Order;
import Jun.HelloSpring.order.OrderService;
import Jun.HelloSpring.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {


    @Test
    @DisplayName("스캔된 모든 빈 출력")
    void printAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        String[] beans = ac.getBeanDefinitionNames();
        for(String bean : beans){
            System.out.println(ac.getBean(bean) + " : " +  bean);
        }
    }

    @Test
    @DisplayName("컴포넌트 스캔과 의존관계 자동주입이 정상적으로 됐느지 테스트")
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
//        MemoryMemberRepository mr = memberService.getMemberRepository();

        OrderService orderService = ac.getBean(OrderService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderServiceImpl.class);

        OrderServiceImpl os = ac.getBean(OrderServiceImpl.class);
        MemberServiceImpl ms  = ac.getBean(MemberServiceImpl.class);
        Assertions.assertThat(os.getMemberRepository()).isEqualTo(ms.getMemberRepository());

        System.out.println(ms );
    }

    @Test
    @DisplayName("이름으로 찾기")
    void nameScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        System.out.println(ac.getBean("memberServiceImpl"));
    }
}
