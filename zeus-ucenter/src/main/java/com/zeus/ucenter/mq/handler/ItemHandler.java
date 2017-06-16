package com.zeus.ucenter.mq.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemHandler {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private RedisService redisService;

    public void execute(String msg) {
        try {
            JsonNode jsonNode = MAPPER.readTree(msg);
            Long itemId = jsonNode.get("itemId").asLong();
            // 删除redis中的数据
           //等待实现 this.redisService.del(ItemService.REDIS_KEY + itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
