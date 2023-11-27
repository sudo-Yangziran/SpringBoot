package com.youkill.composeown.config;

import com.youkill.composeown.dao.InitsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SpringMvcSupport implements WebMvcConfigurer {
    @Autowired
    InitsDAO initsDAO;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path=initsDAO.SelectSystemValue("SystemImg").getValue();
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + path + "/");
    }
}
