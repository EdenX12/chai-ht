package com.tian.sakura.cdd.db.dao.user;

import java.util.List;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.user.UserRelation;
import com.tian.sakura.cdd.db.manage.mytask.vo.MyTaskQueryVo;
import com.tian.sakura.cdd.db.manage.mytask.vo.UserRelationVo;
import org.apache.ibatis.annotations.Param;

public interface UserRelationMapper extends AbstractSingleMapper<UserRelation, String> {

	//获取一级下级
    public List<UserRelationVo> getOneLevel(MyTaskQueryVo queryVo);
    
	//获取直接上级
    public UserRelation getUpLevel(String userId);

    //获取战队人数
    Integer selectCntByUserIdAndRelationType(MyTaskQueryVo queryVo);

    //获取上级数据- 近卫军或预备队
    List<UserRelation>  selectByUserIdAndRelationType(MyTaskQueryVo queryVo);

    List<UserRelation> selectByUserIds(List<String> list);

    //获取上级记录
    List<UserRelation> selectByUnionidAndRelationType(@Param("unionId") String unionId, @Param("relationType")int relationType);

    List<UserRelation> selectByUnionidAndParentId(@Param("unionId") String unionId, @Param("parentId")String parentId);

    UserRelation selectOneTeamParentLatelyByUserId(String userId);
}