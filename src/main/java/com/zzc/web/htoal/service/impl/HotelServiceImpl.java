package com.zzc.web.htoal.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzc.core.common.service.impl.CommonServiceImpl;
import com.zzc.core.util.ResourceUtil;
import com.zzc.web.htoal.entity.Holiday;
import com.zzc.web.htoal.entity.HotelAnnual;
import com.zzc.web.htoal.entity.HotelEstimate;
import com.zzc.web.htoal.entity.HotelMonthly;
import com.zzc.web.htoal.entity.HotelQuarter;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.entity.StarredAnnualReport;
import com.zzc.web.htoal.entity.StarredHotel;
import com.zzc.web.htoal.entity.StarredQuarterlyReport;
import com.zzc.web.htoal.entity.TouristOffice;
import com.zzc.web.htoal.service.Hotelservice;
import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.touristoffices.entity.TouristOffices;
import com.zzc.web.travel.service.impl.TravelServiceImpl;
import com.zzc.webservice.ServiceInstance;
import com.zzc.webservice.provinceHotel.AddAuditAndMonthlyResponseAddAuditAndMonthlyResult;
import com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult;
import com.zzc.webservice.provinceHotel.AddHotelEstimateResponseAddHotelEstimateResult;
import com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult;
import com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult;
import com.zzc.webservice.provinceHotel.Audit;
import com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo;
import com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult;
import com.zzc.webservice.provinceHotel.HotelEstimateInMonth;
import com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult;
import com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult;
import com.zzc.webservice.provinceHotel.MoonFestivalInfo;
import com.zzc.webservice.provinceHotel.TheMonth;
import com.zzc.webservice.provinceHotel.TraveAgenciesBasic;
import com.zzc.webservice.provinceHotel.WebService_SanYaSoapProxy;

/**
 * 
 * 
 * @date: 2016年11月22日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: HotelServiceImpl.java
 * @Version: 1.0
 * @About: 酒店信息的服务层实现类
 * 
 */
@Service("hotelservice")
@Transactional
public class HotelServiceImpl extends CommonServiceImpl implements Hotelservice {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(HotelServiceImpl.class);

	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	// 判断用户名是否存在
	public TSUser checkUserExits(TSUser user) {
		// TODO Auto-generated method stub
		return this.commonDao.getUserByUserIdAndUserNameExits(user);
	}

	@Override
	public String getUserRole(TSUser user) {
		// TODO Auto-generated method stub

		return this.commonDao.getUserRole(user);
	}

	@Override
	public void pwdInit(TSUser user, String newPwd) {
		// TODO Auto-generated method stub
		this.commonDao.pwdInit(user, newPwd);
	}

	/**
	 * 
	 * 国家系统酒店基本信息新增
	 * 
	 * @author Fp
	 */
	public String addHotel(Hotelmanage hotelmanage, String type) {
		String tip = "200";
		try {
			// 暂停1s
			Thread.sleep(1000);
			HttpURLConnection connection = this
					.getConnection("http://fdcs.100chengxin.com/hotel_interface.asmx/hotel_edit");
			DataOutputStream out = null;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 组装hotel数据
				StarredHotel hotel = new StarredHotel();
				hotel.setUnitName(hotelmanage.getUnitname());
				hotel.setCode(hotelmanage.getCode());
				hotel.setTableDate(sdf.format(new Date()));
				hotel.setDelegate(hotelmanage.getLegalPerson());
				hotel.setUserName(hotelmanage.getOrganizationNum());
				hotel.setFillDate(sdf.format(new Date()));
				hotel.setUnitMaster(hotelmanage.getManager());
				hotel.setFiller(hotelmanage.getWriter());
				hotel.setFillerTel(hotelmanage.getFillerTel());
				hotel.setAdessr(hotelmanage.getAddress());
				hotel.setPostcode(hotelmanage.getZipcode());
				hotel.setMobile(hotelmanage.getMobile());
				hotel.setTel(hotelmanage.getMobile());
				hotel.setTelNo(hotelmanage.getTelNo());
				hotel.setFax(hotelmanage.getFax());
				hotel.setFaxNo(hotelmanage.getFaxNo());
				hotel.setWebSite(hotelmanage.getWeburl());
				hotel.setEmail(hotelmanage.getEmail());
				hotel.setTravelAgencyType(hotelmanage.getRegisterstyle());
				hotel.setAccommodationStar(String.valueOf(Integer
						.parseInt(hotelmanage.getHousegrade()) - 3));
				hotel.setCity("002008013");

				logger.info("调用国家系统新增酒店接口：" + hotelmanage.getId() + " type:"
						+ type);
				out = new DataOutputStream(connection.getOutputStream());
				// The URL-encoded contend
				// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
				String content = null;
				content = "json="
						+ URLEncoder.encode(JSON.toJSONString(hotel), "UTF-8");
				content += "&type=" + URLEncoder.encode(type, "UTF-8");
				content += "&city=" + URLEncoder.encode("002008013", "UTF-8");
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

				logger.info("调用国家系统新增酒店接口结果：" + line + " type:" + type + " id:"
						+ hotelmanage.getId());

				JSONObject object = JSON.parseObject(line);
				if (!"0".equals(object.getString("status")))
					tip = object.getString("message");
			} catch (Exception e) {
				logger.info("调用国家系统新增酒店接口异常：" + e.toString() + " type:" + type
						+ " id:" + hotelmanage.getId());
				e.printStackTrace();
				tip = "通信异常!";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tip;
	}

	/**
	 * 获取HTTP请求
	 * 
	 * @param url
	 * @return HttpURLConnection
	 */
	private HttpURLConnection getConnection(String url) {
		// Post请求的url，与get不同的是不需要带参数
		URL postUrl;
		try {
			postUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		// 打开连接
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) postUrl.openConnection();
		} catch (IOException e1) {
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
			e.printStackTrace();
			return null;
		}
		return connection;
	}

