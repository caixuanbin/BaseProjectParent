package com.controller;

import com.bean.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@EnableConfigurationProperties(ConfigBean.class)
public class MyConfigController {
    @Autowired
    private ConfigBean configBean;
    @Value("${spring.config.host}")
    private String host;
    @Value("${spring.config.ip}")
    private String ip;
    @Value("${spring.config.password}")
    private String password;

    @RequestMapping("/keyValue")
    public String keyValue(){
        return "host:"+host+"|ip:"+ip+"|password:"+password;
    }
    @RequestMapping("/getBean")
    public ConfigBean getBean(){
        System.out.println(configBean.getHost()+"|"+configBean.getIp()+"|"+configBean.getPassword());
        ConfigBean b = new ConfigBean();
        b.setHost("167.999.444.000");
        b.setIp("134.555.666.777");
        b.setPassword("123456");
        return b;
    }
}
