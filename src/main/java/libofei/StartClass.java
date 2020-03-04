package libofei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan("libofei")只扫描特定的包

//@ComponentScan(basePackages = {"libofei.controller","libofei.service"})
//@EnableJpaRepositories("libofei.dao")
//@EnableAutoConfiguration
//@EntityScan("libofei.entity.*")
//@MapperScan(basePackages = {"libofei.user.dao","libofei.shop.dao"})
@SpringBootApplication //此注解=EnableAutoConfiguration+ComponentScan+Configuration 自动扫描此类下面的包
public class StartClass {

    public static void main(String[] args) {
        SpringApplication.run(StartClass.class,args);
    }
}
