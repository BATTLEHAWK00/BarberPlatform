package cn.battlehawk233.barberplatform;

import cn.battlehawk233.barberplatform.controller.filters.TokenInterceptor;
import cn.battlehawk233.barberplatform.dao.TokenMapper;
import cn.battlehawk233.barberplatform.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@AllArgsConstructor
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private TokenService tokenService;
    private TokenMapper tokenMapper;
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/system/login",
                        "/system/firstTime",
                        "/system/userInfo",
                        "/system/setLoginToken"
                );
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }
}
