package com.zeus.user.controller;

import com.taotao.userinfo.bean.User;
import com.taotao.userinfo.pojo.UserInfoMore;
import com.taotao.userinfo.service.UserInfoMoreService;
import com.taotao.userinfo.threadLocal.UserThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("userinfomore")
public class UserInfoMoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoMoreController.class);
    @Autowired
    private UserInfoMoreService userInfoMoreService ;
    
    @RequestMapping(value = "myinfomore" , method = RequestMethod.GET)
    public ModelAndView myInfo(){
        User user = UserThreadLocal.get();
        Long userId = user.getId();
        ModelAndView mv = new ModelAndView("my-info-more");
        try {
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("根据userId查询更多资料! userId={}" , userId);
            }
            UserInfoMore userInfoMore = userInfoMoreService.queryUserInfoMoreByUserId(userId);
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("根据userId查询更多资料成功! userInfo={}" , userInfoMore);
            }
            mv.addObject("userInfoMore" , userInfoMore);
        } catch (Exception e) {
            LOGGER.error("根据userId查询更多资料失败! userId={}" , userId);
        }
        return mv ;
    }
    
    @RequestMapping(value = "myinfomore" , method = RequestMethod.POST)
    public ModelAndView upDateUserInfo(UserInfoMore userInfoMore){
        User user = UserThreadLocal.get();
        userInfoMore.setUserId(user.getId());
        ModelAndView mv = new ModelAndView("my-info-more");
        try {
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("根据传递数据修改相关资料! userInfoMore={}" , userInfoMore);
            }
            userInfoMoreService.upDateUserInfo(userInfoMore);
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("根据传递数据修改相关资料成功! userInfoMore={}" , userInfoMore);
            }
            mv.addObject("userInfoMore" , userInfoMore);
        } catch (Exception e) {
            LOGGER.error("根据传递数据修改相关资料失败! userInfoMore={}" , userInfoMore);
        }
        return mv ;
    }
}
