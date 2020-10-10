package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.dict.ERelationType;
import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.user.UserRelationMapper;
import com.tian.sakura.cdd.db.domain.user.UserRelation;
import com.tian.sakura.cdd.db.manage.mytask.vo.MyTaskQueryVo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户上下级关系表
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserRelationManage extends AbstractSingleManage<UserRelation, String> {

    @Autowired
    private UserRelationMapper userRelationMapper;

    @Override
    protected AbstractSingleMapper<UserRelation, String> getSingleMapper() {
        return userRelationMapper;
    }


    public Integer selectCntByUserIdAndRelationType(String userId, int relationType) {
        MyTaskQueryVo queryVo = MyTaskQueryVo.builder()
                .userId(userId)
                .relationType(relationType)
                .build();
        Integer count = userRelationMapper.selectCntByUserIdAndRelationType(queryVo);
        return count != null ? count : 0;
    }

    public Integer selectCntOfTodayByUserIdAndRelationType(String userId, int relationType) {
        MyTaskQueryVo queryVo = MyTaskQueryVo.builder()
                .userId(userId)
                .relationType(relationType)
                .beginDate(DateTime.now().minusDays(-1).toDate())
                .endDate(DateTime.now().minusDays(1).toDate())
                .build();

        Integer count = userRelationMapper.selectCntByUserIdAndRelationType(queryVo);
        return count != null ? count : 0;
    }

    public List<UserRelation> selectByUserIds(List<String> userIds) {
        if (userIds.isEmpty()) {
            return new ArrayList<>();
        }
        return userRelationMapper.selectByUserIds(userIds);
    }

    /**
     * 查询上级用户
     *
     * @param userId
     * @param
     * @return
     */
    public UserRelation selectGuardParentIdByUserId(String userId) {
        MyTaskQueryVo queryVo = MyTaskQueryVo.builder()
                .userId(userId)
                .relationType(ERelationType.GUARD.getCode())
                .build();
        List<UserRelation> relationList = userRelationMapper.selectByUserIdAndRelationType(queryVo);
        if (relationList.size() > 0) {
            return relationList.get(0);
        }
        return null;
    }

    public UserRelation selectGuardParentIdByUnionId(String unionId) {
        int relationType = ERelationType.GUARD.getCode();
        List<UserRelation> relationList = userRelationMapper.selectByUnionidAndRelationType(unionId, relationType);
        if (relationList.size() > 0) {
            return relationList.get(0);
        }
        return null;
    }

    // 查询unionid和parentid的关系记录 原则上只有一条
    public List<UserRelation> selectByUnionidAndParentId(String unionId, String parentId) {
        return userRelationMapper.selectByUnionidAndParentId(unionId, parentId);
    }

    // 查询最近一个预备队上级
    public UserRelation selectOneTeamParentLatelyByUserId(String userId) {
        return userRelationMapper.selectOneTeamParentLatelyByUserId(userId);
    }
}
