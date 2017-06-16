package com.zeus.manage.context;

import com.zeus.manage.bean.Admin;

/**
 * 验证用户是否登陆容器
 *
 * @Author Alex_Bao
 * @create 2017-06-15
 * create by IntelliJ IDEA
 */
public class UserLoginContext {
    
    private static final ThreadLocal<Admin> adminContext = new ThreadLocal<>();

    /**
     * 设置值
     *
     * @param admin
     */
    public void setAdmin(Admin admin){
        if(admin != null){
            adminContext.set(admin);
        }
    }

    /**
     * 获取值
     *
     * @return
     */
    public Admin getAdmin(){
        return adminContext.get();
    }

}
