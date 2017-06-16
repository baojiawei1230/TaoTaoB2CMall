package com.zeus.comment.service;

import com.zeus.comment.bean.OrderItem;
import com.zeus.comment.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;
    /**
     * 根据商品id去订单商品表中查询出商品的数据
     * @param itemId
     * @return
     */
    public OrderItem queryOrderItem(Long itemId, String orderId) {
        OrderItem record = new OrderItem();
        record.setItemId(itemId);
        record.setOrderId(orderId);
        OrderItem orderItem = this.orderItemMapper.selectOne(record );
        return orderItem;
    }

}
