package com.zeus.front.controller;

import com.zeus.common.service.RedisService;
import com.zeus.front.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("cache/data")
@Controller
public class CacheDataController {

    @Autowired
    private RedisService redisService;

    /**
     * 删除商品缓存
     * 
     * @param itemId
     * @return
     */
    @RequestMapping(value = "{itemId}", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteItemCache(@PathVariable("itemId") Long itemId) {
        this.redisService.del(ItemService.REDIS_KEY + itemId);
        return ResponseEntity.ok(null);
    }

}
