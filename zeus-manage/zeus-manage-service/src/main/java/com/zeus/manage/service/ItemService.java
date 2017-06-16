package com.zeus.manage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeus.common.service.ApiService;
import com.zeus.manage.bean.EasyUIResult;
import com.zeus.manage.mapper.ItemMapper;
import com.zeus.manage.pojo.Item;
import com.zeus.manage.pojo.ItemDesc;
import com.zeus.manage.pojo.ItemParamItem;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemService extends BaseService<Item> {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescService itemDescService;

    @Autowired
    private ItemParamItemService itemParamItemService;

    @Autowired
    private ApiService apiService;

    @Value("${TAOTAO_WEB_URL}")
    private String TAOTAO_WEB_URL;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public void saveItem(Item item, String desc, String itemParams) {
        // 设置初始数据
        item.setStatus(1);
        item.setId(null);// 强制设置id为null，避免漏洞的产生
        super.save(item);

        // 保存商品描述数据
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        this.itemDescService.save(itemDesc);

        // 保存规格参数数据
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(item.getId());
        itemParamItem.setParamData(itemParams);
        this.itemParamItemService.save(itemParamItem);

        // 发送消息到交换机，通知其他系统新增商品
        sendMsg(item.getId(), "insert");
    }

    /**
     * 通过更新时间倒序排序
     * 
     * @param page
     * @param rows
     * @return
     */
    public EasyUIResult queryItemList(Integer page, Integer rows) {
        Example example = new Example(Item.class);
        example.setOrderByClause("updated DESC");
        // 设置分页参数
        PageHelper.startPage(page, rows);
        List<Item> items = this.itemMapper.selectByExample(example);
        PageInfo<Item> pageInfo = new PageInfo<Item>(items);
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    public void updateItem(Item item, String desc, String itemParams, Long itemParamId) {
        // 设置不能修改的字段为null
        item.setCreated(null);
        item.setStatus(null);
        super.updateSelective(item);

        // 修改描述数据
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        this.itemDescService.updateSelective(itemDesc);

        // 修改规格参数数据
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setId(itemParamId);
        itemParamItem.setParamData(itemParams);
        this.itemParamItemService.updateSelective(itemParamItem);

        // try {
        // // 通知其他系统商品已经更新
        // String url = TAOTAO_WEB_URL + "/cache/data/" + item.getId() + ".html";
        // this.apiService.doPost(url);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // 发送消息到交换机，通知其他系统该商品已经更新
        sendMsg(item.getId(), "update");
    }

    private void sendMsg(Long itemId, String type) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("itemId", itemId);
            map.put("type", type);
            this.rabbitTemplate.convertAndSend("item." + type, MAPPER.writeValueAsString(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
