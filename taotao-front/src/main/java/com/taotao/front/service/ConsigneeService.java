package com.taotao.front.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.service.ApiService;
import com.taotao.front.bean.Consignee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsigneeService {
    @Autowired
    private ApiService apiService;

    @Value("${TAOTAO_ORDER_URL}")
    private String TAOTAO_ORDER_URL;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void addConsignee(Consignee consignee) {
        try {
            String jsonData = this.objectMapper.writeValueAsString(consignee);
            String url = TAOTAO_ORDER_URL + "/service/consignee/addConsignee";
            this.apiService.doPostJson(url, jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Consignee> queryConsigneeList(Long userId) {
        try {
            String url = TAOTAO_ORDER_URL + "/consignee/queryConsigneeList/" + userId;
            String jsonData = this.apiService.doGet(url);
            List<Consignee> consigneeList = this.objectMapper.readValue(jsonData, this.objectMapper
                    .getTypeFactory().constructCollectionType(List.class, Consignee.class));
            return consigneeList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public void deleteConsignee(Long consigneeId) {
        try {
            String url = TAOTAO_ORDER_URL + "/service/consignee/deleteConsignee/" + consigneeId;
            this.apiService.doPost(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
