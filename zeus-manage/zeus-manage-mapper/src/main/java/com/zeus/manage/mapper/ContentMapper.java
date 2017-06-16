package com.zeus.manage.mapper;

import com.github.abel533.mapper.Mapper;
import com.zeus.manage.pojo.Content;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContentMapper extends Mapper<Content> {

    List<Content> queryContentList(@Param("categoryId") Long categoryId);

}
