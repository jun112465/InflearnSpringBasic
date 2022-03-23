package singleton;

import Jun.HelloSpring.AppConfig;
import Jun.HelloSpring.member.Member;
import Jun.HelloSpring.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        MemberService m1 = appConfig.memberService();
        MemberService m2 = appConfig.memberService();

        Assertions.assertThat(m1).isNotSameAs(m2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService s1 = SingletonService.getInstance();
        SingletonService s2 = SingletonService.getInstance();

        Assertions.assertThat(s1).isSameAs(s2);
        //isSameAs : 참조값을 비교
        //isEqualTo : 내장값을 비
    }

    @Test
    @DisplayName("r")
    void springContainer(){
        AppConfig appConfig = new AppConfig();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService m1 = ac.getBean("memberService", MemberService.class);
        MemberService m2 = ac.getBean("memberService", MemberService.class);

        System.out.println("m1 : " + m1);
        System.out.println("m2 : " + m2);
        Assertions.assertThat(m1).isSameAs(m2);
    }
}
