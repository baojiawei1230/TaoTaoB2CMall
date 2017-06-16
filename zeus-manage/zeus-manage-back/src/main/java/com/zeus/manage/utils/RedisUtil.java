package com.zeus.manage.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

/**
 * redis工具类
 *
 * @Author Alex_Bao
 * @create 2017-06-15
 * create by IntelliJ IDEA
 */
public class RedisUtil {

    private static Properties properties ;
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);
    private static boolean enable = false;

    private void init(){
        properties = new Properties();
        ClassPathResource classPathResource = new ClassPathResource("redis.properties");
        boolean enable = "true".equals(properties.getProperty("redis.enable"));

    }


}
