package com.taotao.front.controller;

import com.taotao.front.bean.Cart;
import com.taotao.front.bean.Consignee;
import com.taotao.front.bean.Item;
import com.taotao.front.bean.Order;
import com.taotao.front.service.ItemService;
import com.taotao.front.service.OrderService;
import com.taotao.front.service.UserService;
import com.taotao.front.threadLocal.UserThreadLocal;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("order")
@Controller
public class OrderController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public ModelAndView toOrder(@PathVariable("itemId") Long itemId) {
        ModelAndView mv = new ModelAndView("order");
        // 设置模型数据
        Item item = this.itemService.queryItemById(itemId);
        mv.addObject("item", item);
        return mv;
    }

    /**
     * 基于购物车实现下单
     * 
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView toCartOrder() {
        ModelAndView mv = new ModelAndView("order-cart");
        List<Cart> carts = this.orderService.queryCartListByUserId(UserThreadLocal.get().getId());
        // 查询收货人列表
        List<Consignee> consigneeList = this.orderService.queryConsigneeListByUserId(UserThreadLocal.get()
                .getId());
        mv.addObject("consigneeList", consigneeList);
        mv.addObject("carts", carts);
        return mv;
    }
    
    @RequestMapping(value = "create/part", method = RequestMethod.GET,params={"itemIds"})
    public ModelAndView toCartOrder(@RequestParam("itemIds")List<Long> itemIds) {
        ModelAndView mv = new ModelAndView("order-cart");
        List<Cart> carts = this.orderService.queryCartListByUserId(UserThreadLocal.get().getId(),itemIds);
     // 查询收货人列表
        List<Consignee> consigneeList = this.orderService.queryConsigneeListByUserId(UserThreadLocal.get()
                .getId());
        mv.addObject("consigneeList", consigneeList);
        mv.addObject("carts", carts);
        return mv;
    }
    
    /**
     * 订单提交
     * 
     * @param order 提交的订单信息
     * @return
     */
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitOrder(Order order) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String orderId = this.orderService.submit(order);
            if (null == orderId) {
                result.put("status", 400);
            } else {
                result.put("status", 200);
                result.put("data", orderId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", 500);
        }
        return result;
    }

    /**
     * 成功页
     * 
     * @param orderId
     * @return
     */
    @RequestMapping(value = "success", method = RequestMethod.GET)
    public ModelAndView success(@RequestParam("id") String orderId) {
        ModelAndView mv = new ModelAndView("success");
        Order order = this.orderService.queryOrderById(orderId);
        mv.addObject("order", order);
        mv.addObject("date", new DateTime().plusDays(2).toString("MM月dd日"));
        return mv;
    }
    
    /**
     * 显示发票页面
     * 
     * @return 
     */
    @RequestMapping(value="invoice",method=RequestMethod.GET)
    public ModelAndView invoice(){
        ModelAndView mv = new ModelAndView("editInvoice");
        return mv;
    }
}
