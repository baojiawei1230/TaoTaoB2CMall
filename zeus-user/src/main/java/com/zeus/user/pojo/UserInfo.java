package com.zeus.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "tb_user_info")
public class UserInfo {
    @Id
    @Column(name="user_id")
    private Long userId ;
    
    @Column(name = "nick_name")
    private String nickName ; //昵称
    
    private String sex ; //性别
    private String birthday ; //生日
    @Column(name = "hobby_type")
    private String hobbyType ; //爱好
    @Column(name = "real_name")
    private String realName ; //真实名字
    private String address ;//地址
    @Column(name="province_city_county")
    private String provinceCityCounty ; //三级联动地址
    @Transient
    private String birthdayYear ;
    @Transient
    private String birthdayMonth ;
    @Transient
    private String birthdayDay ;
    @Transient
    private String province;
    @Transient
    private String city ;
    @Transient
    private String county ;
    
    
    
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public String getBirthdayYear() {
        return birthdayYear;
    }
    public void setBirthdayYear(String birthdayYear) {
        this.birthdayYear = birthdayYear;
    }
    public String getBirthdayMonth() {
        return birthdayMonth;
    }
    public void setBirthdayMonth(String birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }
    public String getBirthdayDay() {
        return birthdayDay;
    }
    public void setBirthdayDay(String birthdayDay) {
        this.birthdayDay = birthdayDay;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getHobbyType() {
        return hobbyType;
    }
    public void setHobbyType(String hobbyType) {
        this.hobbyType = hobbyType;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getProvinceCityCounty() {
        return provinceCityCounty;
    }
    public void setProvinceCityCounty(String provinceCityCounty) {
        this.provinceCityCounty = provinceCityCounty;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
}
