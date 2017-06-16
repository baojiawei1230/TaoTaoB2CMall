package com.zeus.comment.service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeus.comment.mapper.CommentTagsMapper;
import com.zeus.comment.mapper.CommentsMapper;
import com.taotao.comment.pojo.*;
import com.zeus.comment.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommectService {
    
    @Autowired
    private CommentsMapper commentsMapper;
    
    @Autowired
    private CommentTagsMapper commentTagsMapper;
    @Autowired
    private CommentTagsService commentTagsService;
    
    //分页查询商品评价信息
    public Comment showComments(Integer page, Integer rows, Long itemId) {
        Comment comment = new Comment();
        
        //根据商品id,查询出商品评价信息
        Example example = new Example(Comments.class);
        example.setOrderByClause("created_time DESC");
        example.createCriteria().andEqualTo("productId", itemId);
        //开启分页
        PageHelper.startPage(page, rows);
        List<Comments> commentsList = this.commentsMapper.selectByExample(example);
        //获取分页数据
        PageInfo<Comments> pageInfo = new PageInfo<Comments>(commentsList);
        //封装Comment属性1，总条数
        comment.setTotal(pageInfo.getTotal());
        //封装参数2：Comments对象，comments对象中需要封装标签集合对象
        List<Comments> comments = pageInfo.getList();
        if(comments==null){
            comments = new ArrayList<Comments>();
        }else{
            for (Comments c : comments) {
                //将时间转化成字符串
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                c.setCreationTime(df.format(c.getCreatedTime()));
                String[] tags = c.getTags().split(",");
                List<CommentTags> commentTags = this.setTagsToComments(tags);
                c.setCommentTags(commentTags);
                c.setReferenceId(Integer.valueOf(c.getProductId()));
                c.setReferenceName("三星 Galaxy Grand 2 (G7108V) 白色 移动4G手机");
                c.setUserImageUrl("storage.jd.com/i.imageUpload/6a645f3765363563356136346430616431333930363139363832343134_sma.jpg");
                c.setUserClientShow("<a href='http://app.jd.com/android.html' target='_blank'>来自京东Android客户端</a>");
            }
        }
        comment.setComments(comments);
        //根据商品id, 查询商品热门标签
        List<CommentTags> commentTags = this.commentTagsMapper.findCommentTags(itemId);
        if(commentTags==null){
            commentTags = new ArrayList<CommentTags>();
        }
        //封装参数3，该商品对应的热门标签
        comment.setHotCommentTagStatistics(commentTags);
        //封装参数4根据商品评价信息,汇总商品评价
        ProductCommentSummary productSummary = this.getRates(itemId);
        comment.setProductCommentSummary(productSummary);
        comment.setScore(0);
        List<TopFiveCommentVos> topFiveCommentVos = new ArrayList<TopFiveCommentVos>();
        comment.setTopFiveCommentVos(topFiveCommentVos);
        //分页返回数据
        return comment;
    }

    /**
     * 查询该商品对应的标签对象集合
     * @param tags
     * @return
     */
    private List<CommentTags> setTagsToComments(String[] tags) {
        List<CommentTags> tagsList = new ArrayList<CommentTags>();
        for (String tagName : tags) {
            CommentTags commentTags = new CommentTags();
            commentTags.setName(tagName);
            CommentTags tag = commentTagsService.insertTags(commentTags);
            tag.setRid(tag.getCount());
            tagsList.add(tag);
        }
        
        return tagsList;
    }


    @SuppressWarnings("null")
    private ProductCommentSummary getRates(Long itemId){
        
        ProductCommentSummary summary = new ProductCommentSummary();
        
        //定义商品总评论数，好评数 中评数 差评数
        Integer goodCounts = 0, generalCounts = 0,poorCounts = 0;
        //查询商品对应所有评论数
        List<Comments> comments = this.commentsMapper.queryCommentsByProductId(itemId);
        for (Comments comment : comments) {
            if(comment.getScore()>=3){
                goodCounts++;
            }else if(comment.getScore()<2){
                poorCounts++;
            }else{
                generalCounts++;
            }
        }
        summary.setGoodRateShow(goodCounts);  //好评
        summary.setGeneralRateShow(generalCounts);  //中评 
        summary.setPoorRateShow(poorCounts); //差评
        
        return summary;
    }
    
}
