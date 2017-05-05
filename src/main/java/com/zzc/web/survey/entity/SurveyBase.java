package com.zzc.web.survey.entity;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_survey_base")
@SuppressWarnings("serial")
public class SurveyBase implements java.io.Serializable{
	
	private String id;
	private String name;
	private String type;
	private String status;
	private Date createTime;
	private Date publishTime;
	private String publishDepts;
	private Date regainTime;
	private String creater;
	private String declare;
	private Date startDate;
	private Date endDate;
	
	@Id
	@Column(name = "id", nullable = false, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "question_name", nullable = true, length = 300)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "question_type", nullable = true, length = 1)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "question_status", nullable = true, length = 1)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "create_time", nullable = true)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "publish_time", nullable = true)
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	
	@Column(name = "publish_depts", nullable = true)
	public String getPublishDepts() {
		return publishDepts;
	}
	public void setPublishDepts(String publishDepts) {
		this.publishDepts = publishDepts;
	}
	
	@Column(name = "regain_time", nullable = true)
	public Date getRegainTime() {
		return regainTime;
	}
	public void setRegainTime(Date regainTime) {
		this.regainTime = regainTime;
	}
	
	@Column(name = "question_creater", nullable = true)
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Column(name = "question_declare", nullable = true)
	public String getDeclare() {
		return declare;
	}
	public void setDeclare(String declare) {
		this.declare = declare;
	}
	
	@Column(name = "start_date", nullable = true)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name = "end_date", nullable = true)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
