package com.taotao.user.mapper;

import com.github.abel533.mapper.Mapper;
import com.taotao.userinfo.pojo.UserInfoMore;

public interface UserInfoMoreMapper extends Mapper<UserInfoMore>{
    /**根据UserId查询更多资料*/
    UserInfoMore queryUserInfoMoreByUserId(Long userId);

}
