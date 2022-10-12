package cn.battlehawk233.barbershop.config;

import cn.battlehawk233.barbershop.auth.AuthManager;
import cn.battlehawk233.barbershop.auth.provider.JWTAuthProvider;
import cn.battlehawk233.barbershop.auth.provider.LoginAuthProvider;
import cn.battlehawk233.barbershop.filter.JWTAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
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
    public AuthenticationManager authenticationManager(JWTAuthProvider jwtProvider, LoginAuthProvider loginProvider) {
        return new AuthManager(List.of(
                jwtProvider,
                loginProvider
        ));
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            JWTAuthFilter jwtAuthFilter
    ) throws Exception {
        return http.authorizeRequests()
                // 放行登录接口
                .antMatchers(
                        "/auth/login",
                        "/admin/register",
                        "/ws",
                        "/api-docs",
                        "/api-docs.html",
                        "/api-docs/*",
                        "/swagger-ui/*"
                )
                .permitAll()
                // 除登录接口外，所有路径需要登录权限
                .antMatchers("/**")
                .permitAll()
                .and()
                // 添加JWT过滤器
                .addFilterBefore(jwtAuthFilter, BasicAuthenticationFilter.class)
                // 关闭登录页面
                .formLogin()
                .disable()
                // 关闭CSRF
                .csrf()
                .disable()
                // 使用无状态Session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 设置Auth Manager
                .authenticationManager(authenticationManager)
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .build();
    }

    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("http://localhost:[*]"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
