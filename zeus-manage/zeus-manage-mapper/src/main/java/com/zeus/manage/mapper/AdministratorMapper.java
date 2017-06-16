package com.zeus.manage.mapper;

import com.zeus.manage.bean.Admin;

/**
 * 管理员查询mapper
 *
 * @Author Alex_Bao
 * @create 2017-06-15
 * create by IntelliJ IDEA
 */
public interface AdministratorMapper {


    public Admin queryAdminByUserNameAndPassword(Admin admin);

}
