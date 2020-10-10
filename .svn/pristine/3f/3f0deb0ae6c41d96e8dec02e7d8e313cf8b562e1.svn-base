package com.tian.sakura.cdd.srv.service.user;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserSign;
import com.tian.sakura.cdd.db.domain.user.UserSignMax;
import com.tian.sakura.cdd.db.manage.user.UserSignManage;
import com.tian.sakura.cdd.db.manage.user.UserSignMaxManage;
import com.tian.sakura.cdd.srv.web.user.dto.UserSignQueryRspBody;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 签到api服务
 *
 * @author lvzonggang
 */

@Service
@Transactional
@Slf4j
public class UserSignApiService {

    @Autowired
    private UserSignManage signManage;
    @Autowired
    private UserSignMaxManage signMaxManage;

    public UserSignQueryRspBody signWeekList(SUser user) {
        UserSignQueryRspBody body = new UserSignQueryRspBody();
        DateTime now = DateTime.now();
        //当前日志所住的周
        int dayOfWeek = now.getDayOfWeek();
        // 本周第一天
        DateTime firstDay = now.minusDays(dayOfWeek - 1);

        Map<String,Boolean> signDateOfWeek = new HashMap<>();
        for (int i=0; i<7; i++) {
            signDateOfWeek.put(firstDay.plusDays(i).toString("yyyy-MM-dd"),false);
        }

        List<UserSign> signOfWeekList = signManage.selectByUserIdBetweenSignDate(user.getId(),
                firstDay.toString("yyyy-MM-dd"), now.toString("yyyy-MM-dd"));
        for (UserSign userSign : signOfWeekList) {
            if (signDateOfWeek.containsKey(userSign.getSignDate())) {
                signDateOfWeek.put(userSign.getSignDate(), true);
            }
        }
        body.setSignDataThisWeek(signDateOfWeek);

        // 连续签到次数
        UserSignMax userSignMax = signMaxManage.selectByPrimary(user.getId());
        if (userSignMax == null) {
            body.setSignMaxCnt(0);
        } else {
            body.setSignMaxCnt(userSignMax.getSignMaxCnt());
        }
        return body;

    }

    public static void main(String[] args) {
        DateTime now = DateTime.now();
        //当前日志所住的周


        System.out.println(now.getDayOfWeek());
    }

    public void sign(SUser user) {
        // 查询该客户当天的签到记录
        DateTime now = DateTime.now();
        String nowStr = now.toString("yyyy-MM-dd");
        UserSign todaySign = signManage.selectByUserIdAndSignDate(user.getId(),nowStr);
        if (todaySign != null) {
            throw new BizRuntimeException(RespCodeEnum.TODAY_SIGNED);
        }

        // 插入签到记录
        todaySign = new UserSign();
        todaySign.setId(IdGenUtil.uuid());
        todaySign.setUserId(user.getId());
        todaySign.setSignDate(nowStr);
        todaySign.setSignYear(now.getYear());
        todaySign.setSignMonth(now.getMonthOfYear());
        todaySign.setSignDay(now.getDayOfMonth());
        signManage.insert(todaySign);


        // 查询最大连续签到天数
        UserSignMax userSignMax = signMaxManage.selectByPrimary(user.getId());
        if (userSignMax == null) {
            userSignMax = new UserSignMax();
            userSignMax.setUserId(user.getId());
            userSignMax.setSignMaxCnt(1);
            signMaxManage.insert(userSignMax);
        } else {
            //查询上一天是否签到
            String lastDayStr = now.plusDays(-1).toString("yyyy-MM-dd");
            UserSign lastDaySign = signManage.selectByUserIdAndSignDate(user.getId(),lastDayStr);
            if (lastDaySign == null) {
                userSignMax.setSignMaxCnt(1);

            } else {
                userSignMax.setSignMaxCnt(userSignMax.getSignMaxCnt()+1);
            }
            userSignMax.setCreateTime(null);
            signMaxManage.updateByPrimaryKeySelective(userSignMax);
        }

    }
}
