package com.msu.elibrary.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author VPatel
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private Environment environment;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadsLocation = environment.getProperty("com.msu.elibrary.imagePath");
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:" + uploadsLocation);
    }
}
