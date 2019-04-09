package com.hirim.sulgijang.common.config;

import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiPartConfig extends MultipartAutoConfiguration {
    public MultiPartConfig(MultipartProperties multipartProperties) {
        super(multipartProperties);
    }

}
