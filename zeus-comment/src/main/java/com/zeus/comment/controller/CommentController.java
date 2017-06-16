package com.zeus.comment.controller;

import com.zeus.comment.pojo.Comment;
import com.zeus.comment.service.CommectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("comments")
@Controller
public class CommentController {

    @Autowired
    private CommectService commectService;

    // 固定每页显示继续数10条
    private Integer rows = 10;

    /**
     * 查询商品评价列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Comment> showComments(
            @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam("itemId") Long itemId) {
       
        try {
            Comment comment= this.commectService.showComments(page, rows,itemId);
            if (null == comment) {
                // 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            // 200
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
