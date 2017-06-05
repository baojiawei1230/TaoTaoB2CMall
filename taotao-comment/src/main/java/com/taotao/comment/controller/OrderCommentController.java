package com.taotao.comment.controller;

import com.taotao.comment.bean.Order;
import com.taotao.comment.bean.OrderItem;
import com.taotao.comment.pojo.CommentTags;
import com.taotao.comment.pojo.Comments;
import com.taotao.comment.service.CommentTagsService;
import com.taotao.comment.service.CommentsService;
import com.taotao.comment.service.OrderItemService;
import com.taotao.comment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("comment")
@Controller
public class OrderCommentController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CommentTagsService commentTagsService;

    @Autowired
    private CommentsService commentService;

    // 先实现页面的跳转
    // 数据待完善
    // TODO
    @RequestMapping(value = "orderEvaluate/{itemId}/{orderId}", method = RequestMethod.GET)
    public ModelAndView orderEvaluate(@PathVariable("itemId") Long itemId,
            @PathVariable("orderId") String orderId) {
        ModelAndView mv = new ModelAndView("my-order-comment");
        OrderItem orderItem = this.orderItemService.queryOrderItem(itemId, orderId);
        mv.addObject("item", orderItem);
        // 显示商品信息
        Order order = this.orderService.queryOrderByOrderId(orderId);
        mv.addObject("order", order);

        // 显示商品热门评价标签
        List<CommentTags> tags = this.commentTagsService.findCommentTags(itemId);
        mv.addObject("tags", tags);
        return mv;
    }

    // 点击评价按钮，提交评价信息
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Boolean doEvaluate(Comments comments, HttpServletRequest request,
                       @RequestParam("itemId") Long itemId, @RequestParam("orderId") String orderId) {
        // 保存商品评价
        boolean flag = false;
        try {
            this.commentService.saveComments(itemId,comments, request, orderId);
            // 判断是否是该订单中所有商品都已经评价过，如果是，调用订单系统接口，修改订单的评价状态
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    /**
     * 保存用户在商品中添加的标签
     * @param commentTags
     * @return
     */
    @RequestMapping(value="tags",method=RequestMethod.POST)
    public ResponseEntity<CommentTags> insertTags(CommentTags commentTags){
        try {
            CommentTags tags = this.commentTagsService.insertTags(commentTags);
            return ResponseEntity.ok(tags);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
