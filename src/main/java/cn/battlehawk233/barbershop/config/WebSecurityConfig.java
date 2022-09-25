package cn.battlehawk233.barbershop.config;

import cn.battlehawk233.barbershop.auth.AuthManager;
import cn.battlehawk233.barbershop.auth.provider.JWTAuthProvider;
import cn.battlehawk233.barbershop.auth.provider.LoginAuthProvider;
import cn.battlehawk233.barbershop.filter.JWTAuthFilter;
import cn.battlehawk233.barbershop.layer.dao.AdminRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            JWTAuthProvider jwtAuthProvider,
            LoginAuthProvider loginAuthProvider
    ) {
        var providers = List.of(
                jwtAuthProvider,
                loginAuthProvider
        );
        return new AuthManager(providers);
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            JWTAuthFilter jwtAuthFilter
    ) throws Exception {
        return http
                .authorizeRequests()
                // 放行登录接口
                .antMatchers("/auth/login", "/admin/register")
                .permitAll()
                // 除登录接口外，所有路径需要登录权限
                .antMatchers("/**")
                .authenticated()
                .and()
                // 添加JWT过滤器
                .addFilterBefore(jwtAuthFilter, BasicAuthenticationFilter.class)
                // 关闭登录页面
                .formLogin()
                .disable()
                // 关闭CSRF
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationManager(authenticationManager)
                .build();
    }
}
