package cn.battlehawk233.barberplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
@MapperScan(basePackages = "cn.battlehawk233.barberplatform.dao")
public class BarberPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarberPlatformApplication.class, args);
    }

}
