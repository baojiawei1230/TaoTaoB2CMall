package com.zeus.user.controller;

import com.taotao.userinfo.bean.User;
import com.taotao.userinfo.pojo.UserInfo;
import com.taotao.userinfo.service.UserInfoService;
import com.taotao.userinfo.threadLocal.UserThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("userinfo")
public class UserInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService ;
    @RequestMapping(value = "image" , method = RequestMethod.GET)
    public ModelAndView image(){
        ModelAndView mv = new ModelAndView("my-info-img");
        return mv ;
    }
    
    @RequestMapping(value = "myinfo" , method = RequestMethod.GET)
    public ModelAndView myInfo(){
        ModelAndView mv = new ModelAndView("my-info");
        User user = UserThreadLocal.get();
        Long userId = user.getId();
        String email = user.getEmail();
        try {
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("根据userId查询相关资料! userId={}" , userId);
            }
            UserInfo userInfo = userInfoService.queryUserInfoByUserId(userId);
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("根据userId查询相关资料成功! userInfo={}" , userInfo);
            }
            mv.addObject("userinfo" ,userInfo);
            mv.addObject("email" ,email);
        } catch (Exception e) {
            LOGGER.error("根据userId查询相关资料失败! userId={}" , userId);
        }
        return mv ;
    }
    
    @RequestMapping(value = "myinfo" , method = RequestMethod.POST)
    public ModelAndView upDateUserInfo(UserInfo userInfo){
        ModelAndView mv = new ModelAndView("my-info");
        User user = UserThreadLocal.get();
        userInfo.setUserId(user.getId());
        try {
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("根据传递数据修改相关资料! userInfo={}" , userInfo);
            }
            userInfoService.upDateUserInfo(userInfo);
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("根据传递数据修改相关资料成功! userInfo={}" , userInfo);
            }
            mv.addObject("userinfo" , userInfo);
        } catch (Exception e) {
            LOGGER.error("根据传递数据修改相关资料失败! userInfo={}" , userInfo);
        }
        return mv ;
    }
    
}
