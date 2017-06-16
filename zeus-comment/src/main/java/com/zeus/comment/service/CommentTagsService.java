package com.zeus.comment.service;

import com.zeus.comment.mapper.CommentTagsMapper;
import com.zeus.comment.pojo.CommentTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentTagsService {
    
    @Autowired
    private CommentTagsMapper commentTagsMapper;
    
    /**
     * 根据商品id 获取询商品的前十评价标签
     * 
     * @param itemId
     * @return
     */
    public List<CommentTags> findCommentTags(Long itemId) {
        return this.commentTagsMapper.findCommentTags(itemId);
    }

    /**
     *  判断标签在表中是否存在,如果存在,返回已存在的数据,如果不存在,创建标签
     * @param commentTags
     * @return
     */
    public CommentTags insertTags(CommentTags commentTags) {
        //查询数据库
        CommentTags tags = this.commentTagsMapper.selectOne(commentTags);
        if(null == tags){
            //数据库没有对应的标签,在数据库中追加数据
            commentTags.setCount(0);
            commentTags.setCreated(new Date());
            commentTags.setModified(commentTags.getCreated());
            commentTags.setStatus(1);
            this.commentTagsMapper.insertSelective(commentTags);
            return commentTags;
        }
        //数据库中有对应的标签..直接返回查询到的数据
        return tags;
    }
}
