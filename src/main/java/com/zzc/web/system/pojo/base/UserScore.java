package com.zzc.web.system.pojo.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zzc.core.common.entity.IdEntity;

@Entity
@Table(name="t_user_score")
public class UserScore extends IdEntity {
	private String score;
	private String userId;
	
	@Column
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
