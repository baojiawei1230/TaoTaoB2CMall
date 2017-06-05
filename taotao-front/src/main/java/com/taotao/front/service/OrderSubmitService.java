package com.taotao.front.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.service.ApiService;
import com.taotao.front.bean.Consignee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderSubmitService {
    @Autowired
    private ApiService apiService;

    @Value("${TAOTAO_ORDER_URL}")
    private String TAOTAO_ORDER_URL;

    private ObjectMapper objectMapper = new ObjectMapper();

    // 如果是编辑页面,则要根据id去数据库查询数据,将数据回显到页面上
    public Consignee queryConsigneeById(Long consigneeId) {
        try {
            String url = TAOTAO_ORDER_URL + "/service/consignee/queryConsigneeById/" + consigneeId;
            String jsonData = this.apiService.doGet(url);
            Consignee consignee = this.objectMapper.readValue(jsonData, Consignee.class);
            return consignee;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
