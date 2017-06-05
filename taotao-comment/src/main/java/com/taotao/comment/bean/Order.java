package com.taotao.comment.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_order")
public class Order {
    private String orderId;// id,rowKye:id+时间戳

    @JsonDeserialize(using = DateDeserializer.class)
    private Date createTime;// 创建时间

    @JsonDeserialize(using = DateDeserializer.class)
    private Date updateTime;// 更新时间

    private Long userId;// 用户id

    private String buyerNick;// 买家昵称

    private Integer buyerRate;// 买家是否已经评价

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public Integer getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(Integer buyerRate) {
        this.buyerRate = buyerRate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
