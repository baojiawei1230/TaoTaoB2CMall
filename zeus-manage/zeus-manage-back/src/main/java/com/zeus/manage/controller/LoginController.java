package com.zeus.manage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeus.manage.bean.Admin;
import com.zeus.manage.context.ResultMessage;
import com.zeus.manage.mapper.AdministratorMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 后台管理员登陆controller
 *
 * @Author Alex_Bao
 * @create 2017-06-15
 * create by IntelliJ IDEA
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private AdministratorMapper administratorMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 后台管理员登陆操作
     *
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model , @RequestParam("username")String username ,@RequestParam("password") String password ){
        //根据用户名和密码查询该管理员是否存在.
        ResultMessage message = new ResultMessage();
        if(!StringUtils.isNotBlank(username) || !StringUtils.isNotBlank(password)){
            message.setSuccess(false);
            message.setErroText("用户名或密码不能为空!");
        }
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        Admin queryAdmin = administratorMapper.queryAdminByUserNameAndPassword(admin);
        if(queryAdmin != null){
            //将对象放到缓存中.
            String jsonAdmin = null;
            try {
                jsonAdmin = objectMapper.writeValueAsString(admin);

            } catch (JsonProcessingException e) {
                LOGGER.info("序列化对象异常 !");
            }
            return jsonAdmin;
        }
        return "";
    }
}
