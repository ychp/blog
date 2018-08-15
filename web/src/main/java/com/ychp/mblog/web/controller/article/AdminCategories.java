package com.ychp.mblog.web.controller.article;

import com.ychp.async.publisher.AsyncPublisher;
import com.ychp.blog.bean.query.CategoryCriteria;
import com.ychp.blog.model.Category;
import com.ychp.blog.service.CategoryReadService;
import com.ychp.blog.service.CategoryWriteService;
import com.ychp.common.model.paging.Paging;
import com.ychp.mblog.web.async.article.CategoryUpdateEvent;
import com.ychp.redis.cache.annontation.DataCache;
import com.ychp.redis.cache.annontation.DataInvalidCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yingchengpeng
 * @date 2018-08-10
 */
@Api(description = "文章类目管理")
@RestController
@RequestMapping("/api/category")
public class AdminCategories {

    @Autowired
    private CategoryReadService categoryReadService;

    @Autowired
    private CategoryWriteService categoryWriteService;

    @Autowired
    private AsyncPublisher publisher;

    @ApiOperation(value = "类目创建接口", httpMethod = "POST")
    @PostMapping
    @DataInvalidCache("article:categories")
    public Long create(@RequestBody Category category) {
        return categoryWriteService.create(category);
    }

    @ApiOperation(value = "类目更新接口", httpMethod = "PUT")
    @PutMapping
    @DataInvalidCache("article:categories")
    public Boolean update(@ApiParam(example = "1") Long id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        Boolean result = categoryWriteService.update(category);
        publisher.post(new CategoryUpdateEvent(id, name));
        return result;
    }

    @ApiOperation("类目分页接口")
    @GetMapping("paging")
    public Paging<Category> paging(CategoryCriteria criteria) {
        return categoryReadService.paging(criteria);
    }

}
