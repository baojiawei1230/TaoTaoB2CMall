package com.zeus.user.service;

import com.taotao.userinfo.mapper.UserInfoMoreMapper;
import com.taotao.userinfo.pojo.UserInfoMore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoMoreService {

    @Autowired
    private UserInfoMoreMapper userInfoMoreMapper ;
    /**根据UserId查询更多资料*/
    public UserInfoMore queryUserInfoMoreByUserId(Long userId) {
      return  userInfoMoreMapper.queryUserInfoMoreByUserId(userId);
    }
    
    public void upDateUserInfo(UserInfoMore userInfoMore) {
    	Long userId = userInfoMore.getUserId();
    	UserInfoMore user = this.userInfoMoreMapper.selectByPrimaryKey(userId);
    	if(user == null){
    		this.userInfoMoreMapper.insert(userInfoMore);
    	}else{
    		this.userInfoMoreMapper.updateByPrimaryKey(userInfoMore);
    	}
        
    }
    
    
}
