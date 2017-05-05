package com.zzc.demo.crud.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bbs_topic")
public class BBStopicEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2662794703081049567L;

	@Id
	private String id;     //主键
	  
	private String title;  //标题
	
	private String content;
	
	@Column(name="publish_datetime")
	private String publishDatetime;   //发布时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishDatetime() {
		return publishDatetime;
	}

	public void setPublishDatetime(String publishDatetime) {
		this.publishDatetime = publishDatetime;
	}
}
