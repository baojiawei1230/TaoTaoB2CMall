package com.taotao.front.controller;

import com.taotao.common.util.CookieUtils;
import com.taotao.front.bean.Consignee;
import com.taotao.front.bean.User;
import com.taotao.front.service.ConsigneeService;
import com.taotao.front.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("consignee")
public class ConsigneeController {
    @Autowired
    private ConsigneeService consigneeService;

    private String COOKIE_NAME = "TT_TOKEN";

    @Autowired
    private UserService userService;

    // 跳转到编辑或者新增收货人页面,如果consigneeId为0则是新增页面,否则是编辑页面
    @RequestMapping(value = "toConsignee", method = RequestMethod.GET)
    public ModelAndView toConsignee(@RequestParam("consigneeId") Long consigneeId) {
        ModelAndView mv = new ModelAndView("editConsignee");
        if (consigneeId != 0) {
            // 如果consigneeId不为0,则是编辑页面,调用service的方法去数据库查询数据进行回显
            Consignee consignee = this.consigneeService.queryConsigneeById(consigneeId);
            mv.addObject("consignee", consignee);
        }
        return mv;
    }

    // 编辑或者新增收货人,如果consigneeId是0则是新增收货人,如果不是0则是编辑收货人
    @RequestMapping(value = "addConsignee/{consigneeId}", method = RequestMethod.POST)
    public ResponseEntity<Void> addConsignee(Consignee consignee,
                                             @PathVariable("consigneeId") Long consigneeId, HttpServletRequest request) {
        String cookieData = CookieUtils.getCookieValue(request, COOKIE_NAME);
        User user = this.userService.queryUserByToken(cookieData);
        consignee.setUserId(user.getId());
        this.consigneeService.addConsignee(consignee);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 根据用户id查询所有的收货人
    @RequestMapping(value = "queryConsigneeList/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Consignee>> queryConsigneeList(@PathVariable("userId") Long userId) {
        try {
            List<Consignee> consigneeList = this.consigneeService.queryConsigneeList(userId);
            if (consigneeList == null || consigneeList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(consigneeList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    // 根据收货人id查询收货人
    @RequestMapping(value = "queryConsigneeById/{consigneeId}", method = RequestMethod.GET)
    public ResponseEntity<Consignee> queryConsigneeById(@PathVariable("consigneeId") Long consigneeId) {
        try {
            Consignee consignee = this.consigneeService.queryConsigneeById(consigneeId);
            if (consignee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(consignee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    // 删除收货人
    @RequestMapping(value = "deleteConsignee/{consigneeId}", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteConsignee(@PathVariable("consigneeId") Long consigneeId) {
        try {
            this.consigneeService.deleteConsignee(consigneeId);
            
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
