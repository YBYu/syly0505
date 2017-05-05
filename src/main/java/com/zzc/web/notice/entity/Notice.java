package com.zzc.web.notice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "t_notice")
public class Notice  implements java.io.Serializable{

	private static final long serialVersionUID = 1L;	
	private String id;
	private String title;
	private String content;
	private String createDate;
	private String source;
	private String range;
	private String announcer;
	private String status;
	private String publishDate;
	private String image;
	private String iffirst;
	private String gengxin;
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
	private String appContent;
	
	@Column(name = "appContent", nullable = true, length = 255)
	public String getAppContent() {
		return appContent;
	}

	public void setAppContent(String appContent) {
		this.appContent = appContent;
	}

	@Column(name = "iffirst", nullable = true, length = 255)
	public String getIffirst() {
		return iffirst;
	}


	public void setIffirst(String iffirst) {
		this.iffirst = iffirst;
	}

	@Column(name = "image", nullable = true, length = 255)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	@Column
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "update_date")
	public String getGengxin() {
		return gengxin;
	}

	public void setGengxin(String gengxin) {
		this.gengxin = gengxin;
	}


	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Column
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Column(name="noticerange")
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	@Column
	public String getAnnouncer() {
		return announcer;
	}
	public void setAnnouncer(String announcer) {
		this.announcer = announcer;
	}
	@Column
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
	@Column(name = "publish_date")
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
}
