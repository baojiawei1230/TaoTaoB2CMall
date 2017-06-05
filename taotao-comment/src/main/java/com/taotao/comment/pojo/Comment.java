package com.taotao.comment.pojo;

import java.util.List;

/**
 * 评价信息
 * 
 * @author acer
 *
 */
public class Comment {

    Long total;
    
    String productAttr; // 商品属性

    ProductCommentSummary productCommentSummary; // 商品评价汇总
    
    List<CommentTags> hotCommentTagStatistics;

    public List<CommentTags> getHotCommentTagStatistics() {
        return hotCommentTagStatistics;
    }

    public void setHotCommentTagStatistics(List<CommentTags> hotCommentTagStatistics) {
        this.hotCommentTagStatistics = hotCommentTagStatistics;
    }

    Integer score; // 分数

    List<Comments> comments; // 具体评价信息

    List<TopFiveCommentVos> topFiveCommentVos;   // ？？？

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
    }

   

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public ProductCommentSummary getProductCommentSummary() {
        return productCommentSummary;
    }

    public void setProductCommentSummary(ProductCommentSummary productCommentSummary) {
        this.productCommentSummary = productCommentSummary;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<TopFiveCommentVos> getTopFiveCommentVos() {
        return topFiveCommentVos;
    }

    public void setTopFiveCommentVos(List<TopFiveCommentVos> topFiveCommentVos) {
        this.topFiveCommentVos = topFiveCommentVos;
    }

}
