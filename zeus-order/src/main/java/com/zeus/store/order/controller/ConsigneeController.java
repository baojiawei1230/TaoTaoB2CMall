package com.zeus.store.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeus.store.order.pojo.Consignee;
import com.zeus.store.order.service.ConsigneeService;
import com.zeus.store.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("consignee")
public class ConsigneeController {
    @Autowired
    private ConsigneeService consigneeService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserService userService;

    // 编辑或者新增收货人,如果consigneeId是0则是新增收货人,如果不是0则是编辑收货人
    @RequestMapping(value = "addConsignee", method = RequestMethod.POST)
    public ResponseEntity<Void> addConsignee(@RequestBody Consignee consignee) {
        try {
            if (consignee.getId() == 0) {
                // 如果consigneeId是0则是新增收货人
                consignee.setId(null);
                this.consigneeService.addConsignee(consignee);
            } else {
                // 如果consigneeId不是0则是编辑收货人
                this.consigneeService.editConsignee(consignee);
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
