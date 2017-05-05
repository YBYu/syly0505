package com.zzc.app.notice.entity;

import java.io.Serializable;

/**
 * Created by CC on 2017/1/12. 通知实体类,通知界面中使用
 */
public class InformEntity implements Serializable {

	/**
	 * 通知的名称
	 */
	private String informName;

	/**
	 * 通知的时间
	 */
	private String informTime;

	/**
	 * 通知的内容
	 */
	private String informContent;
	/**
	 * 通知范围
	 */
	private String range;
	/**
	 * 消息发布者
	 */
	private String releaser;
	/**
	 * 响应码
	 */
	private String responseCode;
	/**
	 * 通知ID
	 */
	private String id;

	/**
	 * 通知的来源
	 */
	private String informFrom;

	public InformEntity() {
	}

	public InformEntity(String informName, String informTime,
			String informContent, String range, String releaser,
			String responseCode, String id, String informFrom) {
		this.informName = informName;
		this.informTime = informTime;
		this.informContent = informContent;
		this.range = range;
		this.releaser = releaser;
		this.responseCode = responseCode;
		this.id = id;
		this.informFrom = informFrom;
	}

	public String getInformName() {
		return informName;
	}

	public void setInformName(String informName) {
		this.informName = informName;
	}

	public String getInformTime() {
		return informTime;
	}

	public void setInformTime(String informTime) {
		this.informTime = informTime;
	}

	public String getInformFrom() {
		return informFrom;
	}

	public void setInformFrom(String informFrom) {
		this.informFrom = informFrom;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInformContent() {
		return informContent;
	}

	public void setInformContent(String informContent) {
		this.informContent = informContent;
	}

	public String getReleaser() {
		return releaser;
	}

	public void setReleaser(String releaser) {
		this.releaser = releaser;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	@Override
	public String toString() {
		return "InformEntity{" + "range='" + range + '\'' + ", releaser='"
				+ releaser + '\'' + ", responseCode='" + responseCode + '\''
				+ ", id='" + id + '\'' + ", informContent='" + informContent
				+ '\'' + ", informName='" + informName + '\''
				+ ", informTime='" + informTime + '\'' + ", informFrom='"
				+ informFrom + '\'' + '}';
	}
}
