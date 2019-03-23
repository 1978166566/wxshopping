package com.cssl.controller;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class PathConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:///C:\\Users\\许邦柱\\Documents\\Tencent Files\\907236676\\FileRecv\\image\\");
        /*registry.addResourceHandler("/img*//*").addResourceLocations("file:///opt/MyAdmin/static/img/");
        super.addResourceHandlers(registry);*/
    }
}
