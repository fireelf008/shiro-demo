package com.wsf.demo.config;

import inteceptor.JwtInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInteceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/getToken");
    }
}
