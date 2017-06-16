package com.zeus.ucenter.mapper;


import com.github.abel533.mapper.Mapper;
import com.zeus.ucenter.pojo.Order;
import com.zeus.ucenter.vo.OrderSelectVo;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {
    public List<Order> queryOrderByUserIdAndCreateDateAndStatus(OrderSelectVo vo);
}
