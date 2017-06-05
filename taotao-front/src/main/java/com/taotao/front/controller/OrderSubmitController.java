package com.taotao.front.controller;

import com.taotao.front.bean.Consignee;
import com.taotao.front.service.OrderSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @RequestMapping("consignee")
public class OrderSubmitController {
    @Autowired
    private OrderSubmitService orderSubmitService;

    // 跳转到编辑或者新增收货人页面,如果consigneeId为0则是新增页面,否则是编辑页面
    @RequestMapping(value = "editConsignee", method = RequestMethod.GET)
    public ModelAndView addConsignee(@RequestParam("consigneeId") Long consigneeId) {
        ModelAndView mv = new ModelAndView("editConsignee");
        if (consigneeId != 0) {
            // 如果consigneeId不为0,则是编辑页面,调用service的方法去数据库查询数据进行回显
            Consignee consignee = this.orderSubmitService.queryConsigneeById(consigneeId);
            mv.addObject("consignee", consignee);
        }
        return mv;
    }
}
