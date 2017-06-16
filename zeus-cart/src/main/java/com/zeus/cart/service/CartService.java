package com.zeus.cart.service;

import com.github.abel533.entity.Example;
import com.zeus.cart.bean.Item;
import com.zeus.cart.bean.User;
import com.zeus.cart.mapper.CartMapper;
import com.zeus.cart.pojo.Cart;
import com.zeus.cart.threadlocal.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ItemService itemService;

    public void addItemToCart(Long itemId) {
        User user = UserThreadLocal.get();
        // 判断该商品是否已经存在购物车中
        Cart record = new Cart();
        record.setItemId(itemId);
        record.setUserId(user.getId());

        Cart cart = this.cartMapper.selectOne(record);

        if (null == cart) {
            // 购物车中不存在
            cart = new Cart();
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            cart.setItemId(itemId);
            cart.setUserId(user.getId());
            cart.setNum(1); // TODO 默认为1

            Item item = this.itemService.queryItemById(itemId);
            String[] images = StringUtils.split(item.getImage(), ',');
            if (images != null && images.length > 0) {
                cart.setItemImage(images[0]);
            }
            cart.setItemPrice(item.getPrice());
            cart.setItemTitle(item.getTitle());

            this.cartMapper.insert(cart);
        } else {
            // 该商品已经存在，数量相加，默认实现加1
            cart.setNum(cart.getNum() + 1); // TODO
            cart.setUpdated(new Date());
            this.cartMapper.updateByPrimaryKey(cart);
        }
    }

    /**
     * 查询购物车列表，根据创建时间倒序排序
     * 
     * @return
     */
    public List<Cart> queryCartList() {
        User user = UserThreadLocal.get();
        return this.queryCartList(user.getId());
    }

    public List<Cart> queryCartList(Long userId) {
        Example example = new Example(Cart.class);
        example.setOrderByClause("created DESC");
        example.createCriteria().andEqualTo("userId", userId);
        return this.cartMapper.selectByExample(example);
    }

    public void updateNum(Long itemId, Integer num) {
        // 修改的数据
        Cart record = new Cart();
        record.setNum(num);
        record.setUpdated(new Date());

        // 修改条件
        Example example = new Example(Cart.class);
        example.createCriteria().andEqualTo("itemId", itemId)
                .andEqualTo("userId", UserThreadLocal.get().getId());
        this.cartMapper.updateByExampleSelective(record, example);
    }

    public void deleteItem(Long itemId) {
        Cart record = new Cart();
        record.setItemId(itemId);
        record.setUserId(UserThreadLocal.get().getId());
        this.cartMapper.delete(record);
    }

    public List<Cart> queryCartList(Long userId, List<Long> itemIds) {
        List<Object> itemIdParams=new ArrayList<Object>(itemIds);
        Example example = new Example(Cart.class);
        example.setOrderByClause("created DESC");
        example.createCriteria().andEqualTo("userId",userId).andIn("itemId", itemIdParams);
        return this.cartMapper.selectByExample(example);
    }

}
