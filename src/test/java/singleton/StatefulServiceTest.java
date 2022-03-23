package singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){

        //스프링컨테이너는 무상태로 코딩해야 한다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService s1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService s2 = ac.getBean("statefulService", StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        s1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        s2.order("userB", 20000);

        int price = s1.getPrice();
        System.out.println(price);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
