package com.zeus.comment.mapper;

import com.github.abel533.mapper.Mapper;
import com.zeus.comment.pojo.CommentTags;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentTagsMapper extends Mapper<CommentTags>{

    List<CommentTags> findCommentTags(@Param("itemId") Long itemId);

}
