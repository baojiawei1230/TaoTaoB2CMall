package com.taotao.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_user_info_more")
public class UserInfoMore {
    @Id
    @Column(name="user_id")
    private Long userId ;
    @Column(name="marital_status")
    private String maritalStatus ; //婚姻
    @Column(name="monthly_income")
    private String monthlyIncome ; //月收入
    @Column(name="identification_card")
    private String identificationCard ; //身份证
    private String education ; //教育程度
    @Column(name="industry_info")
    private String industryInfo ; //所在行业
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getMonthlyIncome() {
        return monthlyIncome;
    }
    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
   
    public String getIdentificationCard() {
        return identificationCard;
    }
    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
    }
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public String getIndustryInfo() {
        return industryInfo;
    }
    public void setIndustryInfo(String industryInfo) {
        this.industryInfo = industryInfo;
    }
    
}
