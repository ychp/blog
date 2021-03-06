package com.ychp.mblog.web.controller.admin;

import com.ychp.common.model.paging.Paging;
import com.ychp.user.api.bean.query.UserCriteria;
import com.ychp.user.api.bean.query.UserLoginLogCriteria;
import com.ychp.user.api.bean.response.UserLoginLogVO;
import com.ychp.user.api.bean.response.UserVO;
import com.ychp.user.enums.UserStatusEnum;
import com.ychp.user.model.User;
import com.ychp.user.api.service.UserLoginLogReadService;
import com.ychp.user.api.service.UserReadService;
import com.ychp.user.api.service.UserWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yingchengpeng
 * @date 2018-08-09
 */
@Api(description = "运营管理账户用的数据接口")
@RestController
@RequestMapping("/api/admin/user")
public class AdminUsers {

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserWriteService userWriteService;

    @Autowired
    private UserLoginLogReadService userLoginLogReadService;

    @ApiOperation("用户详情接口")
    @GetMapping("{id}/findById")
    public UserVO detail(@ApiParam(example = "1") @PathVariable Long id) {
        return userReadService.findDetailById(id);
    }

    @ApiOperation("用户分页接口")
    @GetMapping("paging")
    public Paging<User> paging(UserCriteria criteria) {
        return userReadService.paging(criteria);
    }

    @ApiOperation("用户登录日志分页接口")
    @GetMapping("login-log/paging")
    public Paging<UserLoginLogVO> pagingLoginLog(UserLoginLogCriteria criteria) {
        return userLoginLogReadService.paging(criteria);
    }

    @ApiOperation("冻结用户")
    @PutMapping("{id}/frozen")
    public Boolean frozen(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setStatus(UserStatusEnum.FROZEN.getValue());
        return userWriteService.update(user);
    }

    @ApiOperation("解冻用户")
    @PutMapping("{id}/unfrozen")
    public Boolean unfrozen(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setStatus(UserStatusEnum.NORMAL.getValue());
        return userWriteService.update(user);
    }

}
