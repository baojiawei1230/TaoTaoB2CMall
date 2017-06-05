package com.taotao.ucenter.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.service.ApiService;
import com.taotao.ucenter.pojo.Order;
import com.taotao.ucenter.pojo.PageResult;
import com.taotao.ucenter.service.MyOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("order")
public class MyOrderController {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(MyOrderController.class);
    
    @Autowired
    private MyOrderService orderService;
    @Autowired
    private ApiService apiService;

    @RequestMapping(value="orderList")
    public ModelAndView getOrderList(@RequestParam(value="submitDate",defaultValue="1")String _byDate,
            @RequestParam(value="orderState",defaultValue="4096")String _byStatus){
      //TODO 先使用传统方法判断是否为数字  后期使用拦截器
        if(!isNumeric(_byDate) && !isNumeric(_byStatus))return null;
        
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("查询用户订单 时间条件{}/订单状态条件{}",_byDate,_byStatus);
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("my-order-item");
        
        try {
            List<Order> orderList = orderService.queryOrderByUserIdAndCreateDateAndStatus(0,0,_byDate,_byStatus);
            PageResult<Order> pageResult = new PageResult<Order>(orderList.size(),orderList);
            if(null != orderList){
                if(LOGGER.isDebugEnabled()){
                    LOGGER.debug("查询用户订单成功 数据为：{}",pageResult.toString());
                }
//                mv.addObject("status",200);
                mv.addObject("orderResult", pageResult);
                return mv;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询用户订单异常 查询条件时间条件{}/订单状态条件{}" + e.getMessage(),_byDate,_byStatus);
        }
//        mv.addObject("status",500);
        return mv;
    }
    
    @RequestMapping("orderDetails")
    public ModelAndView showOrderDetails(@RequestParam("orderid")String _orderId){
        String orderStr = "";
        try {
            orderStr = apiService.doGet("http://order.taotao.com/order/query/" + _orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Order order = null;
        try {
            order = MAPPER.readValue(orderStr, Order.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("my-order-details");
        mv.addObject("order", order);
        return mv;
    }
    
    //TODO 先使用传统方法判断是否为数字  后期使用拦截器
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false; 
        } 
        return true; 
     }
}
