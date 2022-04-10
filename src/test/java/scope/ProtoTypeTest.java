package scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class ProtoTypeTest {

    @Test
    void prototypeBeanfind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean2 = " + bean2);
//        assertThat(bean1).isNotEqualTo(bean2);
        assertThat(bean1).isNotSameAs(bean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            // prototype scope는 destroy를 진행하더라도 이 메소드가 실행될일이 없다
            // 스프링에서 더 이상 관리하는 컨테이너가 아니기 떄문
            System.out.println("PrototypeBean.destroy");
        }
    }
}
