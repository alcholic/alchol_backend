package com.hirim.sulgijang.common.config;

import com.hirim.sulgijang.common.AlcholInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AlcholInterceptor alcholInterceptor;

    public WebConfig(AlcholInterceptor alcholInterceptor) {
        this.alcholInterceptor = alcholInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(alcholInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**");
    }


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/");

    }
}
