package Jun.HelloSpring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 컴포넌트 스캔을 진행할 시작점 지정
        //basePackages = "Jun.HelloSpring.member",
        // 값으로 주어진 클래스의 패키지 위치를 기반으로 스캔을 진행한다
        //basePackageClasses = AutoAppConfig.class,

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //AppConfig를 제외하는 것이다.
        // 보통 설정 정보를 컴포넌트 스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택
        // @Component 어노테이션이 붙은 항목들을 자동으로 스프링빈에 등록해준다
        // @Autowired를 생성자에 붙여주면 자동 의존관계를 등록해준다
)

public class AutoAppConfig {

}
