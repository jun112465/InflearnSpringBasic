package scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {


    @Test
    void filerScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfigTest.class);
        BeanA ba = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(ba).isNotNull();
        ac.getBean("beanB", BeanB.class);

    }

    @Configuration
    @ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes=MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type=FilterType.ANNOTATION, classes=MyExcludeComponent.class)

    )
    static class ComponentFilterAppConfig{
    }
}
