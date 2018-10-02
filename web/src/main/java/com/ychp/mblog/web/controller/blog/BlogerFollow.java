package com.ychp.mblog.web.controller.blog;

import com.ychp.async.publisher.AsyncPublisher;
import com.ychp.common.model.paging.Paging;
import com.ychp.common.util.SessionContextUtils;
import com.ychp.mblog.web.async.user.CancelFollowEvent;
import com.ychp.mblog.web.async.user.FollowEvent;
import com.ychp.user.api.bean.query.FollowRelationCriteria;
import com.ychp.user.api.service.FollowRelationReadService;
import com.ychp.user.api.service.FollowRelationWriteService;
import com.ychp.user.model.FollowRelation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yingchengpeng
 * @date 2018/10/2
 */
@Api(description = "关注")
@RestController
@RequestMapping("/api/bloger")
public class BlogerFollow {

    @Autowired
    private FollowRelationWriteService followRelationWriteService;

    @Autowired
    private FollowRelationReadService followRelationReadService;

    @Autowired
    private AsyncPublisher publisher;

    @ApiOperation(value = "关注", httpMethod = "POST")
    @PostMapping("follow")
    public Boolean follow(Long followeeId) {
        Long followerId = SessionContextUtils.getUserId();
        FollowRelation relation = new FollowRelation();
        relation.setFolloweeId(followeeId);
        relation.setFollowerId(followerId);
        relation.setIsSubscribe(true);
        followRelationWriteService.create(relation);
        publisher.post(new FollowEvent(followeeId, followerId));
        return true;
    }

    @ApiOperation(value = "取消关注", httpMethod = "POST")
    @PostMapping("cancel-follow")
    public Boolean cancelFollow(Long followeeId) {
        Long followerId = SessionContextUtils.getUserId();
        Boolean result= followRelationWriteService.delete(followeeId, followerId);
        if(result) {
            publisher.post(new CancelFollowEvent(followeeId, followerId));
        }
        return result;
    }

    @ApiOperation(value = "粉丝列表", httpMethod = "GET")
    @GetMapping("follower-paging")
    public Paging<FollowRelation> pagingByFollowee(FollowRelationCriteria criteria) {
        criteria.setFolloweeId(SessionContextUtils.getUserId());
        return followRelationReadService.paging(criteria);
    }

    @ApiOperation(value = "关注列表", httpMethod = "GET")
    @GetMapping("followee-paging")
    public Paging<FollowRelation> pagingByFollower(FollowRelationCriteria criteria) {
        criteria.setFollowerId(SessionContextUtils.getUserId());
        return followRelationReadService.paging(criteria);
    }

    @ApiOperation(value = "订阅", httpMethod = "POST")
    @PostMapping("follower/subscribe")
    public Boolean subscribe(Long followeeId) {
        Long followerId = SessionContextUtils.getUserId();
        FollowRelation relation = new FollowRelation();
        relation.setFolloweeId(followeeId);
        relation.setFollowerId(followerId);
        relation.setIsSubscribe(true);
        return followRelationWriteService.update(relation);
    }

    @ApiOperation(value = "取消订阅", httpMethod = "POST")
    @PostMapping("follower/cancel-subscribe")
    public Boolean cancelSubscribe(Long followeeId) {
        Long followerId = SessionContextUtils.getUserId();
        FollowRelation relation = new FollowRelation();
        relation.setFolloweeId(followeeId);
        relation.setFollowerId(followerId);
        relation.setIsSubscribe(false);
        return followRelationWriteService.update(relation);
    }

}