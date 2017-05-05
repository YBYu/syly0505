package com.zzc.web.sylyUtils;
/**
 * 
 *                  
 * @date: 2016年12月6日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: Status.java
 * @Version: 1.0
 * @About: 工具类用来管理月报 季报 年报等表的审核状态状态
 *
 */

public class Status {
	/**
	 * 未填报
	 */
	public final static String noSumbit="1";
	/**
	 * 未审核
	 */
	public final static String noAudit = "2";
	/**管理员已审核(审核不通过)
	 * */
	public final static String notAudit = "3";//
	/**
	 * 管理员已审核(审核通过)
	 */
	public final static String passAudit = "4";//
	
	/**
	 * 省已审核 审核结果:不通过
	 */
	public final static String notAuditByP = "5";
	/**
	 * 省已审核 审核结果:通过
	 */
	public final static String passAuditByP = "6";
	/**
	 * 1-高尔夫项目
	 */
	public final static String golf="1";
	/**
	 * 2-游艇项目
	 */
	public final static String boat = "2";
	/**3-空中飞行项目
	 * */
	public final static String flight = "3";//
	/**
	 * 4-机场项目
	 */
	public final static String airport = "4";//
	
	/**
	 * 5-动车项目
	 */
	public final static String motorcar = "5";
	

	/*
	 * 景区审核 
	 */
	/**
	 * 未分配
	 */
	public final static String undistributed="1";
	/**
	 * 已分配
	 */
	public final static String distributedt = "2";
	/**未填报
	 * */
	public final static String notEdit = "3";//
	/**
	 * 提交待审
	 */
	public final static String PendingSubmission= "4";//
	
	/**
	 * 退回修订
	 */
	public final static String notPass = "5";
	/**
	 * 区级审核
	 */
	public final static String areaAudit = "6";
	/**
	 * 市级审核
	 */
	public final static String cityAudit = "7";
	/**
	 * 省级审核
	 */
	public final static String provinceAudit = "8";
	/**
	 *国家审核
	 */
	public final static String countryAudit = "9";
	//省级待审核
	public final static String waitProAudit = "10";
	
	

}
