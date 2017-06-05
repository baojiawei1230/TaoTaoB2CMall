package com.taotao.front.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.bean.HttpResult;
import com.taotao.common.service.ApiService;
import com.taotao.front.bean.Cart;
import com.taotao.front.bean.Consignee;
import com.taotao.front.bean.Order;
import com.taotao.front.bean.User;
import com.taotao.front.threadLocal.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ApiService apiService;

    @Value("${TAOTAO_ORDER_URL}")
    private String TAOTAO_ORDER_URL;

    @Value("${TAOTAO_CART_URL}")
    private String TAOTAO_CART_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public String submit(Order order) {
        User user = UserThreadLocal.get();
        String url = TAOTAO_ORDER_URL + "/order/create";
        order.setBuyerNick(user.getUsername());
        order.setUserId(user.getId());
        try {
            String jsonData=MAPPER.writeValueAsString(order);
            HttpResult httpResult = this.apiService.doPostJson(url, jsonData);
            if (httpResult.getStatus() == 200) {
                JsonNode jsonNode = MAPPER.readTree(httpResult.getData());
                if (jsonNode.get("status").asInt() == 200) {
                    // 返回订单id
                    return jsonNode.get("data").asText();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据订单id查询订单
     * 
     * @param orderId
     * @return
     */
    public Order queryOrderById(String orderId) {
        try {
            String url = TAOTAO_ORDER_URL + "/order/query/" + orderId;
            String jsonData = this.apiService.doGet(url);
            if (null == jsonData) {
                return null;
            }
            return MAPPER.readValue(jsonData, Order.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cart> queryCartListByUserId(Long userId) {
        String url = TAOTAO_CART_URL + "/service/cart?userId=" + userId;
        try {
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)) {
                return MAPPER.readValue(jsonData,
                        MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 查询收货人列表
    public List<Consignee> queryConsigneeListByUserId(Long userId) {
        try {
            String url = TAOTAO_ORDER_URL + "/service/consignee/queryConsigneeList/" + userId;
            String jsonData = this.apiService.doGet(url);
            List<Consignee> consigneeList = MAPPER.readValue(jsonData, MAPPER.getTypeFactory()
                    .constructCollectionType(List.class, Consignee.class));
            return consigneeList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cart> queryCartListByUserId(Long userId, List<Long> itemIds) {
        StringBuffer sb=new StringBuffer();
        for (Long id:itemIds) {
            sb.append("&itemId=").append(id);
        }
        String url = TAOTAO_CART_URL + "/service/cart/part?userId=" + userId+sb.toString();
        try {
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)) {
                return MAPPER.readValue(jsonData,
                        MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
