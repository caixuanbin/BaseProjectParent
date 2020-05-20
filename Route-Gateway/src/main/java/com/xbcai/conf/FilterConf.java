package com.xbcai.conf;

import com.xbcai.filter.RequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConf {
    @Bean
    public RequestFilter filter(){
        System.out.println("实例化过滤器实例------------------------------");
        return  new RequestFilter() ;
    }
}
