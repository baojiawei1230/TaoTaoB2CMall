package com.taotao.store.order.mapper;

import com.taotao.store.order.pojo.Consignee;

import java.util.List;

public interface ConsigneeMapper {
    // 如果consigneeId是0则是新增收货人
    public void addConsignee(Consignee consignee);

    // 根据用户id查询所有收货人
    public List<Consignee> queryConsigneeList(Long userId);

    // 根据收货人id查询收货人
    public Consignee queryConsigneeById(Long consigneeId);

    // 如果consigneeId不是0则是编辑收货人
    public void editConsignee(Consignee consignee);

    // 删除收货人
    public void deleteConsignee(Long consigneeId);
}
