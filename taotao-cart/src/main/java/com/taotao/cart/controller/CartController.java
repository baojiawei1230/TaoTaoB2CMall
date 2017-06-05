package com.taotao.cart.controller;

import com.taotao.cart.bean.User;
import com.taotao.cart.pojo.Cart;
import com.taotao.cart.service.CartCookieService;
import com.taotao.cart.service.CartService;
import com.taotao.cart.threadlocal.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("cart")
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartCookieService cartCookieService;

    /**
     * 将商品加入购物车
     * 
     * @param itemId
     * @return
     */
    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public String addItemToCart(@PathVariable("itemId") Long itemId, HttpServletRequest request,
                                HttpServletResponse response) {
        User user = UserThreadLocal.get();
        // 判断用户的登录状态
        if (null == user) {
            // 未登录
            this.cartCookieService.addItemToCart(itemId, request, response);
        } else {
            // 已登录
            this.cartService.addItemToCart(itemId);
        }
        return "redirect:/cart.html";
    }

    /**
     * 显示购物车商品列表
     * 
     * @return
     */
    /**
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("cart");
        List<Cart> cartList = null;
        User user = UserThreadLocal.get();
        // 判断用户的登录状态
        if (null == user) {
            // 未登录
            cartList = this.cartCookieService.queryCartList(request);
        } else {
            // 已登录
            cartList = this.cartService.queryCartList();
        }

        mv.addObject("cartList", cartList);
        return mv;
    }

    /**
     * 对外提供接口服务，查询购物车列表数据
     * 
     * @param userId
     * @return
     */
    // http://cart.taotao.com/service/cart?userId=1001
    @RequestMapping(method = RequestMethod.GET, params = "userId")
    public ResponseEntity<List<Cart>> queryCartListByUserId(@RequestParam("userId") Long userId) {
        List<Cart> carts = this.cartService.queryCartList(userId);
        return ResponseEntity.ok(carts);
    }
    /******
     * 对外提供接口
     * @param userId
     * @param itemId
     * @return
     */
    // http://cart.taotao.com/service/cart/part?userId=1001&itemIds=XXXXX
    @RequestMapping(value="part",method = RequestMethod.GET, params = {"userId","itemId"})
    public ResponseEntity<List<Cart>> addItemsToCart(@RequestParam("userId") Long userId,
                                                     @RequestParam("itemId")List<Long> itemId){
        List<Cart> carts = this.cartService.queryCartList(userId,itemId);
        return ResponseEntity.ok(carts);
    }
    
    /**
     * 修改数量
     * 
     * @param itmeId
     * @param num
     * @return
     */
    @RequestMapping(value = "update/num/{itemId}/{num}")
    public ResponseEntity<Void> updateNum(@PathVariable("itemId") Long itemId,
                                          @PathVariable("num") Integer num, HttpServletRequest request, HttpServletResponse response) {
        User user = UserThreadLocal.get();
        // 判断用户的登录状态
        if (null == user) {
            // 未登录
            this.cartCookieService.updateNum(itemId, num, request, response);
        } else {
            // 已登录
            this.cartService.updateNum(itemId, num);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 删除购物车中的商品
     * 
     * @param itemId
     * @return
     */
    @RequestMapping(value = "delete/{itemId}")
    public String deleteItem(@PathVariable("itemId") Long itemId, HttpServletRequest request,
                             HttpServletResponse response) {
        User user = UserThreadLocal.get();
        // 判断用户的登录状态
        if (null == user) {
            // 未登录
            this.cartCookieService.deleteItem(itemId, request, response);
        } else {
            // 已登录
            this.cartService.deleteItem(itemId);
        }
        return "redirect:/cart.html";
    }

}
