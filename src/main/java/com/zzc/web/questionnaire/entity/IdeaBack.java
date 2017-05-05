package com.zzc.web.questionnaire.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 意见反馈管理实体类
 * @author 冯勇齐
 * @date 2017-01-09 22:31:23
 */  

@Entity
@Table(name = "ds_feedback")
@SuppressWarnings("serial")
public class IdeaBack implements java.io.Serializable{

	
	
	
	private String id;
	private String userId;                     //发起反馈用户ID
	private String replyUserId;                //管理员ID(举报记录指被举报用户ID)
	private String content;                    //内容
	private String replyContent;               //回复内容
	private Date   createDate;                 //发起意见时间
	private Date   replyDate;                  //回复时间
	private String backType;                   //反馈类型：0-用户意见;1-举报
	private String feedbackUserId;             //关联用户id
	private String feedbackReplyUserId;        //关联管理员id
	private String status;                     //反馈状态
	private String ideaUserName;               //意见反馈人名称
	
	
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 50)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "user_id", nullable = true, length = 50)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "reply_user_id", nullable = true, length = 50)
	public String getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}
	@Column(name = "content", nullable = true, length = 300)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "reply_content", nullable = true, length = 300)
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	@Column(name = "create_date", nullable = true )
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "reply_date", nullable = true )
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	@Column(name = "back_type", nullable = true, length = 50)
	public String getBackType() {
		return backType;
	}
	public void setBackType(String backType) {
		this.backType = backType;
	}
	@Column(name = "feedback_user_id", nullable = true, length = 50)
	public String getFeedbackUserId() {
		return feedbackUserId;
	}
	public void setFeedbackUserId(String feedbackUserId) {
		this.feedbackUserId = feedbackUserId;
	}
	@Column(name = "feedback_reply_user_id", nullable = true, length = 50)
	public String getFeedbackReplyUserId() {
		return feedbackReplyUserId;
	}
	public void setFeedbackReplyUserId(String feedbackReplyUserId) {
		this.feedbackReplyUserId = feedbackReplyUserId;
	}
	@Column(name = "status", nullable = true, length = 50)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "idea_user_name", nullable = true, length =20)
	public String getIdeaUserName() {
		return ideaUserName;
	}
	public void setIdeaUserName(String ideaUserName) {
		this.ideaUserName = ideaUserName;
	}
	
	
	
	
	
	
	
	
}
