package com.zeus.manage.bean;

/**
 * @Author Alex_Bao
 * @create 2017-06-15
 * create by IntelliJ IDEA
 */
public class Admin implements java.io.Serializable {

    /**
     * 用户名
     */
    private String username ;
    /**
     * 密码
     */
    private String password;

    public void setUsername(String uesrName){
        this.username = username;
    }

    public void setPassword(String password){
        this.password =password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

}
