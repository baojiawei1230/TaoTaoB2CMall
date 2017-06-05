package com.taotao.ucenter.mapper;


import com.github.abel533.mapper.Mapper;
import com.taotao.ucenter.pojo.Order;
import com.taotao.ucenter.vo.OrderSelectVo;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {
    public List<Order> queryOrderByUserIdAndCreateDateAndStatus(OrderSelectVo vo);
}
