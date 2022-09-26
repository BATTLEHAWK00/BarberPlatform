package cn.battlehawk233.barbershop;

import cn.battlehawk233.barbershop.config.NamingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class BarberShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(BarberShopApplication.class, args);
    }
}
