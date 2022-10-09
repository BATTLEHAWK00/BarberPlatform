package cn.battlehawk233.barbershop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocConfig {
    @Bean
    public OpenAPI enableOpenAPI() {
        return new OpenAPI().info(new Info().title("理发店管理系统")
                .description("理发店管理系统")
                .version("v0.0.1")
        );
    }
}
