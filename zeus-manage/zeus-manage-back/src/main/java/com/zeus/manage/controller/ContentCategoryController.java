package com.zeus.manage.controller;

import com.zeus.manage.pojo.ContentCategory;
import com.zeus.manage.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("content/category")
@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 根据父节点id查询内容分类列表
     * 
     * @param parentId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ContentCategory>> queryContentCategoryList(
            @RequestParam(value = "id", defaultValue = "0") Long parentId) {
        try {
            ContentCategory record = new ContentCategory();
            record.setParentId(parentId);
            List<ContentCategory> contentCategories = this.contentCategoryService.queryListByWhere(record);
            if (contentCategories == null || contentCategories.isEmpty()) {
                // 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(contentCategories);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 新增节点
     * 
     * @param contentCategory
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContentCategory> add(ContentCategory contentCategory) {
        // 新增
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);
        this.contentCategoryService.save(contentCategory);

        // 判断当前节点的父节点的isParent是否为true，如果不是，设置为true
        ContentCategory parent = this.contentCategoryService.queryById(contentCategory.getParentId());
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            this.contentCategoryService.update(parent);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(contentCategory);
    }

    /**
     * 重命名
     * 
     * @param contentCategory
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> rename(ContentCategory contentCategory) {
        // 重命名
        this.contentCategoryService.updateSelective(contentCategory);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 删除
     * 
     * @param contentCategory
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(ContentCategory contentCategory) {

        // 定义集合，用于收集待删除的节点id
        List<Object> ids = new ArrayList<Object>();
        ids.add(contentCategory.getId());

        // 查询该节点的所有子节点
        findAllSubNode(ids, contentCategory.getId());

        // 批量删除
        this.contentCategoryService.deleteByIds(ids, "id", ContentCategory.class);

        // 判断当前节点的父节点是否还有其他子节点，如果没有，设置isParent为false
        ContentCategory record = new ContentCategory();
        record.setParentId(contentCategory.getParentId());
        List<ContentCategory> list = this.contentCategoryService.queryListByWhere(record);
        if (list.isEmpty()) {
            ContentCategory parent = new ContentCategory();
            parent.setId(contentCategory.getParentId());
            parent.setIsParent(false);
            this.contentCategoryService.updateSelective(parent);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 递归查询所有的子节点
     * 
     * @param ids
     * @param parentId
     */
    private void findAllSubNode(List<Object> ids, Long parentId) {
        ContentCategory record = new ContentCategory();
        record.setParentId(parentId);
        List<ContentCategory> list = this.contentCategoryService.queryListByWhere(record);
        for (ContentCategory contentCategory : list) {
            ids.add(contentCategory.getId());
            // 开始递归
            findAllSubNode(ids, contentCategory.getId());
        }
    }
}
