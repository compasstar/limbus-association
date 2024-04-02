package com.limbus;

import com.limbus.api.BeforeTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {

    @Bean
    public BeforeTest beforeTest() {
        return new BeforeTest();
    }

}
