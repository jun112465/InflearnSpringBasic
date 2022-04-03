package beanFind;

import Jun.HelloSpring.AppConfig;
import Jun.HelloSpring.member.Member;
import Jun.HelloSpring.member.MemberService;
import Jun.HelloSpring.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;


//스프링빈 조회
public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    static class MyClassTest{
        int a = 10;
    }

    @Test
    @DisplayName(".class가 무엇일까")
    void whatIsClass(){
        System.out.println(MyClassTest.class);
        System.out.println(AppConfig.class);
        System.out.println(System.class);
        System.out.println();
    }

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName  : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);
//            System.out.println("name : " + beanDefinitionName + " object : " + bean);
            System.out.println("bean = " + beanDefinitionName);
            System.out.println("object = " + bean);
            System.out.println();
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name : " + beanDefinitionName + " object : " + bean);
            }
        }
    }

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        HashMap<String, Integer > map = new HashMap<>();
//        map.put("test1", 1);
//        map.put("test1", 2);
//        System.out.println(map.get("test1"));
    }

    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
}
