package com.zzc.app.login;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zzc.app.entity.AppTSUser;
import com.zzc.app.entity.EnterpriseInfo;
import com.zzc.app.login.entity.APPHotel;
import com.zzc.app.login.entity.APPOtherTravel;
import com.zzc.app.login.entity.APPTraveldata;
import com.zzc.app.utils.ResolveParams;
import com.zzc.core.extend.datasource.DataSourceContextHolder;
import com.zzc.core.extend.datasource.DataSourceType;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.entity.HotelmanageSta;
import com.zzc.web.otherTravel.entity.OtherTravelInfo;
import com.zzc.web.system.pojo.base.TSDepart;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.system.service.UserService;
import com.zzc.web.travel.entity.Traveldata;

@Controller
@RequestMapping("/appservice/userlogincontroller")
public class UserLoginController {

	private SystemService systemService;
	private UserService userService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(params = "getVersion")
	@ResponseBody
	public JSONObject getVersion(HttpServletRequest request,
			HttpServletResponse response) {
		InputStream in = new BufferedInputStream(
				UserLoginController.class
						.getResourceAsStream("/sysConfig.properties"));
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			return null;
		}
		JSONObject json = new JSONObject();
		json.put("android_version", p.getProperty("android_version"));
		json.put("ios_version", p.getProperty("ios_version"));
		return json;
	}

	/**
	 * 
	 * 服务策略
	 * 
	 * @author Fp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "privacyClause")
	public ModelAndView privacyClause(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("appservice/pages/privacyClause");
	}

	/**
	 * 
	 * 隐私条款
	 * 
	 * @author Fp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "serviceStrategy")
	public ModelAndView otserviceStrategyherTravel(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("appservice/pages/serviceStrategy");
	}

	@RequestMapping(params = "otherTravel")
	public ModelAndView otherTravel(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		request.setAttribute("userId", userId);
		return new ModelAndView("appservice/company");
	}

	@RequestMapping(params = "hotel")
	public ModelAndView hotel(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		request.setAttribute("userId", userId);
		return new ModelAndView("appservice/hotel");
	}

	@RequestMapping(params = "scenic")
	public ModelAndView scenic(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		request.setAttribute("userId", userId);
		return new ModelAndView("appservice/scenic");
	}

	@RequestMapping(params = "login")
	@ResponseBody
	public AppTSUser login(HttpServletRequest request,
			HttpServletResponse response) {
		// 请求参数解析
		Map<String, String> map = ResolveParams.getParameterMap(request);

		String username = map.get("username");
		String password = map.get("password");

		String key = "D1B5CC2FE46C4CC983C073BCA897935608D926CD32992B5900";
		TSUser user = new TSUser();
		user.setUserKey(key);
		user.setUserName(username);
		user.setPassword(password);
		DataSourceContextHolder
				.setDataSourceType(DataSourceType.dataSource_zzc);

		String tip = "success";
		AppTSUser appUser = new AppTSUser();
		int users = userService.getList(TSUser.class).size();
		try {
			if (users == 0) {
				tip = "用户名或密码错误";
				appUser.setTip(tip);
				return appUser;
			} else {
				TSUser u = userService.checkUserExits(user);
				if (u == null) {
					tip = "用户名或密码错误";
					appUser.setTip(tip);
					return appUser;
				}
				TSUser u2 = userService.getEntity(TSUser.class, u.getId());
				if (u != null && u2.getStatus() != 0) {
					appUser = new AppTSUser();
					Map<String, Object> attrMap = new HashMap<String, Object>();
					Long orgNum = systemService
							.getCountForJdbc("select count(1) from t_s_user_org where user_id = '"
									+ u.getId() + "'");
					if (orgNum > 1) {
						attrMap.put("orgNum", orgNum);
						attrMap.put("user", u2);
					} else {
						Map<String, Object> userOrgMap = systemService
								.findOneForJdbc(
										"select org_id as orgId from t_s_user_org where user_id=?",
										u2.getId());
						TSDepart currentDepart = systemService.get(
								TSDepart.class,
								(String) userOrgMap.get("orgId"));
						user.setCurrentDepart(currentDepart);
					}
					appUser.setUser(user);
					Map<String, Object> roleMap = systemService
							.findOneForJdbc(
									"select * from t_s_role t where t.id=(select tr.roleid from t_s_role_user tr where tr.userid =?)",
									u2.getId());
					String role = roleMap.get("rolename").toString();
					Map<String, Object> rs = new HashMap<>();
					if (role.contains("旅行社")) {
						StringBuffer sql = new StringBuffer();
						sql.append(" SELECT ");
						sql.append(" 	t.`name` enterpriseName, ");
						sql.append(" 	t.licensenum enterpriseCode, ");
						sql.append(" 	t.address enterpriseAddress, ");
						sql.append(" 	if(t.area='0','市辖区',if(t.area='1','崖州区',if(t.area='2','海棠区',if(t.area='3','天涯区',if(t.area='4','吉阳区',''))))) enterpriseArea, ");
						sql.append(" 	if(t.scene_spotlevel='0','未评定',if(t.scene_spotlevel='1','A级',if(t.scene_spotlevel='2','AA级',if(t.scene_spotlevel='3','AAA级',if(t.scene_spotlevel='4','AAAA级',if(t.scene_spotlevel='5','AAAAA级','')))))) enterpriseType ");
						sql.append(" FROM ");
						sql.append(" 	t_travelagency_info t ");
						sql.append(" WHERE ");
						sql.append(" 	t.user_id=?");
						rs = systemService.findOneForJdbc(sql.toString(),
								u2.getId());
					} else if (role.contains("景区")) {
						StringBuffer sql = new StringBuffer();
						sql.append(" SELECT ");
						sql.append(" 	t.`name` enterpriseName, ");
						sql.append(" 	t. CODE enterpriseCode, ");
						sql.append(" 	t.address enterpriseAddress, ");
						sql.append(" if(t.area='5','三亚市',if(t.area='0','市辖区',if(t.area='1','崖州区',if(t.area='2','海棠区',if(t.area='3','天涯区',if(t.area='4','吉阳区','')))))) enterpriseArea, ");
						sql.append(" 	if(t.`level`='0','未评定',if(t.`level`='1','A级',if(t.`level`='2','AA级',if(t.`level`='3','AAA级',if(t.`level`='4','AAAA级',if(t.`level`='5','AAAAA级','')))))) enterpriseType ");
						sql.append(" FROM ");
						sql.append(" 	t_scenicspot_info_sta t ");
						sql.append(" WHERE ");
						sql.append(" 	t.user_id =?");
						rs = systemService.findOneForJdbc(sql.toString(),
								u2.getId());
					} else if (role.contains("酒店")) {
						StringBuffer sql = new StringBuffer();
						sql.append(" SELECT ");
						sql.append(" 	t.unitname enterpriseName, ");
						sql.append(" 	t.organizationNum enterpriseCode, ");
						sql.append(" 	t.address enterpriseAddress, ");
						sql.append(" 	if(t.w_county='0','市辖区',if(t.w_county='1','崖州区',if(t.w_county='2','海棠区',if(t.w_county='3','天涯区',if(t.w_county='4','吉阳区',''))))) enterpriseArea, ");
						sql.append(" 	if(t.housegrade='0','其他',if(t.housegrade='1','舒适',if(t.housegrade='2','高档',if(t.housegrade='3','豪华',if(t.housegrade='4','一星',if(t.housegrade='5','二星',if(t.housegrade='6','三星',if(t.housegrade='7','四星',if(t.housegrade='8','五星',''))))))))) enterpriseType ");
						sql.append(" FROM ");
						sql.append(" 	t_hotelmanage t ");
						sql.append(" WHERE ");
						sql.append(" 	t.id=? ");
						rs = systemService.findOneForJdbc(sql.toString(),
								u2.getId());
					} else if (role.contains("机场") || role.contains("动车")
							|| role.contains("高尔夫") || role.contains("游艇")
							|| role.contains("空中飞行")) {
						StringBuffer sql = new StringBuffer();
						sql.append(" SELECT ");
						sql.append(" 	t.`name` enterpriseName, ");
						sql.append(" 	'' enterpriseCode, ");
						sql.append(" 	t.address enterpriseAddress, ");
						sql.append(" 	if(t.area='0','市辖区',if(t.area='1','崖州区',if(t.area='2','海棠区',if(t.area='3','天涯区',if(t.area='4','吉阳区',''))))) enterpriseArea, ");
						sql.append(" 	if(t.type='1','高尔夫项目',if(t.type='2','游艇项目',if(t.type='3','空中飞行项目',if(t.type='4','机场项目',if(t.type='5','动车项目',''))))) enterpriseType ");
						sql.append(" FROM ");
						sql.append(" 	t_othercompany_info t ");
						sql.append(" WHERE ");
						sql.append(" 	t.id=? ");
						rs = systemService.findOneForJdbc(sql.toString(),
								u2.getId());
					}
					appUser.setRole(role);
					appUser.setUser(u2);
					appUser.setEnterpriseName(rs.get("enterpriseName") == null ? ""
							: rs.get("enterpriseName").toString());
					appUser.setEnterpriseCode(rs.get("enterpriseCode") == null ? ""
							: rs.get("enterpriseCode").toString());
					appUser.setEnterpriseAddress(rs.get("enterpriseAddress") == null ? ""
							: rs.get("enterpriseAddress").toString());
					appUser.setEnterpriseArea(rs.get("enterpriseArea") == null ? ""
							: rs.get("enterpriseArea").toString());
					appUser.setEnterpriseType(rs.get("enterpriseType") == null ? ""
							: rs.get("enterpriseType").toString());
				} else {
					if (u2.getStatus() == 0) {
						tip = "该账户已被锁定";
						appUser.setTip(tip);
						return appUser;
					}
					tip = "用户信息获取失败,请完善企业信息";
					appUser.setTip(tip);
					return appUser;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			tip = "用户信息获取失败,请完善企业信息";
			appUser.setTip(tip);
			return appUser;
		}
		appUser.setTip(tip);
		return appUser;
	}

	@RequestMapping(params = "enterpriseQuery")
	@ResponseBody
	public List<EnterpriseInfo> enterpriseQuery(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String role = request.getParameter("role");
		if (role == null || role.length() == 0)
			return null;
		List<Map<String, Object>> rs = new ArrayList<>();
		if (role.contains("旅行社")) {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT ");
			sql.append(" 	t.id id, ");
			sql.append(" 	t.phone telephone, ");
			sql.append(" (select g.score from t_user_score g where g.user_id=t.user_id) enterpriseScore, ");
			sql.append(" 	t.`name` enterpriseName, ");
			sql.append(" 	t.licensenum enterpriseCode, ");
			sql.append(" 	t.address enterpriseAddress, ");
			sql.append(" 	if(t.area='0','市辖区',if(t.area='1','崖州区',if(t.area='2','海棠区',if(t.area='3','天涯区',if(t.area='4','吉阳区',''))))) enterpriseArea, ");
			sql.append(" 	if(t.scene_spotlevel='0','未评定',if(t.scene_spotlevel='1','A级',if(t.scene_spotlevel='2','AA级',if(t.scene_spotlevel='3','AAA级',if(t.scene_spotlevel='4','AAAA级',if(t.scene_spotlevel='5','AAAAA级','')))))) enterpriseType ");
			sql.append(" FROM ");
			sql.append(" 	t_travelagency_info t ");
			sql.append(" WHERE 1=1 ");
			if (name != null && name.length() != 0) {
				sql.append(" and t.`name` like '%" + name + "%'");
			}
			rs = systemService.findForJdbc(sql.toString());
		} else if (role.contains("景区")) {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT ");
			sql.append(" 	t.id id, ");
			sql.append(" 	t.consult_phone telephone, ");
			sql.append(" (select g.score from t_user_score g where g.user_id=t.id) enterpriseScore, ");
			sql.append(" 	t.`name` enterpriseName, ");
			sql.append(" 	t. CODE enterpriseCode, ");
			sql.append(" 	t.address enterpriseAddress, ");
			sql.append(" 	if(t.area='5','三亚市',if(t.area='0','市辖区',if(t.area='1','崖州区',if(t.area='2','海棠区',if(t.area='3','天涯区',if(t.area='4','吉阳区','')))))) enterpriseArea, ");
			sql.append(" 	if(t.`level`='0','未评定',if(t.`level`='1','A级',if(t.`level`='2','AA级',if(t.`level`='3','AAA级',if(t.`level`='4','AAAA级',if(t.`level`='5','AAAAA级','')))))) enterpriseType ");
			sql.append(" FROM ");
			sql.append(" 	t_scenicspot_info_sta t ");
			sql.append(" WHERE 1=1 ");
			if (name != null && name.length() != 0) {
				sql.append(" and t.`name` like '%" + name + "%'");
			}
			rs = systemService.findForJdbc(sql.toString());
		} else if (role.contains("酒店")) {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT ");
			sql.append(" 	t.id id, ");
			sql.append(" 	t.telephone telephone, ");
			sql.append(" (select g.score from t_user_score g where g.user_id=t.id) enterpriseScore, ");
			sql.append(" 	t.unitname enterpriseName, ");
			sql.append(" 	t.organizationNum enterpriseCode, ");
			sql.append(" 	t.address enterpriseAddress, ");
			sql.append(" 	if(t.w_county='0','市辖区',if(t.w_county='1','崖州区',if(t.w_county='2','海棠区',if(t.w_county='3','天涯区',if(t.w_county='4','吉阳区',''))))) enterpriseArea, ");
			sql.append(" 	if(t.housegrade='0','其他',if(t.housegrade='1','舒适',if(t.housegrade='2','高档',if(t.housegrade='3','豪华',if(t.housegrade='4','一星',if(t.housegrade='5','二星',if(t.housegrade='6','三星',if(t.housegrade='7','四星',if(t.housegrade='8','五星',''))))))))) enterpriseType ");
			sql.append(" FROM ");
			sql.append(" 	t_hotelmanage t ");
			sql.append(" WHERE 1=1 ");
			if (name != null && name.length() != 0) {
				sql.append(" and t.unitname like '%" + name + "%'");
			}
			rs = systemService.findForJdbc(sql.toString());
		} else if (role.contains("机场") || role.contains("动车")
				|| role.contains("高尔夫") || role.contains("游艇")
				|| role.contains("空中飞行")) {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT ");
			sql.append(" 	t.id id, ");
			sql.append(" 	t.phone telephone, ");
			sql.append(" 	t.`name` enterpriseName, ");
			sql.append(" 	'' enterpriseCode, ");
			sql.append(" 	t.address enterpriseAddress, ");
			sql.append(" 	if(t.area='0','市辖区',if(t.area='1','崖州区',if(t.area='2','海棠区',if(t.area='3','天涯区',if(t.area='4','吉阳区',''))))) enterpriseArea, ");
			sql.append(" 	if(t.type='1','高尔夫项目',if(t.type='2','游艇项目',if(t.type='3','空中飞行项目',if(t.type='4','机场项目',if(t.type='5','动车项目',''))))) enterpriseType ");
			sql.append(" FROM ");
			sql.append(" 	t_othercompany_info t ");
			sql.append(" WHERE 1=1 ");
			if (name != null && name.length() != 0) {
				sql.append(" and t.`name` like '%" + name + "%'");
			}
			rs = systemService.findForJdbc(sql.toString());
		}
		List<EnterpriseInfo> list = new ArrayList<>();
		for (Iterator iterator = rs.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
			enterpriseInfo.setId(map.get("id") == null ? "" : map.get("id")
					.toString());
			enterpriseInfo
					.setEnterpriseAddress(map.get("enterpriseAddress") == null ? ""
							: map.get("enterpriseAddress").toString());
			enterpriseInfo
					.setEnterpriseArea(map.get("enterpriseArea") == null ? ""
							: map.get("enterpriseArea").toString());
			enterpriseInfo
					.setEnterpriseCode(map.get("enterpriseCode") == null ? ""
							: map.get("enterpriseCode").toString());
			enterpriseInfo
					.setEnterpriseName(map.get("enterpriseName") == null ? ""
							: map.get("enterpriseName").toString());
			enterpriseInfo
					.setEnterpriseType(map.get("enterpriseType") == null ? ""
							: map.get("enterpriseType").toString());
			enterpriseInfo
					.setEnterpriseScore(map.get("enterpriseScore") == null ? ""
							: map.get("enterpriseScore").toString());
			enterpriseInfo.setTelephone(map.get("telephone") == null ? "" : map
					.get("telephone").toString());
			list.add(enterpriseInfo);
		}
		return list;
	}

	/**
	 * 获取旅行社基本信息
	 * 
	 * @param request
	 * @param response
	 * @return 旅行社基本信息
	 */
	@RequestMapping(params = "getTravelAgencyDetailInfo")
	@ResponseBody
	public APPTraveldata getTravelAgencyDetailInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");

		APPTraveldata data = new APPTraveldata();
		// 参数校验
		if (userId == null || userId.length() == 0)
			return data;

		List<Traveldata> traveldataList = systemService.findByProperty(
				Traveldata.class, "userId", userId);
		Traveldata traveldata = traveldataList.get(0);
		// 主键
		data.setId(traveldata.getId());
		// 许可证编号
		data.setLicensenum(traveldata.getLicensenum());
		;
		// 旅行社名称
		data.setName(traveldata.getName());
		// 英文名称
		data.setEnglishname(traveldata.getEnglishname());
		// 组织机构代码
		data.setOrganizationnum(traveldata.getOrganizationnum());
		// 法人代表
		data.setCorporate(traveldata.getCorporate());
		// 行政区域代码
		data.setAdminnum(traveldata.getAdminnum());
		// 统一社会信用代码
		data.setProvince(traveldata.getProvince());
		// 所在地区
		data.setArea(traveldata.getArea());
		// 详细地址
		data.setAddress(traveldata.getAddress());
		// 邮政编码
		data.setZonecode(traveldata.getZonecode());
		// 固定电话
		data.setPhone(traveldata.getPhone());
		// 固定电话分机
		data.setPhoneextension(traveldata.getPhoneextension());
		// 传真
		data.setFax(traveldata.getFax());
		// 传真分机
		data.setFaxextension(traveldata.getFaxextension());
		// 手机
		data.setMobile(traveldata.getMobile());
		// 邮箱
		data.setEmail(traveldata.getEmail());
		// 单位负责人
		data.setPrincipal(traveldata.getPrincipal());
		// 企业登记注册类型
		data.setRegisterstyle(traveldata.getRegisterstyle());
		// 是否经营出境游
		data.setBusinessexit("1".equals(traveldata.getBusinessexit()) ? "是"
				: "否");
		// 是否经营边境游
		data.setBusinessborder("1".equals(traveldata.getBusinessborder()) ? "是"
				: "否");
		// 旅行社协会会员
		data.setIsmember("1".equals(traveldata.getIsmember()) ? "是" : "否");
		// qq
		data.setQq(traveldata.getQq());
		// 网站
		data.setWeburl(traveldata.getWeburl());
		// 旅行社等级
		data.setSceneSpotlevel(traveldata.getSceneSpotlevel());
		// 工商登记注册时间
		data.setTableDate(traveldata.getTableDate());
		// 填表人
		data.setFiller(traveldata.getFiller());
		// 填表人电话
		data.setFillerTel(traveldata.getFillerTel());
		// 批准文号
		data.setAccommodationStar(traveldata.getAccommodationStar());
		// 旅行社经营场所
		data.setTravelAgencyType(traveldata.getTravelAgencyType());
		return data;
	}

	/**
	 * 获取酒店基本信息
	 * 
	 * @param request
	 * @param response
	 * @return 酒店基本信息
	 */
	@RequestMapping(params = "getOfficalHotelDetailInfo")
	@ResponseBody
	public APPHotel getOfficalHotelDetailInfo(HttpServletRequest request,
			HttpServletResponse response) {

		/** 设置响应头允许ajax跨域访问 **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		String userId = request.getParameter("userId");

		APPHotel data = new APPHotel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			// 获取酒店信息
			Hotelmanage hotelmanage = systemService.get(Hotelmanage.class,
					userId);
			data.setId(hotelmanage.getId());
			// 标牌编码
			data.setCode(hotelmanage.getCode());
			// 酒店法人
			data.setLegalPerson(hotelmanage.getLegalPerson());
			// 组织机构代码
			data.setOrganizationNum(hotelmanage.getOrganizationNum());
			// 单位名称
			data.setUnitname(hotelmanage.getUnitname());
			// 所属市，默认三亚市
			data.setCity("三亚市");
			// 所在地区，"市辖区_0","吉阳区_1","崖州区_2","天涯区_3","海棠区_4"
			String county = "";
			switch (hotelmanage.getCounty() == null ? "" : hotelmanage
					.getCounty()) {
			case "0":
				county = "市辖区";
				break;
			case "1":
				county = "吉阳区";
				break;
			case "2":
				county = "崖州区";
				break;
			case "3":
				county = "天涯区";
				break;
			case "4":
				county = "海棠区";
				break;
			}
			data.setCounty(county);
			// 所属湾区 0 市区1亚龙湾2大东海3三亚湾 4海棠湾
			String bay = "";
			switch (hotelmanage.getBay() == null ? "" : hotelmanage.getBay()) {
			case "0":
				bay = "市区";
				break;
			case "1":
				bay = "亚龙湾";
				break;
			case "2":
				bay = "大东海";
				break;
			case "3":
				bay = "三亚湾";
				break;
			case "4":
				bay = "海棠湾";
				break;
			}
			data.setBay(bay);
			// 地址
			data.setAddress(hotelmanage.getAddress());
			// 负责人
			data.setManager(hotelmanage.getManager());
			// 邮政编码
			data.setZipcode(hotelmanage.getZipcode());
			// 电话号码
			data.setTelephone(hotelmanage.getTelephone());
			// 手机号码
			data.setMobile(hotelmanage.getMobile());
			// 酒店星级，"其他_0","舒适_1","高档_2","豪华_3","一星_4","二星_5","三星_6","四星_7","五星_8"
			String housegrade = "";
			switch (hotelmanage.getHousegrade() == null ? "" : hotelmanage
					.getHousegrade()) {
			case "0":
				housegrade = "其他";
				break;
			case "1":
				housegrade = "舒适";
				break;
			case "2":
				housegrade = "高档";
				break;
			case "3":
				housegrade = "豪华";
				break;
			case "4":
				housegrade = "一星";
				break;
			case "5":
				housegrade = "二星";
				break;
			case "6":
				housegrade = "三星";
				break;
			case "7":
				housegrade = "四星";
				break;
			case "8":
				housegrade = "五星";
				break;
			}
			data.setHousegrade(housegrade);
			// 房间数
			data.setHousenum(hotelmanage.getHousenum() == null ? "" : String
					.valueOf(hotelmanage.getHousenum()));
			// 床位数
			data.setBednum(hotelmanage.getBednum() == null ? "" : String
					.valueOf(hotelmanage.getBednum()));
			// 工商注册登记时间
			String registertime = "";
			try {
				registertime = sdf.format(hotelmanage.getRegistertime());
			} catch (Exception e) {

			}
			data.setRegistertime(registertime);
			// 企业注册登记类型
			data.setRegisterstyle(hotelmanage.getRegisterstyle());
			// 填表人
			data.setWriter(hotelmanage.getWriter());
			// 传真
			data.setFax(hotelmanage.getFax());
			// 网址
			data.setWeburl(hotelmanage.getWeburl());
			// 邮件
			data.setEmail(hotelmanage.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 获取酒店基本信息
	 * 
	 * @param request
	 * @param response
	 * @return 酒店基本信息
	 */
	@RequestMapping(params = "getHotelDetailInfo")
	@ResponseBody
	public APPHotel getHotelDetailInfo(HttpServletRequest request,
			HttpServletResponse response) {

		/** 设置响应头允许ajax跨域访问 **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		String userId = request.getParameter("userId");

		APPHotel data = new APPHotel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			// 获取酒店信息
			HotelmanageSta hotelmanage = systemService.get(
					HotelmanageSta.class, userId);
			data.setId(hotelmanage.getId());
			// 标牌编码
			data.setCode(hotelmanage.getCode());
			// 酒店法人
			data.setLegalPerson(hotelmanage.getLegalPerson());
			// 组织机构代码
			data.setOrganizationNum(hotelmanage.getOrganizationNum());
			// 单位名称
			data.setUnitname(hotelmanage.getUnitname());
			// 所属市，默认三亚市
			data.setCity("三亚市");
			// 所在地区，"市辖区_0","吉阳区_1","崖州区_2","天涯区_3","海棠区_4"
			String county = "";
			switch (hotelmanage.getCounty() == null ? "" : hotelmanage
					.getCounty()) {
			case "0":
				county = "市辖区";
				break;
			case "1":
				county = "吉阳区";
				break;
			case "2":
				county = "崖州区";
				break;
			case "3":
				county = "天涯区";
				break;
			case "4":
				county = "海棠区";
				break;
			}
			data.setCounty(county);
			// 所属湾区 0 市区1亚龙湾2大东海3三亚湾 4海棠湾
			String bay = "";
			switch (hotelmanage.getBay() == null ? "" : hotelmanage.getBay()) {
			case "0":
				bay = "市区";
				break;
			case "1":
				bay = "亚龙湾";
				break;
			case "2":
				bay = "大东海";
				break;
			case "3":
				bay = "三亚湾";
				break;
			case "4":
				bay = "海棠湾";
				break;
			}
			data.setBay(bay);
			// 地址
			data.setAddress(hotelmanage.getAddress());
			// 负责人
			data.setManager(hotelmanage.getManager());
			// 邮政编码
			data.setZipcode(hotelmanage.getZipcode());
			// 电话号码
			data.setTelephone(hotelmanage.getTelephone());
			// 手机号码
			data.setMobile(hotelmanage.getMobile());
			// 酒店星级，"其他_0","舒适_1","高档_2","豪华_3","一星_4","二星_5","三星_6","四星_7","五星_8"
			String housegrade = "";
			switch (hotelmanage.getHousegrade() == null ? "" : hotelmanage
					.getHousegrade()) {
			case "0":
				housegrade = "其他";
				break;
			case "1":
				housegrade = "舒适";
				break;
			case "2":
				housegrade = "高档";
				break;
			case "3":
				housegrade = "豪华";
				break;
			case "4":
				housegrade = "一星";
				break;
			case "5":
				housegrade = "二星";
				break;
			case "6":
				housegrade = "三星";
				break;
			case "7":
				housegrade = "四星";
				break;
			case "8":
				housegrade = "五星";
				break;
			}
			data.setHousegrade(housegrade);
			// 房间数
			data.setHousenum(hotelmanage.getHousenum() == null ? "" : String
					.valueOf(hotelmanage.getHousenum()));
			// 床位数
			data.setBednum(hotelmanage.getBednum() == null ? "" : String
					.valueOf(hotelmanage.getBednum()));
			// 工商注册登记时间
			String registertime = "";
			try {
				registertime = sdf.format(hotelmanage.getRegistertime());
			} catch (Exception e) {

			}
			data.setRegistertime(registertime);
			// 企业注册登记类型
			data.setRegisterstyle(hotelmanage.getRegisterstyle());
			// 填表人
			data.setWriter(hotelmanage.getWriter());
			// 传真
			data.setFax(hotelmanage.getFax());
			// 网址
			data.setWeburl(hotelmanage.getWeburl());
			// 邮件
			data.setEmail(hotelmanage.getEmail());
			// 审核状态
			String status = this.getStatus(hotelmanage.getStatus());
			data.setStatus(status);
			String districtStatus = this.getStatus(hotelmanage
					.getDistrictStatus());
			data.setDistrictStatus(districtStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public String getStatus(String status) {
		switch (status) {
		case "1":
			status = "未填报";
			break;
		case "2":
			status = "待审核";
			break;
		case "3":
			status = "审核不通过";
			break;
		case "4":
			status = "审核通过";
			break;
		}
		return status;
	}

	/**
	 * 获取涉旅企业基本信息
	 * 
	 * @param request
	 * @param response
	 * @return 涉旅企业基本信息
	 */
	@RequestMapping(params = "getOtherTravelDetailInfo")
	@ResponseBody
	public APPOtherTravel getOtherTravelDetailInfo(HttpServletRequest request,
			HttpServletResponse response) {

		/** 设置响应头允许ajax跨域访问 **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		APPOtherTravel data = new APPOtherTravel();

		String userId = request.getParameter("userId");
		try {
			OtherTravelInfo otherTravelInfo = systemService.get(
					OtherTravelInfo.class, userId);
			data.setId(otherTravelInfo.getId());
			// 企业名称
			data.setName(otherTravelInfo.getName());
			// 负责人
			data.setPrincipal(otherTravelInfo.getPrincipal());
			// 联系电话
			data.setPhone(otherTravelInfo.getPhone());
			// 企业类型,高尔夫_1","游艇_2","空中飞行_3","机场_4,动车_5
			String type = "";
			switch (otherTravelInfo.getType() == null ? "" : otherTravelInfo
					.getType()) {
			case "1":
				type = "高尔夫";
				break;
			case "2":
				type = "游艇";
				break;
			case "3":
				type = "空中飞行";
				break;
			case "4":
				type = "机场";
				break;
			case "5":
				type = "动车";
				break;
			}
			data.setType(type);
			// 所属区,0-市辖区；1-崖州区；2-海棠区；3-天涯区；4-吉阳区
			String area = "";
			switch (otherTravelInfo.getArea() == null ? "" : otherTravelInfo
					.getArea()) {
			case "0":
				area = "市辖区";
				break;
			case "1":
				area = "崖州区";
				break;
			case "2":
				area = "海棠区";
				break;
			case "3":
				area = "天涯区";
				break;
			case "4":
				area = "吉阳区";
				break;
			}
			data.setArea(area);
			// 详细地址
			data.setAddress(otherTravelInfo.getAddress());
			// 邮政编码
			data.setPostalcode(otherTravelInfo.getPostalcode());
			// 所在省，默认海南省
			data.setPrivince(otherTravelInfo.getPrivince());
			// 所在市，默认三亚市
			data.setCity(otherTravelInfo.getCity());
			data.setTel(otherTravelInfo.getMobile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
