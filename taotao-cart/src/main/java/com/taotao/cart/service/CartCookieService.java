package com.taotao.cart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cart.bean.Item;
import com.taotao.cart.pojo.Cart;
import com.taotao.common.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartCookieService {

    public static final String COOKIE_NAME = "TT_CART";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ItemService itemService;

    /**
     * 商品加入购物车
     * 
     * @param itemId
     * @param request
     * @param response
     */
    public void addItemToCart(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<Cart> carts = queryCartList(request);
        // 判断该商品在购物中是否存在，如果不存在直接添加，如果存在，数量相加
        Cart cart = null;
        for (Cart c : carts) {
            if (c.getItemId().intValue() == itemId.intValue()) {
                cart = c;
                break;
            }
        }

        if (null == cart) {
            // 购物车中不存在
            cart = new Cart();
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            cart.setItemId(itemId);
            cart.setNum(1); // TODO 默认为1

            Item item = this.itemService.queryItemById(itemId);
            String[] images = StringUtils.split(item.getImage(), ',');
            if (images != null && images.length > 0) {
                cart.setItemImage(images[0]);
            }
            cart.setItemPrice(item.getPrice());
            cart.setItemTitle(item.getTitle());

            carts.add(cart);
        } else {
            // 该商品已经存在，数量相加，默认实现加1
            cart.setNum(cart.getNum() + 1); // TODO
            cart.setUpdated(new Date());
        }
        saveItemList(carts, request, response);
    }

    private void saveItemList(List<Cart> carts, HttpServletRequest request, HttpServletResponse response) {
        // 将数据写入到cookie中
        try {
            CookieUtils.setCookie(request, response, COOKIE_NAME, MAPPER.writeValueAsString(carts), 60 * 60
                    * 24 * 30 * 3, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询购物车列表
     * 
     * @param request
     * @return
     */
    public List<Cart> queryCartList(HttpServletRequest request) {
        List<Cart> carts = null;
        String cookieValue = CookieUtils.getCookieValue(request, COOKIE_NAME, true);
        if (StringUtils.isEmpty(cookieValue)) {
            carts = new ArrayList<Cart>();
        } else {
            try {
                carts = MAPPER.readValue(cookieValue,
                        MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
            } catch (Exception e) {
                e.printStackTrace();
                carts = new ArrayList<Cart>();
            }
        }
        return carts;
    }

    /**
     * 修改购买数量
     * 
     * @param itemId
     * @param num
     * @param request
     * @param response
     */
    public void updateNum(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        List<Cart> carts = queryCartList(request);
        // 判断该商品在购物中是否存在，如果不存在直接添加，如果存在，数量相加
        Cart cart = null;
        for (Cart c : carts) {
            if (c.getItemId().intValue() == itemId.intValue()) {
                cart = c;
                break;
            }
        }

        if (null == cart) {
            return;
        }

        // 该商品已经存在，数量相加，默认实现加1
        cart.setNum(num);
        cart.setUpdated(new Date());

        saveItemList(carts, request, response);
    }

    /**
     * 删除商品
     * 
     * @param itemId
     * @param request
     * @param response
     */
    public void deleteItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<Cart> carts = queryCartList(request);
        // 判断该商品在购物中是否存在，如果不存在直接添加，如果存在，数量相加
        for (Cart c : carts) {
            if (c.getItemId().intValue() == itemId.intValue()) {
                carts.remove(c);
                break;
            }
        }
        saveItemList(carts, request, response);
    }

}
