package com.zeus.comment.mapper;

import com.github.abel533.mapper.Mapper;
import com.zeus.comment.pojo.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsMapper extends Mapper<Comments>{

    List<Comments> queryCommentsByProductId(@Param("itemId") Long itemId);

    
    
}
