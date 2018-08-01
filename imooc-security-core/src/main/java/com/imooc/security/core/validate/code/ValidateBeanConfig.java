package com.imooc.security.core.validate.code;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.validate.code.generator.ImageCodeGenerator;

@Configuration
public class ValidateBeanConfig {

    @Bean
    @ConditionalOnMissingBean(ImageCodeGenerator.class)
    public ImageCodeGenerator imageCodeGenerator() {
        return new ImageCodeGenerator();
    }
}
