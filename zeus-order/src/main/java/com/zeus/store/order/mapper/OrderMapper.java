package com.zeus.store.order.mapper;

import com.zeus.store.order.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface OrderMapper extends IMapper<Order> {
	
	public void paymentOrderScan(@Param("date") Date date);

}
