package com.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class InitConfi implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    ConfigurableApplicationContext configurableApplicationContext;
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        this.configurableApplicationContext = configurableApplicationContext;

        System.out.println("程序初始化开始.............."+configurableApplicationContext);
    }

    public InitConfi() {
    }
}
