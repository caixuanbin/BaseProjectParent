package com.xbcai.auth.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
//获取配置属性前缀
@ConfigurationProperties(prefix = "fantJ.security")
public class SecurityProperties {
    /**
     * 浏览器 属性类
     */
    private BrowserProperties browser = new BrowserProperties();
    /**
     * 验证码 属性类
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();


    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
