package com.taotao.user.mapper;

import com.github.abel533.mapper.Mapper;
import com.taotao.userinfo.pojo.UserInfo;

public interface UserInfoMapper extends Mapper<UserInfo>{
    /**根据UserId查询相关资料*/
    UserInfo queryUserInfoByUserId(Long userId);

}