	/**
	 * 
	 * @param hotelmanages
	 * @return 省系统酒店基本信息新增
	 */
	public String addProvinceHotel(Hotelmanage hotelmanages) {
		String tip = "200";
		try {
			// 暂停1s
			Thread.sleep(1000);
			WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance
					.getHotelProvinceService();
			TraveAgenciesBasic traveAgenciesBasic = new TraveAgenciesBasic();
			// 1、Key必填
			// 2、TraveAgenciesBasic.CountyCode 行政区划代码
			String countyCode = "";
			// 460201 　　　市辖区
			// 460202 　　　海棠区
			// 460203 　　　吉阳区
			// 460204 　　　天涯区
			// 460205 　　　崖州区
			switch (hotelmanages.getCounty()) {
			case "0":
				countyCode = "460201";
				break;
			case "1":
				countyCode = "460205";
				break;
			case "2":
				countyCode = "460202";
				break;
			case "3":
				countyCode = "460204";
				break;
			case "4":
				countyCode = "460203";
				break;
			}
			traveAgenciesBasic.setCountyCode(countyCode);
			// 3、TraveAgenciesBasic.UserAccount 用户登录账号
			traveAgenciesBasic.setUserAccount(hotelmanages.getUsername());
			// 4、TraveAgenciesBasic.UserPassword 用户密码
			traveAgenciesBasic.setUserPassword2("syly1111");
			// 5、TraveAgenciesBasic.OrcanCode 组织机构代码
			traveAgenciesBasic.setOrcanCode(hotelmanages.getOrganizationNum());
			// 6、TraveAgenciesBasic.OrcanName 单位名称
			traveAgenciesBasic.setOrcanName(hotelmanages.getUnitname());
			// 7、TraveAgenciesBasic.UnitLeader 单位负责人
			traveAgenciesBasic.setUnitLeader(hotelmanages.getManager());
			// 8、TraveAgenciesBasic.PostalCode 邮政编码
			traveAgenciesBasic.setPostalCode(hotelmanages.getZipcode());
			// 9、TraveAgenciesBasic.TelephoneNumber 固定电话
			traveAgenciesBasic.setTelephoneNumber(hotelmanages.getMobile());
			// 10、TraveAgenciesBasic.RoomNumber 客房数
			traveAgenciesBasic
					.setRoomNumber(hotelmanages.getHousenum() == null ? 0
							: hotelmanages.getHousenum());
			// 11、TraveAgenciesBasic.BedNumber 床位数
			traveAgenciesBasic
					.setBedNumber(hotelmanages.getBednum() == null ? 0
							: hotelmanages.getBednum());
			// 12、TraveAgenciesBasic.OrcanRegistertime 工商注册登记时间
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(hotelmanages.getRegistertime());
			traveAgenciesBasic.setOrcanRegistertime(calendar);
			// 13、TraveAgenciesBasic.LegalRepresentative 法定代表人
			traveAgenciesBasic.setLegalRepresentative(hotelmanages
					.getLegalPerson());
			// 14、TraveAgenciesBasic.StreetName 街(村)、门牌号
			traveAgenciesBasic.setStreetName(hotelmanages.getAddress());
			// 15、TraveAgenciesBasic.Phototelephony 传真电话
			traveAgenciesBasic.setPhototelephony(hotelmanages.getFax());
			// 16、TraveAgenciesBasic.OrcanType 企业登记注册类型
			int orcanType = 190;
			if (hotelmanages.getRegisterstyle() != null) {
				switch (hotelmanages.getRegisterstyle()) {
				// <enterpriseType name="110">国有</enterpriseType>
				case "国有":
					orcanType = 110;
					break;
				// <enterpriseType name="120">集体</enterpriseType>
				case "集体":
					orcanType = 120;
					break;
				// <enterpriseType name="130">股份合作</enterpriseType>
				case "股份合作":
					orcanType = 130;
					break;
				// <enterpriseType name="141">国有联营</enterpriseType>
				case "国有联营":
					orcanType = 141;
					break;
				// <enterpriseType name="142">集体联营</enterpriseType>
				case "集体联营":
					orcanType = 142;
					break;
				// <enterpriseType name="143">国有与集体联营</enterpriseType>
				case "国有与集体联营":
					orcanType = 143;
					break;
				// <enterpriseType name="149">其它联营</enterpriseType>
				case "其它联营":
					orcanType = 149;
					break;
				// <enterpriseType name="151">国有独资公司</enterpriseType>
				case "国有独资公司":
					orcanType = 151;
					break;
				// <enterpriseType name="159">其它有限责任公司</enterpriseType>
				case "其它有限责任公司":
					orcanType = 159;
					break;
				// <enterpriseType name="160">股份有限公司</enterpriseType>
				case "股份有限公":
					orcanType = 160;
					break;
				// <enterpriseType name="171">私营独资</enterpriseType>
				case "私营独资":
					orcanType = 171;
					break;
				// <enterpriseType name="172">私营合伙</enterpriseType>
				case "私营合伙":
					orcanType = 172;
					break;
				// <enterpriseType name="173">私营有限责任公司</enterpriseType>
				case "私营有限责任公司":
					orcanType = 173;
					break;
				// <enterpriseType name="174">私营股份有限公司</enterpriseType>
				case "私营股份有限公司":
					orcanType = 174;
					break;
				// <enterpriseType name="190">其它</enterpriseType>
				case "其它":
					orcanType = 190;
					break;
				// <enterpriseType name="210">与港澳台商合资经营</enterpriseType>
				case "与港澳台商合资经营":
					orcanType = 210;
					break;
				// <enterpriseType name="220">与港澳台商合作经营</enterpriseType>
				case "与港澳台商合作经营":
					orcanType = 220;
					break;
				// <enterpriseType name="230">港澳台商独资</enterpriseType>
				case "港澳台商独资":
					orcanType = 230;
					break;
				// <enterpriseType name="240">港澳台商投资股份有限公司</enterpriseType>
				case "港澳台商投资股份有限公司":
					orcanType = 240;
					break;
				// <enterpriseType name="290">其它港澳台商投资</enterpriseType>
				case "其它港澳台商投资":
					orcanType = 290;
					break;
				// <enterpriseType name="310">中外合资经营</enterpriseType>
				case "中外合资经营":
					orcanType = 310;
					break;
				// <enterpriseType name="320">中外合作经营</enterpriseType>
				case "中外合作经营":
					orcanType = 320;
					break;
				// <enterpriseType name="330">外资企业</enterpriseType>
				case "外资企业":
					orcanType = 330;
					break;
				// <enterpriseType name="340">外商投资股份有限公司</enterpriseType>
				case "外商投资股份有限公司":
					orcanType = 340;
					break;
				// <enterpriseType name="390">其它外商投资</enterpriseType>
				case "其它外商投资":
					orcanType = 390;
					break;

				}
			}
			traveAgenciesBasic.setOrcanType(orcanType);
			// 17、TraveAgenciesBasic.StarHotel 饭店星级
			int grade = Integer
					.parseInt(hotelmanages.getHousegrade() == null ? "0"
							: hotelmanages.getHousegrade());
			if (grade > 3) {
				grade = grade - 3;
			} else {
				grade = 0;
			}
			traveAgenciesBasic.setStarHotel(grade);
			// 18、TraveAgenciesBasic.ShareHolding 控股情况
			traveAgenciesBasic.setShareHolding(Integer.parseInt(hotelmanages
					.getShareHolding() == null ? "6" : hotelmanages
					.getShareHolding()));
			// 19、TraveAgenciesBasic.OperateUser 填报人
			traveAgenciesBasic.setOperateUser(hotelmanages.getWriter());
			// 20、TraveAgenciesBasic.OperateTelephone 填报人电话
			traveAgenciesBasic.setOperateTelephone(hotelmanages.getTelephone());
			// 21、IsRatified审核状态
			traveAgenciesBasic.setIsRatified(2);
			// 时间字段不能为空
			traveAgenciesBasic.setOrcanRegistertime(Calendar.getInstance());
			traveAgenciesBasic.setRatifiedTime(Calendar.getInstance());
			traveAgenciesBasic.setCreateTime(Calendar.getInstance());
			traveAgenciesBasic.setUpdateTime(Calendar.getInstance());

			AddHotelResponseAddHotelResult rs = null;
			logger.info("调用省系统新增酒店接口：" + hotelmanages.getId());
			rs = hotelProvinceService.addHotel(
					GlobalParams.hotelProvinceCode, traveAgenciesBasic);
			logger.info("调用省系统新增酒店接口结果：" + rs.get_any()[0].toString() + " id:"
					+ hotelmanages.getId());
			Document doc = DocumentHelper.parseText(rs.get_any()[0].toString());
			Element root = doc.getRootElement();
			if (!"1".equals(root.element("DataStructure").element("ReturnCode")
					.getTextTrim()))
				tip = "接口服务失败!";
		} catch (Exception e) {
			logger.info("调用省系统新增酒店接口异常：" + e.toString() + " id:"
					+ hotelmanages.getId());
			e.printStackTrace();
			return null;
		}
		return tip;
	}

	/**
	 * 
	 * @param hotelmanages
	 * @return 省系统编辑酒店基本信息
	 */
	public String editProvinceHotel(Hotelmanage hotelmanages) {
		String tip = "200";
		try {
			// 暂停1s
			Thread.sleep(1000);
			WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance
					.getHotelProvinceService();
			TraveAgenciesBasic traveAgenciesBasic = new TraveAgenciesBasic();
			// 1、Key必填
			// 2、TraveAgenciesBasic.CountyCode 行政区划代码
			String countyCode = "";
			// 460201 　　　市辖区
			// 460202 　　　海棠区
			// 460203 　　　吉阳区
			// 460204 　　　天涯区
			// 460205 　　　崖州区
			if (hotelmanages.getCounty() != null) {
				switch (hotelmanages.getCounty()) {
				case "0":
					countyCode = "460201";
					break;
				case "1":
					countyCode = "460205";
					break;
				case "2":
					countyCode = "460202";
					break;
				case "3":
					countyCode = "460204";
					break;
				case "4":
					countyCode = "460203";
					break;
				}
			}
			traveAgenciesBasic.setCountyCode(countyCode);
			// 3、TraveAgenciesBasic.UserAccount 用户登录账号
			traveAgenciesBasic.setUserAccount(hotelmanages.getUsername());
			// 4、TraveAgenciesBasic.UserPassword 用户密码
			traveAgenciesBasic.setUserPassword2("syly+1111");
			// 5、TraveAgenciesBasic.OrcanCode 组织机构代码
			traveAgenciesBasic.setOrcanCode(hotelmanages.getOrganizationNum());
			// 6、TraveAgenciesBasic.OrcanName 单位名称
			traveAgenciesBasic.setOrcanName(hotelmanages.getUnitname());
			// 7、TraveAgenciesBasic.UnitLeader 单位负责人
			traveAgenciesBasic.setUnitLeader(hotelmanages.getManager());
			// 8、TraveAgenciesBasic.PostalCode 邮政编码
			traveAgenciesBasic.setPostalCode(hotelmanages.getZipcode());
			// 9、TraveAgenciesBasic.TelephoneNumber 固定电话
			traveAgenciesBasic.setTelephoneNumber(hotelmanages.getMobile());
			// 10、TraveAgenciesBasic.RoomNumber 客房数
			traveAgenciesBasic
					.setRoomNumber(hotelmanages.getHousenum() == null ? 0
							: hotelmanages.getHousenum());
			// 11、TraveAgenciesBasic.BedNumber 床位数
			traveAgenciesBasic
					.setBedNumber(hotelmanages.getBednum() == null ? 0
							: hotelmanages.getBednum());
			// 12、TraveAgenciesBasic.OrcanRegistertime 工商注册登记时间
			traveAgenciesBasic.setOrcanRegistertime(null);
			// 13、TraveAgenciesBasic.LegalRepresentative 法定代表人
			traveAgenciesBasic.setLegalRepresentative(hotelmanages
					.getLegalPerson());
			// 14、TraveAgenciesBasic.StreetName 街(村)、门牌号
			traveAgenciesBasic.setStreetName(hotelmanages.getAddress());
			// 15、TraveAgenciesBasic.Phototelephony 传真电话
			traveAgenciesBasic.setPhototelephony(hotelmanages.getFax());
			// 16、TraveAgenciesBasic.OrcanType 企业登记注册类型
			int orcanType = 190;
			if (hotelmanages.getRegisterstyle() != null) {
				switch (hotelmanages.getRegisterstyle()) {
				// <enterpriseType name="110">国有</enterpriseType>
				case "国有":
					orcanType = 110;
					break;
				// <enterpriseType name="120">集体</enterpriseType>
				case "集体":
					orcanType = 120;
					break;
				// <enterpriseType name="130">股份合作</enterpriseType>
				case "股份合作":
					orcanType = 130;
					break;
				// <enterpriseType name="141">国有联营</enterpriseType>
				case "国有联营":
					orcanType = 141;
					break;
				// <enterpriseType name="142">集体联营</enterpriseType>
				case "集体联营":
					orcanType = 142;
					break;
				// <enterpriseType name="143">国有与集体联营</enterpriseType>
				case "国有与集体联营":
					orcanType = 143;
					break;
				// <enterpriseType name="149">其它联营</enterpriseType>
				case "其它联营":
					orcanType = 149;
					break;
				// <enterpriseType name="151">国有独资公司</enterpriseType>
				case "国有独资公司":
					orcanType = 151;
					break;
				// <enterpriseType name="159">其它有限责任公司</enterpriseType>
				case "其它有限责任公司":
					orcanType = 159;
					break;
				// <enterpriseType name="160">股份有限公司</enterpriseType>
				case "股份有限公":
					orcanType = 160;
					break;
				// <enterpriseType name="171">私营独资</enterpriseType>
				case "私营独资":
					orcanType = 171;
					break;
				// <enterpriseType name="172">私营合伙</enterpriseType>
				case "私营合伙":
					orcanType = 172;
					break;
				// <enterpriseType name="173">私营有限责任公司</enterpriseType>
				case "私营有限责任公司":
					orcanType = 173;
					break;
				// <enterpriseType name="174">私营股份有限公司</enterpriseType>
				case "私营股份有限公司":
					orcanType = 174;
					break;
				// <enterpriseType name="190">其它</enterpriseType>
				case "其它":
					orcanType = 190;
					break;
				// <enterpriseType name="210">与港澳台商合资经营</enterpriseType>
				case "与港澳台商合资经营":
					orcanType = 210;
					break;
				// <enterpriseType name="220">与港澳台商合作经营</enterpriseType>
				case "与港澳台商合作经营":
					orcanType = 220;
					break;
				// <enterpriseType name="230">港澳台商独资</enterpriseType>
				case "港澳台商独资":
					orcanType = 230;
					break;
				// <enterpriseType name="240">港澳台商投资股份有限公司</enterpriseType>
				case "港澳台商投资股份有限公司":
					orcanType = 240;
					break;
				// <enterpriseType name="290">其它港澳台商投资</enterpriseType>
				case "其它港澳台商投资":
					orcanType = 290;
					break;
				// <enterpriseType name="310">中外合资经营</enterpriseType>
				case "中外合资经营":
					orcanType = 310;
					break;
				// <enterpriseType name="320">中外合作经营</enterpriseType>
				case "中外合作经营":
					orcanType = 320;
					break;
				// <enterpriseType name="330">外资企业</enterpriseType>
				case "外资企业":
					orcanType = 330;
					break;
				// <enterpriseType name="340">外商投资股份有限公司</enterpriseType>
				case "外商投资股份有限公司":
					orcanType = 340;
					break;
				// <enterpriseType name="390">其它外商投资</enterpriseType>
				case "其它外商投资":
					orcanType = 390;
					break;

				}
			}
			traveAgenciesBasic.setOrcanType(orcanType);
			// 17、TraveAgenciesBasic.StarHotel 饭店星级
			int grade = Integer
					.parseInt(hotelmanages.getHousegrade() == null ? "0"
							: hotelmanages.getHousegrade());
			if (grade > 3) {
				grade = grade - 3;
			} else {
				grade = 0;
			}
			traveAgenciesBasic.setStarHotel(grade);
			// 18、TraveAgenciesBasic.ShareHolding 控股情况,6为其他
			traveAgenciesBasic.setShareHolding(Integer.parseInt(hotelmanages
					.getShareHolding() == null ? "6" : hotelmanages
					.getShareHolding()));
			// 19、TraveAgenciesBasic.OperateUser 填报人
			traveAgenciesBasic.setOperateUser(hotelmanages.getWriter());
			// 20、TraveAgenciesBasic.OperateTelephone 填报人电话
			traveAgenciesBasic.setOperateTelephone(hotelmanages.getTelephone());
			// 21、IsRatified审核状态
			traveAgenciesBasic.setIsRatified(2);
			// 时间字段不能为空
			traveAgenciesBasic.setOrcanRegistertime(Calendar.getInstance());
			traveAgenciesBasic.setRatifiedTime(Calendar.getInstance());
			traveAgenciesBasic.setCreateTime(Calendar.getInstance());
			traveAgenciesBasic.setUpdateTime(Calendar.getInstance());

			logger.info("调用省系统系统编辑酒店接口：" + hotelmanages.getId());
			ModiflyHotelResponseModiflyHotelResult rs = hotelProvinceService
					.modiflyHotel(GlobalParams.hotelProvinceCode,
							hotelmanages.getUsername(), traveAgenciesBasic);
			logger.info("调用省系统系统编辑酒店接口结果：" + rs.get_any()[0].toString()
					+ " id:" + hotelmanages.getId());
			Document doc = DocumentHelper.parseText(rs.get_any()[0].toString());
			Element root = doc.getRootElement();
			if (!"1".equals(root.element("DataStructure").element("ReturnCode")
					.getTextTrim()))
				tip = "接口服务失败!";
		} catch (Exception e) {
			logger.info("调用省系统系统编辑酒店接口异常：" + e.toString() + " id:"
					+ hotelmanages.getId());
			e.printStackTrace();
			tip = "接口异常";
		}
		return tip;
	}

