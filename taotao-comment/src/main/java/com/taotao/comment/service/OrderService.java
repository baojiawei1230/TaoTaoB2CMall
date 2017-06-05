package com.taotao.comment.service;

import com.taotao.comment.bean.Order;
import com.taotao.comment.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    public Order queryOrderByOrderId(String orderId) {
        Order record = new Order();
        record.setOrderId(orderId);
        Order order = this.orderMapper.selectOne(record);
        return order;
    }
    
   

}
