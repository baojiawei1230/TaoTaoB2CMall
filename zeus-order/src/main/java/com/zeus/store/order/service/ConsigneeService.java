package com.zeus.store.order.service;

import com.zeus.store.order.mapper.ConsigneeMapper;
import com.zeus.store.order.pojo.Consignee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsigneeService {
    @Autowired
    private ConsigneeMapper consigneeMapper;

    // 如果consigneeId是0则是新增收货人
    public void addConsignee(Consignee consignee) {
        this.consigneeMapper.addConsignee(consignee);
    }

    public List<Consignee> queryConsigneeList(Long userId) {
        List<Consignee> consigneeList = this.consigneeMapper.queryConsigneeList(userId);
        return consigneeList;
    }

    public Consignee queryConsigneeById(Long consigneeId) {
        Consignee consignee = this.consigneeMapper.queryConsigneeById(consigneeId);
        return consignee;
    }

    // 如果consigneeId不是0则是编辑收货人
    public void editConsignee(Consignee consignee) {
        this.consigneeMapper.editConsignee(consignee);
    }

    public void deleteConsignee(Long consigneeId) {
        this.consigneeMapper.deleteConsignee(consigneeId);
    }
}
