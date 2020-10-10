package com.tian.sakura.cdd.db.domain.auth.vo;

import com.tian.sakura.cdd.common.entity.BaseQueryVo;
import com.tian.sakura.cdd.common.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class UserQueryVo extends BaseQueryVo {

	private String userNo;		//登录账户
	private String userName;	//用户姓名
	private String startTime;	//开始时间
	private String endTime;		//结束时间

	private Date beginDate;
	private Date endDate;
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		if (StringUtils.isNotEmpty(startTime)) {
			beginDate = DateUtils.parseDate(startTime + "T00:00:01");
		}
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
		if (StringUtils.isNotEmpty(endTime)) {
			endDate = DateUtils.parseDate(endTime + "T23:59:59");
		}
	}
	
	
}
