package com.taotao.user.service;

import com.taotao.userinfo.mapper.UserInfoMapper;
import com.taotao.userinfo.pojo.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /** 根据UserId查询相关资料 */
    public UserInfo queryUserInfoByUserId(Long userId) {
        UserInfo userinfo = userInfoMapper.selectByPrimaryKey(userId);
        
        if(StringUtils.isNotEmpty(userinfo.getBirthday())){
            String[] temp = StringUtils.split(userinfo.getBirthday(), ",");
            if (StringUtils.isNotEmpty(temp[0])) {

                userinfo.setBirthdayYear(temp[0]);
            }
            if (StringUtils.isNotEmpty(temp[1])) {

                userinfo.setBirthdayMonth(temp[1]);
            }
            if (StringUtils.isNotEmpty(temp[2])) {

                userinfo.setBirthdayDay(temp[2]);
            }
        }
        
        if(StringUtils.isNotEmpty(userinfo.getProvinceCityCounty())){
            String[] temp1 = StringUtils.split(userinfo.getProvinceCityCounty(), ",");
            if (StringUtils.isNotEmpty(temp1[0])) {

                userinfo.setProvince(temp1[0]);
            }
            if (StringUtils.isNotEmpty(temp1[1])) {

                userinfo.setCity(temp1[1]);
            }
            if (StringUtils.isNotEmpty(temp1[2])) {

                userinfo.setCounty(temp1[2]);
            }
        }
        
        return userinfo;
    }

    /** 根据传递数据修改相关资料 */
    public void upDateUserInfo(UserInfo userInfo) {
    	Long userId = userInfo.getUserId();
    	UserInfo user= this.userInfoMapper.selectByPrimaryKey(userId);
    	if(user == null){
    		this.userInfoMapper.insert(userInfo);
    	}else{
    		userInfo.setBirthday(userInfo.getBirthdayYear()+"," + userInfo.getBirthdayMonth() + "," +userInfo.getBirthdayDay());
            userInfo.setProvinceCityCounty(userInfo.getProvince()+ "," +userInfo.getCity() + "," +userInfo.getCounty());
            this.userInfoMapper.updateByPrimaryKey(userInfo);
    	}
        
    }

}
