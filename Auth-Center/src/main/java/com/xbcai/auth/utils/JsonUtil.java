package com.xbcai.auth.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xbcai.entity.User;
import org.springframework.util.StringUtils;

/**
 * Created on 2018/1/17.
 *
 * @author zlf
 * @since 1.0
 */
public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

    public static void main(String[] args) {
        GsonBuilder gs = new GsonBuilder();
        gs.setPrettyPrinting();
        User user = new User();
        user.setCompany("赛程");
        user.setName("cxb");
        user.setSex("男");
        System.out.println(gs.create().toJson(user));

        System.out.println(StringUtils.tokenizeToStringArray("admin", ","));
    }
}
