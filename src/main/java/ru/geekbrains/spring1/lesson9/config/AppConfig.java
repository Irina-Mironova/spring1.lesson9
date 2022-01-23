package ru.geekbrains.spring1.lesson9.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("ru.geekbrains.spring1.lesson9")


public class AppConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/static/images/**")) {
            registry.addResourceHandler("/static/images/**").addResourceLocations("file:images/");
        }
    }
}