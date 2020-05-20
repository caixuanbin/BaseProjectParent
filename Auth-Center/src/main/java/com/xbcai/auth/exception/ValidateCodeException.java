package com.xbcai.auth.exception;

import javax.naming.AuthenticationException;

/**
 * 自定义 验证码异常类
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
