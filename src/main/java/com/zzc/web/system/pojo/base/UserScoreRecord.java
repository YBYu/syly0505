package com.zzc.web.system.pojo.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zzc.core.common.entity.IdEntity;

@Entity
@Table(name="t_user_score_record")
public class UserScoreRecord extends IdEntity {
	private String scoreChange;
	private String time;
	private String remark;
	private String userId;
	
	@Column(name="score_change")
	public String getScoreChange() {
		return scoreChange;
	}
	public void setScoreChange(String scoreChange) {
		this.scoreChange = scoreChange;
	}
	@Column
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Column
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
