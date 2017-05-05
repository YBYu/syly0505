package com.zzc.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.entity.HotelmanageSta;
import com.zzc.web.htoal.entity.TouristOffice;
import com.zzc.web.sylyUtils.AutoAddUser;
import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.system.pojo.base.TSUser;

public class Testurl {
	
	public static void main(String[] args) {
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/getcheckInfo";
		try {
				HttpURLConnection connection = getConnection(url);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				// The URL-encoded contend
				// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
				String content = null;
				content = "code="
						+ URLEncoder.encode("4630136", "UTF-8");
				content += "&dates=" + URLEncoder.encode("1", "UTF-8");
				// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
				out.writeBytes(content);
				out.flush();
				out.close();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream(),
								"UTF-8"));
				reader.readLine();
				String line = reader.readLine()
						.replace("<string xmlns=\"http://tempuri.org/\">", "")
						.replace("</string>", "");

				// 数据转为json对象
				JSONObject jsonObject = JSON.parseObject(line);
				String status = jsonObject.getString("status");
				// 是否成功调用
				if("0".equals(status)) System.out.println("null");
				
				JSONObject data = jsonObject.getJSONObject("data");
				
				// 关闭连接
				connection.disconnect();
				reader.close();
				System.out.println(data.getString("sheng"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getUserInfo1() {

		System.out
				.println("-----------------------------getUserInfo开始同步-----------------------------");
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/getUserInfo";
		HttpURLConnection connection = getConnection(url);
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(connection.getOutputStream());
			// The URL-encoded contend
			// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
			String content = null;
			content = "start_page=" + URLEncoder.encode("1", "UTF-8");
			content += "&page_nums=" + URLEncoder.encode("100", "UTF-8");
			// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
			out.writeBytes(content);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			reader.readLine();
			String line = reader.readLine()
					.replace("<string xmlns=\"http://tempuri.org/\">", "")
					.replace("</string>", "");

			System.out.println(line);
			// 数据转为json对象
			JSONObject jsonObject = JSON.parseObject(line);
			String data = jsonObject.get("data").toString();
			JSONArray list = JSON.parseArray(data);

			// 格式化时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:dd:mm");
			List<Hotelmanage> hotelList = new ArrayList<>();
			List<HotelmanageSta> hotelStaList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = JSON.parseObject(list.get(i).toString());
				Hotelmanage hotelManage = new Hotelmanage();
				HotelmanageSta hotelManageSta = new HotelmanageSta();
				// 信息来源 1,国家系统  2,省系统
				hotelManage.setSourceType("1");
				hotelManageSta.setSourceType("1");
				// Code Var50 不允许 标牌编码
				hotelManage.setCode(obj.getString("UserName"));
				hotelManageSta.setCode(obj.getString("UserName"));
				// TableDate DateTime 不允许 报表时间（页面左上角）
				try {
					hotelManage.setW_time(sdf.parse(obj.getString("TableDate")
							.replace("T", " ")));
					hotelManageSta.setW_time(sdf.parse(obj.getString(
							"TableDate").replace("T", " ")));
				} catch (ParseException e) {
					e.printStackTrace();
					hotelManage.setW_time(null);
					hotelManageSta.setW_time(null);
				}
				hotelManage.setVerifyTime(new Date());
				hotelManageSta.setVerifyTime(new Date());
				// UnitName varchar(100) 不允许 单位名称
				hotelManage.setUnitname(obj.getString("UnitName"));
				hotelManageSta.setUnitname(obj.getString("UnitName"));
				// Delegate Var50 不允许 法定代表人
				hotelManage.setLegalPerson(obj.getString("Delegate"));
				hotelManageSta.setLegalPerson(obj.getString("Delegate"));
				// UserName Var50 不允许 组织机构代码
				hotelManage.setOrganizationNum(obj.getString("Code"));
				hotelManageSta.setOrganizationNum(obj.getString("Code"));
				// Password Var50 允许 密码
				hotelManage.setPassword(obj.getString("Password"));
				hotelManageSta.setPassword(obj.getString("Password"));
				// FillDate DateTime 不允许 报出日期
				hotelManage.setFillDate(obj.getString("FillDate").substring(0,
						10));
				hotelManageSta.setFillDate(obj.getString("FillDate").substring(
						0, 10));
				// UnitMaster Var50 允许 单位负责人
				hotelManage.setManager(obj.getString("UnitMaster"));
				hotelManageSta.setManager(obj.getString("UnitMaster"));
				// Filler Var50 允许 填表人
				hotelManage.setWriter(obj.getString("Filler"));
				hotelManageSta.setWriter(obj.getString("Filler"));
				// FillerTel Var50 允许 填表人电话
				hotelManage.setFillerTel(obj.getString("FillerTel"));
				hotelManageSta.setFillerTel(obj.getString("FillerTel"));
				// Adessr varchar(100) 不允许 单位所在地
				hotelManage.setAddress(obj.getString("Adessr"));
				hotelManageSta.setAddress(obj.getString("Adessr"));
				// Province Var50 允许 单位省
				// City Var50 允许 单位市
				// Postcode Var50 不允许 邮政编码
				hotelManage.setZipcode(obj.getString("Postcode"));
				hotelManageSta.setZipcode(obj.getString("Postcode"));
				// RegionalismCode Var50 允许 行政区划码
				hotelManage
						.setRegionalismCode(obj.getString("RegionalismCode"));
				hotelManageSta.setRegionalismCode(obj
						.getString("RegionalismCode"));
				// Tel Var50 不允许 电话号码
				hotelManage.setTelephone(obj.getString("Tel"));
				hotelManageSta.setTelephone(obj.getString("Tel"));
				// TelNo Var50 允许 电话分机号码
				hotelManage.setTelNo(obj.getString("TelNo"));
				hotelManageSta.setMobile(obj.getString("Mobile"));
				// Mobile Var50 允许 移动电话
				hotelManage.setMobile(obj.getString("Mobile"));
				hotelManageSta.setMobile(obj.getString("Mobile"));
				// Fax Var50 不允许 传真号码
				hotelManage.setFax(obj.getString("Fax"));
				hotelManageSta.setFax(obj.getString("Fax"));
				// FaxNo Var50 允许 传真分机号码
				hotelManage.setFaxNo(obj.getString("FaxNo"));
				hotelManageSta.setFaxNo(obj.getString("FaxNo"));
				// WebSite varchar(200) 允许 互联网网址
				hotelManage.setWeburl(obj.getString("WebSite"));
				hotelManageSta.setWeburl(obj.getString("WebSite"));
				// Email varchar(200) 允许 电子邮件信箱
				hotelManage.setEmail(obj.getString("Email"));
				hotelManageSta.setEmail(obj.getString("Email"));
				// RegistrationType Var50 不允许 旅行社类别
				hotelManage.setRegistrationType(obj
						.getString("RegistrationType"));
				hotelManageSta.setRegistrationType(obj
						.getString("RegistrationType"));
				// UnitType Var50 不允许 单位类别
				hotelManage.setUnitType(obj.getString("UnitType"));
				hotelManageSta.setUnitType(obj.getString("UnitType"));
				// AccommodationStar Var50 不允许 住宿设施星级
				try {
					hotelManage.setHousegrade(String.valueOf((Integer.parseInt(obj
							.getString("Code").substring(2, 3)) + 3)));
					hotelManageSta.setHousegrade(String.valueOf((Integer.parseInt(obj
							.getString("Code").substring(2, 3)) + 3)));
				} catch (Exception e) {
					hotelManage.setHousegrade("0");
					hotelManageSta.setHousegrade("0");
				}
				// TravelAgencyType Var50 不允许 企业登记注册类型
				hotelManage.setRegisterstyle(obj.getString("TravelAgencyType"));
				hotelManageSta.setRegisterstyle(obj
						.getString("TravelAgencyType"));
				// SceneSpotLevel Var50 不允许 旅游区（点）等级
				hotelManage.setSceneSpotLevel(obj.getString("SceneSpotLevel"));
				hotelManageSta.setSceneSpotLevel(obj
						.getString("SceneSpotLevel"));
				// IsCheck Var50 是否审核
				// IsDelete Var50 是否删除
				// Demo Var200 年份
				// Kuo Var50 用户填报添加随机数，证明已经填报过
				// Ip Var50 最后一次的ip
				// Sheng Var50 省审核
				hotelManage.setSheng(obj.getString("Sheng"));
				hotelManageSta.setSheng(obj.getString("Sheng"));
				// Shi Var50 市审核
				hotelManage.setShi(obj.getString("Shi"));
				hotelManageSta.setShi(obj.getString("Shi"));
				// Xian Var50 县审核
				hotelManage.setXian(obj.getString("Xian"));
				hotelManageSta.setXian(obj.getString("Xian"));
				hotelManage.setStatus("4");
				hotelManageSta.setStatus("4");
				hotelList.add(hotelManage);
				hotelStaList.add(hotelManageSta);
			}
			// 关闭连接
			connection.disconnect();
			reader.close();

			// 添加用户
			List<Hotelmanage> hotelNewList = new ArrayList<>();
			List<HotelmanageSta> hotelStaNewList = new ArrayList<>();
			for (int i = 0; i < hotelList.size(); i++) {
				Hotelmanage hotelManage = hotelList.get(i);
				HotelmanageSta hotelManageSta = hotelStaList.get(i);
				TSUser user = AutoAddUser.interfaceAdd(
						hotelManage.getOrganizationNum(),
						GlobalParams.XINGJIJIUDIAN, hotelManage.getManager());
				hotelManage.setId(user.getId());
				hotelManage.setHuserId(user.getId());
				hotelManageSta.setId(user.getId());
				hotelManageSta.setHuserId(user.getId());
				hotelNewList.add(hotelManage);
				hotelStaNewList.add(hotelManageSta);
			}
			System.out
					.println("-----------------------------getUserInfo结束同步-----------------------------");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static void getHotel_year_info() throws Exception {
		String validateURL="http://fdcs.100chengxin.com/hotel_interface.asmx/hotel_year_info";
		// Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(validateURL);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
      
        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        connection.setRequestMethod("POST");
       
        // Post 请求不能使用缓存
        connection.setUseCaches(false);
       
        connection.setInstanceFollowRedirects(true);
       
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
        // 进行编码
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.setRequestProperty("apikey", "b76a843c8e0b7ccc1f8c51302557ae75");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        // The URL-encoded contend
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        String content = "code=" + URLEncoder.encode("4640010", "UTF-8");
        content +="&year="+URLEncoder.encode("2015", "UTF-8");
     // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        out.writeBytes(content);

        out.flush();
        out.close(); 
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        reader.readLine();
        String line = reader.readLine().replace("<string xmlns=\"http://tempuri.org/\">", "").replace("</string>", "");
        System.out.println(line);
        
        // 关闭连接
        reader.close();
        connection.disconnect();
        
        JSONObject jsonObject = JSON.parseObject(line);
        String data = jsonObject.get("data").toString();
        JSONArray list = JSON.parseArray(data);
        for (int i = 0; i < list.size(); i++) {
        	JSONObject obj = JSON.parseObject(list.get(i).toString());
			System.out.println(obj.getString("UnitName"));
		}
	}
	
	public void getHotelSeaInfo() throws Exception {

		String validateURL="http://fdcs.100chengxin.com/hotel_interface.asmx/hotel_sea_info";
		// Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(validateURL);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
      
        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        connection.setRequestMethod("POST");
       
        // Post 请求不能使用缓存
        connection.setUseCaches(false);
       
        connection.setInstanceFollowRedirects(true);
       
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
        // 进行编码
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.setRequestProperty("apikey", "b76a843c8e0b7ccc1f8c51302557ae75");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        // The URL-encoded contend
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        String content = "Code=" + URLEncoder.encode("4640013", "UTF-8");
        content +="&year="+URLEncoder.encode("2016", "UTF-8");
        content +="&sea="+URLEncoder.encode("1", "UTF-8");
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        out.writeBytes(content);

        out.flush();
        out.close(); 
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        reader.readLine();
        String line = reader.readLine().replace("<string xmlns=\"http://tempuri.org/\">", "").replace("</string>", "");
        System.out.println(line);
        
        // 关闭连接
        reader.close();
        connection.disconnect();
        
        JSONObject jsonObject = JSON.parseObject(line);
        String data = jsonObject.get("data").toString();
        JSONArray list = JSON.parseArray(data);
        for (int i = 0; i < list.size(); i++) {
			JSONObject obj = JSON.parseObject(list.get(i).toString());
			System.out.println(obj.getString("Filler"));
        }
	}
	
	public  void getUserInfo() throws Exception {
		String validateURL="http://fdcs.100chengxin.com/hotel_interface.asmx/getUserInfo";
		// Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(validateURL);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
      
        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        connection.setRequestMethod("POST");
       
        // Post 请求不能使用缓存
        connection.setUseCaches(false);
       
        connection.setInstanceFollowRedirects(true);
       
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
        // 进行编码
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.setRequestProperty("apikey", "b76a843c8e0b7ccc1f8c51302557ae75");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        // The URL-encoded contend
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        String content = "start_page=" + URLEncoder.encode("1", "UTF-8");
        content +="&page_nums="+URLEncoder.encode("100", "UTF-8");;
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        out.writeBytes(content);

        out.flush();
        out.close(); 
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        reader.readLine();
        String line = reader.readLine().replace("<string xmlns=\"http://tempuri.org/\">", "").replace("</string>", "");
        System.out.println(line);
        
        // 关闭连接
        reader.close();
        connection.disconnect();
        
        JSONObject jsonObject = JSON.parseObject(line);
        String data = jsonObject.get("data").toString();
        JSONArray list = JSON.parseArray(data);
        for (int i = 0; i < list.size(); i++) {
			JSONObject obj = JSON.parseObject(list.get(i).toString());
			System.out.println(obj.getString("Filler"));
        }
        
        
	}
	
	/**
	 * 获取HTTP请求
	 * 
	 * @param url
	 * @return HttpURLConnection
	 */
	private static HttpURLConnection getConnection(String url) {
		// Post请求的url，与get不同的是不需要带参数
		URL postUrl;
		try {
			postUrl = new URL(url);
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
		// 打开连接
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) postUrl.openConnection();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
			return null;
		}

		// 设置是否向connection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		// 默认是 GET方式
		try {
			connection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}

		// Post 请求不能使用缓存
		connection.setUseCaches(false);

		connection.setInstanceFollowRedirects(true);

		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		// 进行编码
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.setRequestProperty("apikey",
				"b76a843c8e0b7ccc1f8c51302557ae75");
		// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		try {
			connection.connect();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
		return connection;
	}
	
}
