package com.taotao.sso.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.service.RedisService;
import com.taotao.sso.mapper.UserMapper;
import com.taotao.sso.pojo.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 检测数据是否可用
     * 
     * @param param
     * @param type
     * @return null：参数不合法，true：数据可用，false：数据不可用
     */
    public Boolean check(String param, Integer type) {
        User record = new User();
        switch (type) {
        case 1:
            record.setUsername(param);
            break;
        case 2:
            record.setPhone(param);
            break;
        case 3:
            record.setEmail(param);
            break;
        default:
            // 参数不合法
            return null;
        }
        User user = this.userMapper.selectOne(record);
        return user == null;
    }

    public Boolean doRegister(User user) {
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        user.setId(null);

        // 密码进行MD5加密处理
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));

        return this.userMapper.insert(user) == 1;
    }

    public String doLogin(String username, String password) throws Exception {
        User record = new User();
        record.setUsername(username);
        User user = this.userMapper.selectOne(record);
        if (null == user) {
            return null;
        }
        // 对比密码是否相同
        if (!StringUtils.equals(user.getPassword(), DigestUtils.md5Hex(password))) {
            return null;
        }

        // 用户名和密码都正确，登录成功
        // 生成token
        String token = DigestUtils.md5Hex(user.getUsername() + "_" + System.currentTimeMillis());
        // 将用户信息保存到redis中
        this.redisService.set("TOKEN_" + token, MAPPER.writeValueAsString(user), 60 * 30);

        return token;
    }

    public User queryUserByToken(String token) throws Exception {
        String key = "TOKEN_" + token;
        String jsonData = this.redisService.get(key);
        if (null == jsonData) {
            return null;
        }
        //重新设置redis中的数据的生存时间
        //非常重要，别忘记
        this.redisService.expire(key, 60 * 30);
        
        return MAPPER.readValue(jsonData, User.class);
    }

}
