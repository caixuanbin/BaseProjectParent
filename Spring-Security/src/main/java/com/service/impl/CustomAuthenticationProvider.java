package com.service.impl;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * 自定义鉴权
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取验证的用户名及密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("用户名："+username+",密码："+password);
        //验证逻辑
        UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
        if(!StringUtils.isEmpty(userDetails)){
            String encodePassword = DigestUtils.md5DigestAsHex(password.getBytes());
            if(userDetails.getPassword().equals(encodePassword)){
                System.out.println("验证通过，密码一致："+encodePassword);
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                //设置设置角色和权限
                authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
                authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
                // 生成令牌 这里令牌里面存入了:name,password,authorities, 当然你也可以放其他内容
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                        userDetails.getPassword(),authorities);
                //SecurityContextHolder.getContext().setAuthentication(auth);
                return auth;
            }else{
                throw new BadCredentialsException("密码口令错误");
            }
        }else{
            throw new UsernameNotFoundException("该用户不存在");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    public static void main(String[] args) {
       System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
}