	/**
	 * 
	 * 根据用户名获取省系统酒店基本信息
	 * 
	 * @author Fp
	 */
	public String getProvinceHotelInfo(String username) {
		try {
			// 暂停1s
			Thread.sleep(1000);
			WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance
					.getHotelProvinceService();
			TraveAgenciesBasic traveAgenciesBasic = new com.zzc.webservice.provinceHotel.TraveAgenciesBasic();
			String org = "0001-01-01 00:00:00";
			SimpleDateFormat sdforg = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date orgDate = null;
			Calendar cal = Calendar.getInstance();
			try {
				orgDate = sdforg.parse(org);
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			cal.setTime(orgDate);
			traveAgenciesBasic.setOrcanRegistertime(cal);
			traveAgenciesBasic.setRatifiedTime(cal);
			traveAgenciesBasic.setCreateTime(cal);
			traveAgenciesBasic.setUpdateTime(cal);
			traveAgenciesBasic.setUserRole(1);
			traveAgenciesBasic.setFlag(1);
			traveAgenciesBasic.setUserAccount(username);
			com.zzc.webservice.provinceHotel.GetHotelResponseGetHotelResult rs = null;
			logger.info("调用获取省系统酒店基本信息接口：" + " username:" + username);
			rs = hotelProvinceService.getHotel(
					GlobalParams.hotelProvinceCode, traveAgenciesBasic);
			System.out.println(rs.get_any()[0].toString());
			Document doc = DocumentHelper.parseText(rs.get_any()[0].toString());
			Element root = doc.getRootElement();
			// 没有查询到数据
			if (root.element("DataSet") == null) {
				logger.info("调用获取省系统酒店基本信息接口异常：" + " username:" + username);
				return null;
			}
			// 遍历元素
			List<Element> nodes = root.element("DataSet").elements();
			if (nodes == null || nodes.size() == 0)
				return null;
			return nodes.get(0).element("IsRatified").getTextTrim();
		} catch (Exception e) {
			logger.info("调用获取省系统酒店基本信息接口异常：" + e.toString() + " username:"
					+ username);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 获取国家系统所有酒店的基本信息
	 * 
	 * @author Fp
	 */
	public List<Hotelmanage> getCountryHotelInfo() {
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/getUserInfo";
		List<Hotelmanage> hotelList = new ArrayList<>();
		try {
			for (int k = 1; k <= 20; k++) {
				HttpURLConnection connection = this.getConnection(url);
				logger.info("调用国家系统获取所有酒店基本信息接口");
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				// The URL-encoded contend
				// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
				String content = null;
				content = "start_page="
						+ URLEncoder.encode(String.valueOf(k), "UTF-8");
				content += "&page_nums=" + URLEncoder.encode("100", "UTF-8");
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
				String data = jsonObject.get("data").toString();
				JSONArray list = JSON.parseArray(data);

				for (int i = 0; i < list.size(); i++) {
					JSONObject obj = JSON.parseObject(list.get(i).toString());
					Hotelmanage hotelManage = new Hotelmanage();
					// Code Var50 不允许 标牌编码
					hotelManage.setCode(obj.getString("Code"));
					hotelManage.setCountryStatus(obj.getString("Sheng"));
					hotelList.add(hotelManage);
				}
				// 关闭连接
				connection.disconnect();
				reader.close();
			}
		} catch (Exception e) {
			logger.info("调用国家系统获取所有酒店基本信息接口异常：" + e.toString());
			e.printStackTrace();
		}
		return hotelList;
	}
	
	/**
	 * 国家系统审核意见
	 */
	public String getBasicInfoContent(String code){
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/getcheckInfo";
		try {
				HttpURLConnection connection = this.getConnection(url);
				logger.info("调用基本信息年报审核意见接口");
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				// The URL-encoded contend
				// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
				String content = null;
				content = "code="
						+ URLEncoder.encode(code, "UTF-8");
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
				logger.info("基本信息年报审核意见接口结果：" +line+"code:"+code);

				// 数据转为json对象
				JSONObject jsonObject = JSON.parseObject(line);
				String status = jsonObject.getString("status");
				// 是否成功调用
				if(!"0".equals(status)) return null;
				
				JSONObject data = jsonObject.getJSONObject("data");
				
				// 关闭连接
				connection.disconnect();
				reader.close();
				
				return data.getString("sheng");
		} catch (Exception e) {
			logger.info("基本信息年报审核意见接口异常：" + e.toString()+"code:"+code);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 新增酒店月报
	 * 
	 * @author Fp
	 */
	public String addAuditAndMonthly(HotelMonthly hotelMonthly) {
		String tip = "200";
		try {
			// 暂停1s
			Thread.sleep(1000);
			WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance
					.getHotelProvinceService();
			Audit audit = new Audit();
			// 组装月报数据
			List<TheMonth> listTheMonth = this.getListTheMonth(hotelMonthly);
			TheMonth[] theMonths = new TheMonth[listTheMonth.size()];
			for (int i = 0; i < listTheMonth.size(); i++) {
				theMonths[i] = listTheMonth.get(i);
			}
			// Audit 类;
			// 2、 audit.CreateUser 填表人，必填
			audit.setCreateUser(hotelMonthly.getFiller());
			// 3、 audit.Phone 联系电话，必填
			audit.setPhone(hotelMonthly.getFillerTel());
			// 4、 audit.RealityCount 实际出租间夜数，必填
			audit.setRealityCount(new BigDecimal(hotelMonthly
					.getActualRentNum() == null ? "0" : hotelMonthly
					.getActualRentNum()));
			// 5、 audit.MaxCount可供出租间夜数，必填
			audit.setMaxCount(new BigDecimal(
					hotelMonthly.getRentNum() == null ? "0" : hotelMonthly
							.getRentNum()));

			// 6、 audit.State审核状态，必填写，1或2
			audit.setState(2);
			try {
				audit.setYear(Integer.parseInt(hotelMonthly.getYear()));
				audit.setMonth(Integer.parseInt(hotelMonthly.getMonth()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			audit.setCreateTime(Calendar.getInstance());
			AddAuditAndMonthlyResponseAddAuditAndMonthlyResult rs = null;
			try {
				logger.info("调用新增酒店月报接口：" + hotelMonthly.getId());
				rs = hotelProvinceService.addAuditAndMonthly(
						GlobalParams.hotelProvinceCode, audit, theMonths,
						hotelMonthly.getHotelmanage().getUsername());
				logger.info("调用新增酒店月报接口结果：" + rs.get_any()[0].toString()
						+ " id:" + hotelMonthly.getId());
			} catch (RemoteException e) {
				logger.info("调用新增酒店月报接口异常：" + e.toString() + " id:"
						+ hotelMonthly.getId());
				e.printStackTrace();
				tip = "通信异常!";
			}
		} catch (Exception e) {
			logger.info("调用新增酒店月报接口异常：" + e.toString() + " id:"
					+ hotelMonthly.getId());
			e.printStackTrace();
			tip = "通信异常!";
		}
		return tip;
	}

	/**
	 * 
	 * 编辑酒店月报
	 * 
	 * @author Fp
	 */
	public String modiflyAuditAndMonthly(HotelMonthly hotelMonthly) {
		String tip = "200";
		try {
			// 暂停1s
			Thread.sleep(1000);
			WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance
					.getHotelProvinceService();
			Audit audit = new Audit();
			// 组装月报数据
			List<TheMonth> listTheMonth = this.getListTheMonth(hotelMonthly);
			TheMonth[] theMonths = new TheMonth[listTheMonth.size()];
			for (int i = 0; i < listTheMonth.size(); i++) {
				theMonths[i] = listTheMonth.get(i);
			}
			// Audit 类;
			// 2、 audit.CreateUser 填表人，必填
			audit.setCreateUser(hotelMonthly.getFiller());
			// 3、 audit.Phone 联系电话，必填
			audit.setPhone(hotelMonthly.getFillerTel());
			// 4、 audit.RealityCount 实际出租间夜数，必填
			audit.setRealityCount(new BigDecimal(hotelMonthly
					.getActualRentNum() == null ? "0" : hotelMonthly
					.getActualRentNum()));
			// 5、 audit.MaxCount可供出租间夜数，必填
			audit.setMaxCount(new BigDecimal(
					hotelMonthly.getRentNum() == null ? "0" : hotelMonthly
							.getRentNum()));

			// 6、 audit.State审核状态，必填写，1或2
			audit.setState(2);
			ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult rs = null;
			try {
				rs = hotelProvinceService.modiflyAuditAndMonthly(
						GlobalParams.hotelProvinceCode, audit, theMonths,
						hotelMonthly.getHotelmanage().getUsername());
			} catch (RemoteException e) {
				e.printStackTrace();
				tip = "通信异常!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			tip = "通信异常!";
		}
		return tip;
	}

	/**
	 * 组装月报数据
	 * 
	 * @param hotelMonthly
	 * @return
	 */
	public List<TheMonth> getListTheMonth(HotelMonthly hotelMonthly) {
		List<TheMonth> listTheMonth = new ArrayList<>();
		TheMonth theMonth = new TheMonth();
		// 总人数 01
		/*
		 * theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
		 * .getTotalMonthTime() == null ? "0" : hotelMonthly
		 * .getTotalMonthTime())); theMonth.setDayCount(Integer
		 * .parseInt(hotelMonthly.getTotalMonthDay() == null ? "0" :
		 * hotelMonthly.getTotalMonthDay()));
		 * theMonth.setSumPeopleCount(Integer.parseInt(hotelMonthly
		 * .getTotalMonthYearTime() == null ? "0" : hotelMonthly
		 * .getTotalMonthYearTime()));
		 * theMonth.setSumDayCount(Integer.parseInt(hotelMonthly
		 * .getTotalMonthYearDay() == null ? "0" : hotelMonthly
		 * .getTotalMonthYearDay()));
		 * theMonth.setCreateTime(Calendar.getInstance());
		 * listTheMonth.add(theMonth);
		 */
		// 一、国内过夜游客 02
		theMonth = new TheMonth();
		theMonth.setIndexId("7ea1a1e3-41d7-48f5-a42b-b13bdf1dcd8f");
		theMonth.setIndexCode("02");
		theMonth.setIndexName("一、国内过夜游客");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getInlandMonthTime() == null ? "0" : hotelMonthly
				.getInlandMonthTime()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getInlandMonthDay() == null ? "0"
						: hotelMonthly.getInlandMonthDay()));
		theMonth.setSumPeopleCount(Integer.parseInt(hotelMonthly
				.getInlandMonthYearTime() == null ? "0" : hotelMonthly
				.getInlandMonthYearTime()));
		theMonth.setSumDayCount(Integer.parseInt(hotelMonthly
				.getInlandMonthYearDay() == null ? "0" : hotelMonthly
				.getInlandMonthYearDay()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 二、入境过夜游客 03
		/*
		 * theMonth = new TheMonth();
		 * theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
		 * .getEntryMonthTime() == null ? "0" : hotelMonthly
		 * .getEntryMonthTime())); theMonth.setDayCount(Integer
		 * .parseInt(hotelMonthly.getEntryMonthDay() == null ? "0" :
		 * hotelMonthly.getEntryMonthDay()));
		 * theMonth.setSumPeopleCount(Integer.parseInt(hotelMonthly
		 * .getEntryMonthYearTime() == null ? "0" : hotelMonthly
		 * .getEntryMonthYearTime()));
		 * theMonth.setSumDayCount(Integer.parseInt(hotelMonthly
		 * .getEntryMonthYearDay() == null ? "0" : hotelMonthly
		 * .getEntryMonthYearDay()));
		 * theMonth.setCreateTime(Calendar.getInstance());
		 * listTheMonth.add(theMonth);
		 */
		// (一)香港同胞 04
		theMonth = new TheMonth();
		theMonth.setIndexId("6a63dd30-103b-428c-b163-8be0d7c4133e");
		theMonth.setIndexCode("04");
		theMonth.setIndexName("(一)香港同胞");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getHongkongMonthTime() == null ? "0" : hotelMonthly
				.getHongkongMonthTime()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getHongkongMonthDay() == null ? "0" : hotelMonthly
				.getHongkongMonthDay()));
		theMonth.setSumPeopleCount(Integer.parseInt(hotelMonthly
				.getHongkongMonthYearTime() == null ? "0" : hotelMonthly
				.getHongkongMonthYearTime()));
		theMonth.setSumDayCount(Integer.parseInt(hotelMonthly
				.getHongkongMonthYearDay() == null ? "0" : hotelMonthly
				.getHongkongMonthYearDay()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// (二)澳门同胞 05
		theMonth = new TheMonth();
		theMonth.setIndexId("b9282828-0d23-48dc-b953-76fa56eb7085");
		theMonth.setIndexCode("05");
		theMonth.setIndexName("(二)澳门同胞");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getMacauMonthTime() == null ? "0" : hotelMonthly
				.getMacauMonthTime()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getMacauMonthDay() == null ? "0"
						: hotelMonthly.getMacauMonthDay()));
		theMonth.setSumPeopleCount(Integer.parseInt(hotelMonthly
				.getMacauMonthYearTime() == null ? "0" : hotelMonthly
				.getMacauMonthYearTime()));
		theMonth.setSumDayCount(Integer.parseInt(hotelMonthly
				.getMacauMonthYearDay() == null ? "0" : hotelMonthly
				.getMacauMonthYearDay()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// (三)台湾同胞 06
		theMonth = new TheMonth();
		theMonth.setIndexId("e80d6ff9-dd1f-406e-9f40-cc5ceb477f77");
		theMonth.setIndexCode("06");
		theMonth.setIndexName("(三)台湾同胞");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getTaiwanMonthTime() == null ? "0" : hotelMonthly
				.getTaiwanMonthTime()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getTaiwanMonthDay() == null ? "0"
						: hotelMonthly.getTaiwanMonthDay()));
		theMonth.setSumPeopleCount(Integer.parseInt(hotelMonthly
				.getTaiwanMonthYearTime() == null ? "0" : hotelMonthly
				.getTaiwanMonthYearTime()));
		theMonth.setSumDayCount(Integer.parseInt(hotelMonthly
				.getTaiwanMonthYearDay() == null ? "0" : hotelMonthly
				.getTaiwanMonthYearDay()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// (四)外国人 07
		/*
		 * theMonth = new TheMonth();
		 * theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
		 * .getForeignMonthTime() == null ? "0" : hotelMonthly
		 * .getForeignMonthTime())); theMonth.setDayCount(Integer
		 * .parseInt(hotelMonthly.getForeignMonthDay() == null ? "0" :
		 * hotelMonthly.getForeignMonthDay()));
		 * theMonth.setSumPeopleCount(Integer.parseInt(hotelMonthly
		 * .getForeignMonthYearTime() == null ? "0" : hotelMonthly
		 * .getForeignMonthYearTime()));
		 * theMonth.setSumDayCount(Integer.parseInt(hotelMonthly
		 * .getForeignMonthYearDay() == null ? "0" : hotelMonthly
		 * .getForeignMonthYearDay()));
		 * theMonth.setCreateTime(Calendar.getInstance());
		 * listTheMonth.add(theMonth);
		 */
		// 1.亚洲小计 08
		/*
		 * theMonth = new TheMonth(); theMonth.setPeopleCount(Integer
		 * .parseInt(hotelMonthly.getAsianMonth() == null ? "0" :
		 * hotelMonthly.getAsianMonth())); theMonth.setDayCount(Integer
		 * .parseInt(hotelMonthly.getAsianMonthYear() == null ? "0" :
		 * hotelMonthly.getAsianMonthYear()));
		 * theMonth.setCreateTime(Calendar.getInstance());
		 * listTheMonth.add(theMonth);
		 */
		// 日本 09
		theMonth = new TheMonth();
		theMonth.setIndexId("ee5f1377-9220-42e2-9b7b-cf03c11f30a4");
		theMonth.setIndexCode("09");
		theMonth.setIndexName("日本");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getJapanMonth() == null ? "0"
						: hotelMonthly.getJapanMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getJapanMonthYear() == null ? "0"
						: hotelMonthly.getJapanMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 韩国 10
		theMonth = new TheMonth();
		theMonth.setIndexId("82f785b7-94f5-470f-a807-1d4fb4d53711");
		theMonth.setIndexCode("10");
		theMonth.setIndexName("韩国");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getKoreaMonth() == null ? "0"
						: hotelMonthly.getKoreaMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getKoreaMonthYear() == null ? "0"
						: hotelMonthly.getKoreaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 蒙古 11
		theMonth = new TheMonth();
		theMonth.setIndexId("70e00bcf-5847-4c3b-88f3-9bb27d65577b");
		theMonth.setIndexCode("11");
		theMonth.setIndexName("蒙古");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getMongoliaMonth() == null ? "0" : hotelMonthly
				.getMongoliaMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getMongoliaMonthYear() == null ? "0" : hotelMonthly
				.getMongoliaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 印度尼西亚 12
		theMonth = new TheMonth();
		theMonth.setIndexId("57202841-70f0-4d5a-bd9a-c23093c2b783");
		theMonth.setIndexCode("12");
		theMonth.setIndexName("印度尼西亚");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getIndonesiaMonth() == null ? "0" : hotelMonthly
				.getIndonesiaMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getIndonesiaMonthYear() == null ? "0" : hotelMonthly
				.getIndonesiaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 马来西亚 13
		theMonth = new TheMonth();
		theMonth.setIndexId("d6e58ccd-2231-420d-913b-28c4a48cdbd0");
		theMonth.setIndexCode("13");
		theMonth.setIndexName("马来西亚");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getMalaysiaMonth() == null ? "0" : hotelMonthly
				.getMalaysiaMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getMalaysiaMonthYear() == null ? "0" : hotelMonthly
				.getMalaysiaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 菲律宾 14
		theMonth = new TheMonth();
		theMonth.setIndexId("aca2319b-06b8-47a8-8056-61e06de7b935");
		theMonth.setIndexCode("14");
		theMonth.setIndexName("菲律宾");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getPhilippinesMonth() == null ? "0" : hotelMonthly
				.getPhilippinesMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getPhilippinesMonthYear() == null ? "0" : hotelMonthly
				.getPhilippinesMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 新加坡 15
		theMonth = new TheMonth();
		theMonth.setIndexId("8714ab2f-2e12-46b3-9b8b-7d10ebe00328");
		theMonth.setIndexCode("15");
		theMonth.setIndexName("新加坡");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getSingaporeMonth() == null ? "0" : hotelMonthly
				.getSingaporeMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getSingaporeMonthYear() == null ? "0" : hotelMonthly
				.getSingaporeMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 泰国 16
		theMonth = new TheMonth();
		theMonth.setIndexId("b3e8d95b-5efa-4496-8483-36abb5abd8ec");
		theMonth.setIndexCode("16");
		theMonth.setIndexName("泰国");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getThailandMonth() == null ? "0" : hotelMonthly
				.getThailandMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getThailandMonthYear() == null ? "0" : hotelMonthly
				.getThailandMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 印度 17
		theMonth = new TheMonth();
		theMonth.setIndexId("3d6d0150-661f-4b0d-997b-e229cbb30a75");
		theMonth.setIndexCode("17");
		theMonth.setIndexName("印度");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getIndiaMonth() == null ? "0"
						: hotelMonthly.getIndiaMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getIndiaMonthYear() == null ? "0"
						: hotelMonthly.getIndiaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 越南 18
		theMonth = new TheMonth();
		theMonth.setIndexId("2ee7650b-14eb-4fa8-bb2b-10875157793f");
		theMonth.setIndexCode("18");
		theMonth.setIndexName("越南");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getVietnamMonth() == null ? "0"
						: hotelMonthly.getVietnamMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getVietnamMonthYear() == null ? "0" : hotelMonthly
				.getVietnamMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 缅甸 19
		theMonth = new TheMonth();
		theMonth.setIndexId("79df2eb8-663b-4295-8892-b178c5900c3e");
		theMonth.setIndexCode("19");
		theMonth.setIndexName("缅甸");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getBurmaMonth() == null ? "0"
						: hotelMonthly.getBurmaMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getBurmaMonthYear() == null ? "0"
						: hotelMonthly.getBurmaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 朝鲜 20
		theMonth = new TheMonth();
		theMonth.setIndexId("aa7c9ca9-c770-4469-8648-dc0d278ad34f");
		theMonth.setIndexCode("20");
		theMonth.setIndexName("朝鲜");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getNorthkoreaMonth() == null ? "0" : hotelMonthly
				.getNorthkoreaMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getNorthkoreaMonthYear() == null ? "0" : hotelMonthly
				.getNorthkoreaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 巴基斯坦 21
		theMonth = new TheMonth();
		theMonth.setIndexId("92240bbf-2d0d-441b-8a4d-420aa912f2f1");
		theMonth.setIndexCode("21");
		theMonth.setIndexName("巴基斯坦");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getPakistanMonth() == null ? "0" : hotelMonthly
				.getPakistanMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getPakistanMonthYear() == null ? "0" : hotelMonthly
				.getPakistanMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 亚洲其它 22
		theMonth = new TheMonth();
		theMonth.setIndexId("c464a281-7128-46c3-a673-152711c29fa1");
		theMonth.setIndexCode("22");
		theMonth.setIndexName("亚洲其它");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getAisaOtherMonth() == null ? "0" : hotelMonthly
				.getAisaOtherMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getAisaOtherMonthYear() == null ? "0" : hotelMonthly
				.getAisaOtherMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 2.欧洲小计 23
		/*
		 * theMonth = new TheMonth(); theMonth.setPeopleCount(Integer
		 * .parseInt(hotelMonthly.getEuropeMonth() == null ? "0" :
		 * hotelMonthly.getEuropeMonth())); theMonth.setDayCount(Integer
		 * .parseInt(hotelMonthly.getEuropeMonthYear() == null ? "0" :
		 * hotelMonthly.getEuropeMonthYear()));
		 * theMonth.setCreateTime(Calendar.getInstance());
		 * listTheMonth.add(theMonth);
		 */
		// 英国 24
		theMonth = new TheMonth();
		theMonth.setIndexId("d80f8c80-08e0-424d-acfc-3932cd591243");
		theMonth.setIndexCode("24");
		theMonth.setIndexName("英国");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getEnglandMonth() == null ? "0"
						: hotelMonthly.getEnglandMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getEnglandMonthYear() == null ? "0" : hotelMonthly
				.getEnglandMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 法国 25
		theMonth = new TheMonth();
		theMonth.setIndexId("1296bbae-7c46-4062-806e-14cc83c32dc1");
		theMonth.setIndexCode("25");
		theMonth.setIndexName("法国");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getFranceMonth() == null ? "0"
						: hotelMonthly.getFranceMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getFranceMonthYear() == null ? "0"
						: hotelMonthly.getFranceMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 德国 26
		theMonth = new TheMonth();
		theMonth.setIndexId("e07ac7a2-2381-4664-8278-bed4fa257733");
		theMonth.setIndexCode("26");
		theMonth.setIndexName("德国");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getGermanyMonth() == null ? "0"
						: hotelMonthly.getGermanyMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getGermanyMonthYear() == null ? "0" : hotelMonthly
				.getGermanyMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 意大利 27
		theMonth = new TheMonth();
		theMonth.setIndexId("80e3452d-974e-4d2d-a7a1-54cfa757b7b2");
		theMonth.setIndexCode("27");
		theMonth.setIndexName("意大利");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getItalyMonth() == null ? "0"
						: hotelMonthly.getItalyMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getItalyMonthYear() == null ? "0"
						: hotelMonthly.getItalyMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 瑞士 28
		theMonth = new TheMonth();
		theMonth.setIndexId("b6ff1917-3f1b-467a-8bf6-110635952681");
		theMonth.setIndexCode("28");
		theMonth.setIndexName("瑞士");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getSwitzerlandMonth() == null ? "0" : hotelMonthly
				.getSwitzerlandMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getSwitzerlandMonthYear() == null ? "0" : hotelMonthly
				.getSwitzerlandMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 瑞典 29
		theMonth = new TheMonth();
		theMonth.setIndexId("421d6638-f6ac-46c9-89db-472f02d8869f");
		theMonth.setIndexCode("29");
		theMonth.setIndexName("瑞典");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getSwedenMonth() == null ? "0"
						: hotelMonthly.getSwedenMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getSwedenMonthYear() == null ? "0"
						: hotelMonthly.getSwedenMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 俄罗斯 30
		theMonth = new TheMonth();
		theMonth.setIndexId("f24f9676-b1c4-456c-87a4-10d04ffd98be");
		theMonth.setIndexCode("30");
		theMonth.setIndexName("俄罗斯");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getRussiaMonth() == null ? "0"
						: hotelMonthly.getRussiaMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getRussiaMonthYear() == null ? "0"
						: hotelMonthly.getRussiaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 西班牙 31
		theMonth = new TheMonth();
		theMonth.setIndexId("3cf97bbc-50ab-40d1-a1dc-24107c7e0899");
		theMonth.setIndexCode("31");
		theMonth.setIndexName("西班牙");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getSpainMonth() == null ? "0"
						: hotelMonthly.getSpainMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getSpainMonthYear() == null ? "0"
						: hotelMonthly.getSpainMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 欧洲其它 32
		theMonth = new TheMonth();
		theMonth.setIndexId("0018c6cc-9f81-4166-8508-dd3edf1b93c4");
		theMonth.setIndexCode("32");
		theMonth.setIndexName("欧洲其它");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getEuropeOtherMonth() == null ? "0" : hotelMonthly
				.getEuropeOtherMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getEuropeOtherMonthYear() == null ? "0" : hotelMonthly
				.getEuropeOtherMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 3.美洲小计 33
		/*
		 * theMonth = new TheMonth(); theMonth.setPeopleCount(Integer
		 * .parseInt(hotelMonthly.getAmericaMonth() == null ? "0" :
		 * hotelMonthly.getAmericaMonth()));
		 * theMonth.setDayCount(Integer.parseInt(hotelMonthly
		 * .getAmericaMonthYear() == null ? "0" : hotelMonthly
		 * .getAmericaMonthYear()));
		 * theMonth.setCreateTime(Calendar.getInstance());
		 * listTheMonth.add(theMonth);
		 */
		// 美国 34
		theMonth = new TheMonth();
		theMonth.setIndexId("7e3ad4a5-65cc-4db1-bda1-c9b5a4675022");
		theMonth.setIndexCode("34");
		theMonth.setIndexName("美国");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getUsaMonth() == null ? "0"
						: hotelMonthly.getUsaMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getUsaMonthYear() == null ? "0"
						: hotelMonthly.getUsaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 加拿大 35
		theMonth = new TheMonth();
		theMonth.setIndexId("f1e98fd8-187f-4dec-ae5c-d6c60c8b7887");
		theMonth.setIndexCode("35");
		theMonth.setIndexName("加拿大");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getCanadaMonth() == null ? "0"
						: hotelMonthly.getCanadaMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getCanadaMonthYear() == null ? "0"
						: hotelMonthly.getCanadaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 美洲其它 36
		theMonth = new TheMonth();
		theMonth.setIndexId("02391031-1bce-4c01-8378-43ef27893fac");
		theMonth.setIndexCode("36");
		theMonth.setIndexName("美洲其它");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getAmericaOtherMonth() == null ? "0" : hotelMonthly
				.getAmericaOtherMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getAmericaOtherMonthYear() == null ? "0" : hotelMonthly
				.getOceaniaMonth()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 4.大洋洲小计 37
		/*
		 * theMonth = new TheMonth(); theMonth.setPeopleCount(Integer
		 * .parseInt(hotelMonthly.getOceaniaMonth() == null ? "0" :
		 * hotelMonthly.getOceaniaMonth()));
		 * theMonth.setDayCount(Integer.parseInt(hotelMonthly
		 * .getOceaniaMonthYear() == null ? "0" : hotelMonthly
		 * .getOceaniaMonthYear()));
		 * theMonth.setCreateTime(Calendar.getInstance());
		 * listTheMonth.add(theMonth);
		 */
		// 澳大利亚 38
		theMonth = new TheMonth();
		theMonth.setIndexId("84e6e3d5-e99f-44b0-b579-22c39cb51822");
		theMonth.setIndexCode("38");
		theMonth.setIndexName("澳大利亚");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getAustraliaMonth() == null ? "0" : hotelMonthly
				.getAustraliaMonthYear()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getAustraliaMonthYear() == null ? "0" : hotelMonthly
				.getAustraliaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 新西兰 39
		theMonth = new TheMonth();
		theMonth.setIndexId("91b2dda8-f822-46c1-ac60-62867e45450b");
		theMonth.setIndexCode("39");
		theMonth.setIndexName("新西兰");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getNzMonth() == null ? "0"
						: hotelMonthly.getNzMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getNzMonthYear() == null ? "0"
						: hotelMonthly.getNzMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 大洋洲其它 40
		theMonth = new TheMonth();
		theMonth.setIndexId("fed8ed35-73be-4b9f-ac81-d4b397a21ddf");
		theMonth.setIndexCode("40");
		theMonth.setIndexName("大洋洲其它");
		theMonth.setPeopleCount(Integer.parseInt(hotelMonthly
				.getOceaniaOtherMonth() == null ? "0" : hotelMonthly
				.getOceaniaOtherMonth()));
		theMonth.setDayCount(Integer.parseInt(hotelMonthly
				.getOceaniaOtherMonthYear() == null ? "0" : hotelMonthly
				.getOceaniaOtherMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 5.非洲小计 41
		theMonth = new TheMonth();
		theMonth.setIndexId("2c966471-0740-4057-ae1d-3aaf2890059a");
		theMonth.setIndexCode("41");
		theMonth.setIndexName("5.非洲小计");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getAfricaMonth() == null ? "0"
						: hotelMonthly.getAfricaMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getAfricaMonthYear() == null ? "0"
						: hotelMonthly.getAfricaMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);
		// 6.其它小计 42
		theMonth = new TheMonth();
		theMonth.setIndexId("ccad8be9-94d6-4bbf-bba6-a1ebf3b1087b");
		theMonth.setIndexCode("42");
		theMonth.setIndexName("6.其它小计");
		theMonth.setPeopleCount(Integer
				.parseInt(hotelMonthly.getOtherMonth() == null ? "0"
						: hotelMonthly.getOtherMonth()));
		theMonth.setDayCount(Integer
				.parseInt(hotelMonthly.getOtherMonthYear() == null ? "0"
						: hotelMonthly.getOtherMonthYear()));
		theMonth.setCreateTime(Calendar.getInstance());
		listTheMonth.add(theMonth);

		return listTheMonth;
	}

	public String getProvinceMonthlyInfo(HotelMonthly hotelMonthly) {
		try {
			// 暂停1s
			Thread.sleep(1000);
			Hotelmanage hotelmanage = hotelMonthly.getHotelmanage();
			Audit audit = new Audit();
			audit.setYear(Integer.parseInt(hotelMonthly.getYear()));
			audit.setMonth(Integer.parseInt(hotelMonthly.getMonth()));
			WebService_SanYaSoapProxy hotelService = new WebService_SanYaSoapProxy();
			GetMonthlyResponseGetMonthlyResult rs = null;
			logger.info("调用查询酒店月报接口：" + hotelMonthly.getId());
			rs = hotelService.getMonthly(GlobalParams.hotelProvinceCode,
					audit, hotelmanage.getUsername());
			logger.info("调用查询酒店月报接口结果：" + rs.get_any()[0].toString() + " id:"
					+ hotelMonthly.getId());
			Document doc = DocumentHelper.parseText(rs.get_any()[0].toString());
			Element root = doc.getRootElement();
			List<Element> nodes = root.element("DataSet").elements();
			return nodes.get(0).element("State").getTextTrim();
		} catch (Exception e) {
			logger.info("调用查询酒店月报接口异常：" + e.toString() + " id:"
					+ hotelMonthly.getId());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 新增酒店季报
	 * 
	 * @author Fp
	 */
	public String addQuarterReport(HotelQuarter hotelQuarter) {
		String tip = "200";
		HttpURLConnection connection = this
				.getConnection("http://fdcs.100chengxin.com/hotel_interface.asmx/hotel_sea_add");
		DataOutputStream out = null;
		String type = "";
		if ("1".equals(hotelQuarter.getRevocated()))
			type = "edit";
		else
			type = "add";
		try {
			// 暂停1s
			Thread.sleep(1000);

			// 获取酒店信息
			Hotelmanage hotel = systemService.get(Hotelmanage.class,
					hotelQuarter.getHotelQid());
			// 组装季报数据
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			StarredQuarterlyReport report = new StarredQuarterlyReport();
			report.setCode(hotel.getCode());
			report.setUnitName(hotel.getUnitname());
			report.setFiller(hotelQuarter.getWriter());
			report.setFillerTerl(hotelQuarter.getMobile());
			try {
				report.setTanbleDate(sdf.format(hotelQuarter.getWriterDate()));
			} catch (Exception e) {
				report.setTanbleDate(sdf.format(new Date()));
			}
			report.setUnitMaster(hotel.getManager());
			report.setOperationCost(String.valueOf(hotelQuarter
					.getTotalIncome()));
			report.setRoomRevenue(String.valueOf(hotelQuarter.getRoomIncome()));
			report.setCateringIncome(String.valueOf(hotelQuarter
					.getCanteenIncome()));
			report.setOtherIncome(String.valueOf(hotelQuarter.getOtherIncome()));
			report.setRoom(String.valueOf(hotelQuarter.getRoomnum()));
			report.setBed(String.valueOf(hotelQuarter.getBednum()));
			report.setActualRent(String.valueOf(hotelQuarter.getRealNights()));
			report.setForHire(String.valueOf(hotelQuarter.getCanNights()));
			report.setNian(hotelQuarter.getYear());
			report.setJi(String.valueOf(hotelQuarter.getQuarter()));
			report.setCity("002008013");

			logger.info("调用新增酒店年报接口：" + hotelQuarter.getId() + " type:" + type);
			out = new DataOutputStream(connection.getOutputStream());
			// The URL-encoded contend
			// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
			String content = null;
			content = "json="
					+ URLEncoder.encode(JSON.toJSONString(report), "UTF-8");
			content += "&type=" + URLEncoder.encode(type, "UTF-8");
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
			logger.info("调用新增酒店年报接口结果：" + line + "id: " + hotelQuarter.getId()
					+ " type:" + type);
			JSONObject object = JSON.parseObject(line);
			if (!"0".equals(object.getString("status")))
				tip = object.getString("message");
		} catch (Exception e) {
			logger.info("调用新增酒店年报接口异常：" + "id: " + hotelQuarter.getId()
					+ " type:" + type);
			e.printStackTrace();
			tip = "通信异常!";
		}
		return tip;
	}

	/**
	 * 
	 * 获取酒店季报信息
	 * 
	 * @author Fp
	 */
	public String getQuarterReport(HotelQuarter hotelQuarter) {
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/hotel_sea_info";
		try {
			// 暂停1s
			Thread.sleep(1000);
			Hotelmanage hotelmanage = systemService.get(Hotelmanage.class,
					hotelQuarter.getHotelQid());
			String year = hotelQuarter.getYear();
			String quarter = String.valueOf(hotelQuarter.getQuarter());

			logger.info("调用获取酒店季报信息接口：" + "id: " + hotelQuarter.getId());

			HttpURLConnection connection = this.getConnection(url);
			DataOutputStream out = null;
			out = new DataOutputStream(connection.getOutputStream());
			// The URL-encoded contend
			// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
			String content = "Code="
					+ URLEncoder.encode(hotelmanage.getCode(), "UTF-8");
			content += "&year=" + URLEncoder.encode(year, "UTF-8");
			content += "&sea="
					+ URLEncoder.encode(String.valueOf(quarter), "UTF-8");
			// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
			out.writeBytes(content);
			out.flush();
			out.close();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "UTF-8"));
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("调用获取酒店季报信息接口异常：" + "id: " + hotelQuarter.getId());
				return null;
			}
			reader.readLine();
			String line = reader.readLine()
					.replace("<string xmlns=\"http://tempuri.org/\">", "")
					.replace("</string>", "");

			System.out.println(line);
			logger.info("调用获取酒店季报信息接口结果：" + line + " id:"
					+ hotelQuarter.getId());

			// 数据转为json对象
			JSONObject jsonObject = JSON.parseObject(line);
			String data = jsonObject.get("data").toString();
			JSONArray list = JSON.parseArray(data);
			// 无数据
			if (list.size() == 0)
				return null;

			JSONObject obj = JSON.parseObject(list.get(0).toString());
			String status = obj.getString("Sheng");
			// 关闭连接
			connection.disconnect();
			reader.close();
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("调用获取酒店季报信息接口异常：" + "id: " + hotelQuarter.getId());
			return null;
		}
	}
	
	/**
	 * 季报国家系统审核意见
	 */
	public String getQuarterReportContent(String code, String quarter, String year){
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/getcheckInfo";
		try {
				HttpURLConnection connection = this.getConnection(url);
				logger.info("调用季报国家系统审核意见接口");
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				// The URL-encoded contend
				// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
				String content = null;
				content = "code="
						+ URLEncoder.encode(code, "UTF-8");
				content += "&dates=" + URLEncoder.encode(quarter, "UTF-8");
				content += "&dates=" + URLEncoder.encode(year, "UTF-8");
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
				logger.info("季报国家系统审核意见接口结果：" +line+"code:"+code);

				// 数据转为json对象
				JSONObject jsonObject = JSON.parseObject(line);
				String status = jsonObject.getString("status");
				// 是否成功调用
				if(!"0".equals(status)) return null;
				
				JSONObject data = jsonObject.getJSONObject("data");
				
				// 关闭连接
				connection.disconnect();
				reader.close();
				
				return data.getString("sheng");
		} catch (Exception e) {
			logger.info("季报国家系统审核意见异常：" + e.toString()+"code:"+code);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 添加酒店年报
	 */
	public String addAnnualReport(HotelAnnual hotelAnnual) {
		String tip = "200";
		HttpURLConnection connection = this
				.getConnection("http://fdcs.100chengxin.com/hotel_interface.asmx/hotel_year_add");
		DataOutputStream out = null;
		// 依据撤回记录判断是新增还是编辑
		String type = "";
		if ("1".equals(hotelAnnual.getRevocated()))
			type = "edit";
		else
			type = "add";
		try {
			// 暂停1s
			Thread.sleep(1000);

			// 获取酒店对象
			Hotelmanage hotel = systemService.get(Hotelmanage.class,
					hotelAnnual.getHotelAid());
			// 组装年报数据
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			StarredAnnualReport report = new StarredAnnualReport();
			report.setCode(hotel.getCode());
			report.setUnitName(hotel.getUnitname());
			try {
				report.setTableDate(sdf.format(hotelAnnual.getReportTime()));
			} catch (Exception e) {
				report.setTableDate(sdf.format(new Date()));
			}
			report.setUnitName(hotel.getUnitname());
			try {
				report.setFillDate(sdf.format(hotelAnnual.getReportTime()));
			} catch (Exception e) {
				report.setFillDate(sdf.format(new Date()));
			}
			report.setFiller(hotelAnnual.getReportPerson());
			report.setFillerTel(hotelAnnual.getFillerTel());
			report.setUnitMaster(hotel.getManager());
			report.setCurrentAssetsTotal(String.valueOf(hotelAnnual
					.getStreamTotal()));
			report.setLongTermInvest(String.valueOf(hotelAnnual.getLongInvest()));
			report.setFixAssetsTotal(String.valueOf(hotelAnnual
					.getFixedAssets()));
			report.setFixAssetsInitialPrice(String.valueOf(hotelAnnual
					.getFixedPrice()));
			report.setAccumulatedDepreciation(String.valueOf(hotelAnnual
					.getDepreciation()));
			report.setCurrentYearDepreciation(String.valueOf(hotelAnnual
					.getYearDepreciation()));
			report.setCapitalTotal(String.valueOf(hotelAnnual.getAssetTotal()));
			report.setTotalLiabilities(String.valueOf(hotelAnnual
					.getLiabilitiesTotal()));
			report.setOwnerBenefitTotal(String.valueOf(hotelAnnual
					.getPossessorTotal()));
			report.setRealCapital(String.valueOf(hotelAnnual.getRealIncome()));
			report.setOperationIncome(String.valueOf(hotelAnnual
					.getOperationIncome()));
			report.setRoomRevenue(String.valueOf(hotelAnnual.getRoomIncome()));
			report.setCateringIncome(String.valueOf(hotelAnnual
					.getCanteeIncome()));
			report.setOtherIncome(String.valueOf(hotelAnnual.getOtherIncome()));
			report.setOperationCost(String.valueOf(hotelAnnual
					.getOperationCost()));
			report.setOperationFee(String.valueOf(hotelAnnual.getOperationFee()));
			report.setOperationTaxAddition(String.valueOf(hotelAnnual
					.getOprFeeAndTax()));
			report.setManagementFee(String.valueOf(hotelAnnual.getManageCost()));
			report.setTax(String.valueOf(hotelAnnual.getTaxes()));
			report.setYearendEmployment(String.valueOf(hotelAnnual.getPeople()));
			report.setJuniorEmployment(String.valueOf(hotelAnnual.getCollege()));
			report.setBed(String.valueOf(hotelAnnual.getBed()));
			report.setRoom(String.valueOf(hotelAnnual.getRoom()));
			report.setActualRent(String.valueOf(hotelAnnual.getActualRent()));
			report.setForHire(String.valueOf(hotelAnnual.getForHire()));
			report.setManagementFee(String.valueOf(hotelAnnual.getManageCost())); //	管理费用
			report.setTheVAT(String.valueOf(hotelAnnual.getAddedValueTax())); //	本年应交增值税
			report.setNonIncome(String.valueOf(hotelAnnual.getExtraneousIncome())); //	营业外收入
			report.setSubsidies(String.valueOf(hotelAnnual.getSubsidies())); //		政府补贴
			report.setEnergyCosts(String.valueOf(hotelAnnual.getConsumption())); //		能耗成本
			report.setNian(hotelAnnual.getYear());
			report.setCity("002008013");
			report.setDeposit(String.valueOf(hotelAnnual.getDeposit()));

			logger.info("调用新增酒店年报接口：" + hotelAnnual.getId() + " type:" + type);
			out = new DataOutputStream(connection.getOutputStream());
			// The URL-encoded contend
			// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
			String content = null;
			content = "json="
					+ URLEncoder.encode(JSON.toJSONString(report), "UTF-8");
			content += "&type=" + URLEncoder.encode(type, "UTF-8");
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
			JSONObject object = JSON.parseObject(line);
			if (!"0".equals(object.getString("status")))
				tip = object.getString("message");
			logger.info("调用新增酒店年报接口结果：" + tip + " type:" + type);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "通信异常!";
			logger.info("调用新增酒店年报接口异常：" + e.toString() + " 年报id："
					+ hotelAnnual.getId() + " type:" + type);
		}
		return tip;
	}

	/**
	 * 获取酒店年报审核状态
	 */
	public String getAnnualReport(HotelAnnual hotelAnnual) {
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/hotel_year_info";
		try {
			// 暂停1s
			Thread.sleep(1000);
			String year = hotelAnnual.getYear();
			// 获取酒店对象
			Hotelmanage hotelmanage = systemService.get(Hotelmanage.class,
					hotelAnnual.getHotelAid());
			HttpURLConnection connection = this.getConnection(url);
			DataOutputStream out = null;
			out = new DataOutputStream(connection.getOutputStream());
			// The URL-encoded contend
			// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
			String content = "code="
					+ URLEncoder.encode(hotelmanage.getCode(), "UTF-8");
			content += "&year=" + URLEncoder.encode(year, "UTF-8");
			// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
			logger.info("调用获取酒店年报审核状态接口：" + hotelAnnual.getId());
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
			logger.info("调用获取酒店年报审核状态接口结果：" + line);
			// 数据转为json对象
			JSONObject jsonObject = JSON.parseObject(line);
			String data = jsonObject.get("data").toString();

			JSONArray list = JSON.parseArray(data);
			if (list == null || list.size() == 0)
				return "-1";
			JSONObject obj = JSON.parseObject(list.get(0).toString());
			String status = obj.getString("Sheng");
			// 关闭连接
			connection.disconnect();
			reader.close();
			return status;
		} catch (Exception e) {
			logger.info("调用获取酒店年报审核状态接口异常：" + e.toString() + " 年报id："
					+ hotelAnnual.getId());
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 年报国家系统审核意见
	 */
	public String getAnnaulReportContent(String code, String year){
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/getcheckInfo";
		try {
				HttpURLConnection connection = this.getConnection(url);
				logger.info("调用年报国家系统审核意见接口");
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				// The URL-encoded contend
				// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
				String content = null;
				content = "code="
						+ URLEncoder.encode(code, "UTF-8");
				content += "&dates=" + URLEncoder.encode(year, "UTF-8");
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
				logger.info("年报国家系统审核意见接口结果：" +line+"code:"+code);

				// 数据转为json对象
				JSONObject jsonObject = JSON.parseObject(line);
				String status = jsonObject.getString("status");
				// 是否成功调用
				if(!"0".equals(status)) return null;
				
				JSONObject data = jsonObject.getJSONObject("data");
				
				// 关闭连接
				connection.disconnect();
				reader.close();
				
				return data.getString("sheng");
		} catch (Exception e) {
			logger.info("年报国家系统审核意见异常：" + e.toString()+"code:"+code);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 新增旅游局
	 * 
	 * @author Fp
	 */
	public String addTourism(List<TouristOffices> list) {
		String tip = "200";
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/tourism_add";
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			TouristOffices touristOffices = (TouristOffices) iterator.next();
			try {
				HttpURLConnection connection = this.getConnection(url);
				DataOutputStream out = null;
				TouristOffice to = new TouristOffice();
				to.setUserName(touristOffices.getAccount());
				to.setPermit(touristOffices.getAccount());
				// CNCoName 名称
				to.setCNCoName(touristOffices.getName());
				// Address 地址
				to.setAddress(touristOffices.getAdd());
				// PostCode 邮编
				to.setPostCode(touristOffices.getPostcode());
				// Tel 手机
				to.setTel(touristOffices.getTel());
				// InArea 地区
				to.setInArea(touristOffices.getArea());
				// Fax 传真
				to.setFax(touristOffices.getFax());
				// Url 网址
				to.setUrl(touristOffices.getHttp());
				// QQ
				to.setQq(touristOffices.getQq());
				// Email
				to.setEmail(touristOffices.getEmail());
				// Manager 负责人
				to.setManager(touristOffices.getHead());
				out = new DataOutputStream(connection.getOutputStream());
				// The URL-encoded contend
				// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
				String content = null;
				content = "json="
						+ URLEncoder.encode(JSON.toJSONString(to), "UTF-8");
				content += "&city=" + URLEncoder.encode("002008013", "UTF-8");
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
				logger.info("新增旅游局接口结果：" + line + " id："
						+ touristOffices.getId());
				JSONObject object = JSON.parseObject(line);
				if (!"0".equals(object.getString("status")))
					tip = object.getString("message");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("新增旅游局接口异常：" + e.toString() + " id："
						+ touristOffices.getId());
				tip = "通信异常!";
			}
		}
		return tip;
	}

	/**
	 * 
	 * 编辑旅游局
	 * 
	 * @author Fp
	 */
	public String editTourism(List<TouristOffices> list) {
		String tip = "200";
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/tourism_edit";
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			TouristOffices touristOffices = (TouristOffices) iterator.next();
			try {
				HttpURLConnection connection = this.getConnection(url);
				DataOutputStream out = null;
				TouristOffice to = new TouristOffice();
				to.setUserName(touristOffices.getAccount());
				to.setPermit(touristOffices.getAccount());
				// CNCoName 名称
				to.setCNCoName(touristOffices.getName());
				// Address 地址
				to.setAddress(touristOffices.getAdd());
				// PostCode 邮编
				to.setPostCode(touristOffices.getPostcode());
				// Tel 手机
				to.setTel(touristOffices.getTel());
				// InArea 地区
				to.setInArea(touristOffices.getArea());
				// Fax 传真
				to.setFax(touristOffices.getFax());
				// Url 网址
				to.setUrl(touristOffices.getHttp());
				// QQ
				to.setQq(touristOffices.getQq());
				// Email
				to.setEmail(touristOffices.getEmail());
				// Manager 负责人
				to.setManager(touristOffices.getHead());
				out = new DataOutputStream(connection.getOutputStream());
				// The URL-encoded contend
				// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
				String content = null;
				content = "json="
						+ URLEncoder.encode(JSON.toJSONString(to), "UTF-8");
				content += "&city=" + URLEncoder.encode("002008013", "UTF-8");
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
				logger.info("编辑旅游局接口结果：" + line + " id："
						+ touristOffices.getId());
				JSONObject object = JSON.parseObject(line);
				if (!"0".equals(object.getString("status")))
					tip = object.getString("message");
			} catch (Exception e) {
				logger.info("编辑旅游局接口异常：" + e.toString() + " id："
						+ touristOffices.getId());
				e.printStackTrace();
				tip = "通信异常!";
			}
		}
		return tip;
	}

	/**
	 * 预计接待数据
	 */
	public String addHotelEstimate(HotelEstimate hotelEstimate) {
		String tip = "200";
		WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance
				.getHotelProvinceService();
		try {
			Thread.sleep(100);
			HotelEstimateInMonth hotelEstimateInMonth = new HotelEstimateInMonth();
			hotelEstimateInMonth.setCountryCode(hotelEstimate.getCountryCode());
			hotelEstimateInMonth.setYear(Integer.parseInt(hotelEstimate
					.getYear()));
			hotelEstimateInMonth.setMonth(Integer.parseInt(hotelEstimate
					.getMonth()));
			hotelEstimateInMonth.setOptUser("D5460201001");
			com.zzc.webservice.provinceHotel.HotelEstimateValues day = new com.zzc.webservice.provinceHotel.HotelEstimateValues();
			day.setAbroad(hotelEstimate.getEntryD());
			day.setDomestic(hotelEstimate.getDomesticD());
			day.setForeigner(hotelEstimate.getForeignDays());
			day.setHongkong(hotelEstimate.getHongkongD());
			day.setMacao(hotelEstimate.getMacaoD());
			day.setTaiwan(hotelEstimate.getTaiwanD());
			day.setTotal(hotelEstimate.getSumD());
			hotelEstimateInMonth.setPeopleDay(day);
			com.zzc.webservice.provinceHotel.HotelEstimateValues num = new com.zzc.webservice.provinceHotel.HotelEstimateValues();
			num.setAbroad(hotelEstimate.getEntry());
			num.setDomestic(hotelEstimate.getDomestic());
			num.setForeigner(hotelEstimate.getForeignTimes());
			num.setHongkong(hotelEstimate.getHongkong());
			num.setMacao(hotelEstimate.getMacao());
			num.setTaiwan(hotelEstimate.getTaiwan());
			num.setTotal(hotelEstimate.getSum());
			hotelEstimateInMonth.setPeopleNum(num);
			AddHotelEstimateResponseAddHotelEstimateResult rs = hotelProvinceService
					.addHotelEstimate(GlobalParams.hotelProvinceCode,
							hotelEstimateInMonth);
			Document doc = DocumentHelper.parseText(rs.get_any()[0].toString());
			Element root = doc.getRootElement();
			logger.info("预计接待数据接口结果：" + rs.get_any()[0].toString() + " id："
					+ hotelEstimate.getId());
			// 判断接口是否调用成功
			if (!"1".equals(root.element("DataStructure").element("ReturnCode")
					.getText())) {
				tip = "接口返回失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			tip = "操作异常";
			logger.info("预计接待数据接口异常：" + e.toString() + " id："
					+ hotelEstimate.getId());
		}
		return tip;
	}

	// 季度停业
	public String seasonStop(Hotelmanage hotelmanage) {
		String tip = "200";
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/";
		try {

			Thread.sleep(500);

			// 判断是停业还是开业
			if ("1".equals(hotelmanage.getOnBuinessSeason())) {
				url = url + "sea_stop_cancel";
			} else {
				url = url + "sea_stop_set";
			}

			HttpURLConnection connection = this.getConnection(url);
			DataOutputStream out = null;
			out = new DataOutputStream(connection.getOutputStream());
			// The URL-encoded contend
			// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
			String content = null;
			content = "code="
					+ URLEncoder.encode(hotelmanage.getCode(), "UTF-8");
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
			JSONObject object = JSON.parseObject(line);
			logger.info("季度停业接口结果：" + line + " id："
					+ hotelmanage.getId());
			if (!"0".equals(object.getString("status")))
				tip = object.getString("message");
		} catch (Exception e) {
			e.printStackTrace();
			tip = "通信异常!";
			logger.info("季度停业接口异常：" + e.toString() + " id："
					+ hotelmanage.getId());
		}
		return tip;
	}

	// 年度停业
	public String yearStop(Hotelmanage hotelmanage) {
		String tip = "200";
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/";
		try {

			Thread.sleep(500);

			// 判断是停业还是开业
			if ("1".equals(hotelmanage.getOnBuinessSeason())) {
				url = url + "year_stop_cancel";
			} else {
				url = url + "year_stop_set";
			}

			HttpURLConnection connection = this.getConnection(url);
			DataOutputStream out = null;
			out = new DataOutputStream(connection.getOutputStream());
			// The URL-encoded contend
			// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
			String content = null;
			content = "code="
					+ URLEncoder.encode(hotelmanage.getCode(), "UTF-8");
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
			JSONObject object = JSON.parseObject(line);
			logger.info("年度停业接口结果：" + line + " id："
					+ hotelmanage.getId());
			if (!"0".equals(object.getString("status")))
				tip = object.getString("message");
		} catch (Exception e) {
			e.printStackTrace();
			tip = "通信异常!";
			logger.info("年度停业接口异常：" + e.toString() + " id："
					+ hotelmanage.getId());
		}
		return tip;
	}

	
	/**
	 * 
	 * 端午节
	 */
	public String addDragonBoatFestival(Holiday holiday){
		WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance.getHotelProvinceService();
		String tip = "200";
		DragonBoatFestivalInfo dragonBoatFestivalInfo = new DragonBoatFestivalInfo();
		
//		            HotelCount 酒店人数
		dragonBoatFestivalInfo.setHotelCount(new BigDecimal(holiday.getHotelNum()));
//		            HotelEarning 酒店收入
		dragonBoatFestivalInfo.setHotelEarning(new BigDecimal(holiday.getHotelIncome()));
//		            PlaceCount 景区人数
		dragonBoatFestivalInfo.setPlaceCount(new BigDecimal(holiday.getScenicNum()));
//		            PlaceEarning 景区收入
		dragonBoatFestivalInfo.setPlaceEarning(new BigDecimal(holiday.getScenicIncome()));
//		            VillageCount 乡村人数
		dragonBoatFestivalInfo.setVillageCount(new BigDecimal(holiday.getTownTravelNum()));
//		            VillageEarning 乡村收入
		dragonBoatFestivalInfo.setVillageEarning(new BigDecimal(holiday.getTownTravelIncome()));
//		ExemptionCount  免税店人数
		dragonBoatFestivalInfo.setExemptionCount(new BigDecimal(holiday.getTaxFreeNum()));
//		ExemptionEarning 免税店收入
		dragonBoatFestivalInfo.setExemptionEarning(new BigDecimal(holiday.getTaxFreeIncome()));
//		UserAccount 当前登录用户名
		String userName = ResourceUtil.getSessionUserName().getUserName();
		AddDragonBoatFestivalResponseAddDragonBoatFestivalResult rs = null;
		try {
			rs = hotelProvinceService.addDragonBoatFestival(GlobalParams.hotelProvinceCode, dragonBoatFestivalInfo, userName);
			logger.info("端午节接口结果：" + rs + " id："
					+ holiday.getId());
		} catch (RemoteException e) {
			e.printStackTrace();
			tip = "通信异常!";
			logger.info("端午节接口异常：" + e.toString() + " id："
					+ holiday.getId());
		}
		return tip;
	
	}
	
	/**
	 * 
	 * 中秋节
	 */
	public String addMoonFestival(Holiday holiday){
		WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance.getHotelProvinceService();
		String tip = "200";
		MoonFestivalInfo moonFestivalInfo = new MoonFestivalInfo();
//		            HotelCount 酒店人数
		moonFestivalInfo.setHotelCount(new BigDecimal(holiday.getHotelNum()));
//		            HotelEarning 酒店收入
		moonFestivalInfo.setHotelEarning(new BigDecimal(holiday.getHotelIncome()));
//		            PlaceCount 景区人数
		moonFestivalInfo.setPlaceCount(new BigDecimal(holiday.getScenicNum()));
//		            PlaceEarning 景区收入
		moonFestivalInfo.setPlaceEarning(new BigDecimal(holiday.getScenicIncome()));
//		            VillageCount 乡村人数
		moonFestivalInfo.setVillageCount(new BigDecimal(holiday.getTownTravelNum()));
//		            VillageEarning 乡村收入
		moonFestivalInfo.setVillageEarning(new BigDecimal(holiday.getTownTravelIncome()));
//		ExemptionCount  免税店人数
		moonFestivalInfo.setExemptionCount(new BigDecimal(holiday.getTaxFreeNum()));
//		ExemptionEarning 免税店收入
		moonFestivalInfo.setExemptionEarning(new BigDecimal(holiday.getTaxFreeIncome()));
//		UserAccount 当前登录用户名
		String userName = ResourceUtil.getSessionUserName().getUserName();
		AddMoonFestivalResponseAddMoonFestivalResult rs = null;
		try {
			rs = hotelProvinceService.addMoonFestival(GlobalParams.hotelProvinceCode, moonFestivalInfo, userName);
			logger.info("中秋节接口结果：" + rs + " id："
					+ holiday.getId());
		} catch (RemoteException e) {
			e.printStackTrace();
			tip = "通信异常!";
			logger.info("中秋节接口异常：" + e.toString() + " id："
					+ holiday.getId());
		}
		return tip;
	}
}
