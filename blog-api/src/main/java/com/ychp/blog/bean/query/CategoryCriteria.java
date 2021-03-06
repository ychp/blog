package com.ychp.blog.bean.query;

import com.ychp.common.model.paging.PagingCriteria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yingchengpeng
 * @date 2018/08/10
 */
@ApiModel(description = "查询类型")
@ToString
public class CategoryCriteria extends PagingCriteria {

    private static final long serialVersionUID = 7346127129736911979L;

    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    @Getter
    @Setter
    @ApiModelProperty(value = "用户ID", example = "1")
    private Long userId;

    @Getter
    @Setter
    @ApiModelProperty("名称")
    private String name;


}