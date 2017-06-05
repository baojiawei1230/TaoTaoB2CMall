package com.taotao.ucenter.vo;

import java.util.Date;

public class OrderSelectVo {
    //开始时间条件
    private Date startDate = null;
    //结束时间
    private Date endDate = null;
    //订单状态
    private Integer orderStatus = null;
    //用户id
    private Long userId = null;
    
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Integer getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
