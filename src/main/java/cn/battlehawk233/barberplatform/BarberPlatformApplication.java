package cn.battlehawk233.barberplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.battlehawk233.barberplatform.dao")
public class BarberPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarberPlatformApplication.class, args);
    }

}
