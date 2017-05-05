package com.zzc.web.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.zzc.poi.excel.annotation.Excel;
import com.zzc.poi.excel.annotation.ExcelEntity;
import com.zzc.poi.excel.annotation.ExcelTarget;

/**
 * 
 *                  
 * @date: 2017年1月17日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: STQuarterInOreach.java
 * @Version: 1.0
 * @About: 统计:入境旅游目的地国家外联人次统计表（按旅行社）
 *
 */
@Entity      //1
@Table(name = "t_travelAgency_quarterly1")
@SuppressWarnings("serial")
@ExcelTarget("travelQuarterIn")
public class STQuarterInOreach implements java.io.Serializable {
	private String  id;
	// 旅行社id
    private String tid;
    //指标名称
    private String  indexname;
    @Excel(name="年份",needMerge=true)
    private String year;
    @Excel(name="季度",needMerge=true)
    private String quarter;
    
    private Integer wailianPersonTime;
    private Integer jiedaiPersonTime;
    //代码
    private String  code;
    private String  reportPerson;
    private String  reportTelephone;
    private String  reportDate;
    private String  status;
    @ExcelEntity()
    private Traveldata traveldata;              //关系属性
    
    // 五大洲汇总
    private String asia;
    private String europe;
    private String africa;
    private String oceania;
    private String america;
    
    //入境游客
    private String inGuestName;
    //入境游客代码
    private Integer inGuestCode;
    //入境游客外联人次数
    private Integer inGuestOne;
    //入境游客接待人次数
    private Integer inGuestTwo;
    //入境游客外联人天数
    private Integer inGuestThree;
    //入境游客接待人天数
    private Integer inGuestFour;
    
    //香港同胞
    private String hkCom;
    //香港同胞代码
    private Integer hkComCode;
    //香港同胞外联人次数
    @Excel(name="香港",needMerge=true)
    private Integer hkComOne;
    //香港同胞接待人次数
    private Integer hkComTwo;
    //香港同胞外联人天数
    private Integer hkComThree;
    //香港同胞接待人天数
    private Integer hkComFour;
    
    //澳门同胞
    private String mcCom;
    //澳门同胞代码
    private Integer mcComCode;
    //澳门同胞外联人次数
    @Excel(name="澳门",needMerge=true)
    private Integer mcComOne;
    //澳门同胞接待人次数
    private Integer mcComTwo;
    //澳门同胞外联人天数
    private Integer mcComThree;
    //澳门同胞接待人天数
    private Integer mcComFour;
    
    //台湾同胞
    private String twCom;
    //台湾同胞代码
    private Integer twComCode;
    //台湾同胞外联人次数
    @Excel(name="台湾",needMerge=true)
    private Integer twComOne;
    //台湾同胞接待人次数
    private Integer twComTwo;
    //台湾同胞外联人天数
    private Integer twComThree;
    //台湾同胞接待人天数
    private Integer twComFour;
    
    //外国人
    private String foreigners;
    //外国人代码
    private Integer foreignersCode;
    //外国人外联人次数
    private Integer foreignersOne;
    //外国人接待人次数
    private Integer foreignersTwo;
    //外国人外联人天数
    private Integer foreignersThree;
    //外国人接待人天数
    private Integer foreignersFour;
    
    //亚洲小计
    private String asiaTotal;
    //亚洲小计代码
    private Integer asiaTotalCode;
    //亚洲小计外联人次数
    private Integer asiaTotalOne;
    //亚洲小计接待人次数
    private Integer asiaTotalTwo;
    //亚洲小计外联人天数
    private Integer asiaTotalThree;
    //亚洲小计接待人天数
    private Integer asiaTotalFour;
    
    //日本
    private String japan;
    //日本代码
    private Integer japanCode;
    //日本外联人次数
    @Excel(name="日本",needMerge=true)
    private Integer japanOne;
    //日本接待人次数
    
    private Integer japanTwo;
    //日本外联人天数
    private Integer japanThree;
    //日本接待人天数
    private Integer japanFour;
    
    //韩国
    private String korea;
    //韩国代码
    private Integer koreaCode;
    //韩国外联人次数
    @Excel(name="韩国",needMerge=true)
    private Integer koreaOne;
    //韩国接待人次数
    private Integer koreaTwo;
    //韩国外联人天数
   
    private Integer koreaThree;
    //韩国接待人天数
    private Integer koreaFour;
    
    //蒙古
    private String mongo;
    //蒙古代码
    private Integer mongoCode;
    //蒙古外联人次数
    @Excel(name="蒙古",needMerge=true)
    private Integer mongoOne;
    //蒙古接待人次数
    private Integer mongoTwo;
    //蒙古外联人天数
    private Integer mongoThree;
    //蒙古接待人天数
    private Integer mongoFour;
    
    //印度尼西亚
    private String indonxy;
    //印度尼西亚代码
    private Integer indonxyCode;
    //印度尼西亚外联人次数
    @Excel(name="印度尼西亚",needMerge=true)
    private Integer indonxyOne;
    //印度尼西亚接待人次数
    private Integer indonxyTwo;
    //印度尼西亚外联人天数
    private Integer indonxyThree;
    //印度尼西亚接待人天数
    private Integer indonxyFour;
    
    //马来西亚
    private String malaxy;
    //马来西亚代码
    private Integer malaxyCode;
    //马来西亚外联人次数
    @Excel(name="马来西亚",needMerge=true)
    private Integer malaxyOne;
    //马来西亚接待人次数
    private Integer malaxyTwo;
    //马来西亚外联人天数
    
    private Integer malaxyThree;
    //马来西亚接待人天数
    private Integer malaxyFour;
    
    //菲律宾
    private String philipn;
    //菲律宾代码
    private Integer philipnCode;
    //菲律宾外联人次数
    @Excel(name="菲律宾",needMerge=true)
    private Integer philipnOne;
    //菲律宾接待人次数
    private Integer philipnTwo;
    //菲律宾外联人天数
    private Integer philipnThree;
    //菲律宾接待人天数
    private Integer philipnFour;
    
    //新加坡
    private String singapore;
    //新加坡代码
    private Integer singaporeCode;
    //新加坡外联人次数
    @Excel(name="新加坡",needMerge=true)
    private Integer singaporeOne;
    //新加坡接待人次数
    private Integer singaporeTwo;
    //新加坡外联人天数
    private Integer singaporeThree;
    //新加坡接待人天数
    private Integer singaporeFour;
    
    //泰国
    private String tailand;
    //泰国代码
    private Integer tailandCode;
    //泰国外联人次数
    @Excel(name="泰国",needMerge=true)
    private Integer tailandOne;
    //泰国接待人次数
    private Integer tailandTwo;
    //泰国外联人天数
    private Integer tailandThree;
    //泰国接待人天数
    private Integer tailandFour;
    
    //印度
    private String india;
    //印度代码
    private Integer indiaCode;
    //印度外联人次数
    @Excel(name="印度",needMerge=true)
    private Integer indiaOne;
    private Integer indiaTwo;
    //印度接待人次数
    //印度外联人天数
    private Integer indiaThree;
    //印度接待人天数
    private Integer indiaFour;
    
    //越南
    private String vietnam;
    //越南代码
    private Integer vietnamCode;
    //越南外联人次数
    @Excel(name="越南",needMerge=true)
    private Integer vietnamOne;
    //越南接待人次数
    private Integer vietnamTwo;
    //越南外联人天数
    private Integer vietnamThree;
    //越南接待人天数
    private Integer vietnamFour;
    
    //缅甸
    private String myanamar;
    //缅甸代码
    private Integer myanamarCode;
    //缅甸外联人次数
    
    @Excel(name="缅甸",needMerge=true)
    private Integer myanamarOne;
    //缅甸接待人次数
    private Integer myanamarTwo;
    //缅甸外联人天数
    private Integer myanamarThree;
    //缅甸接待人天数
    private Integer myanamarFour;
    
    //亚洲其他
    private String otherAsian;
    //亚洲其他代码
    private Integer otherAsianCode;
    //亚洲其他外联人次数
    @Excel(name="年份",needMerge=true)
    private Integer otherAsianOne;
    //亚洲其他接待人次数
    private Integer otherAsianTwo;
    //亚洲其他外联人天数
    private Integer otherAsianThree;
    //亚洲其他接待人天数
    private Integer otherAsianFour;
    
    //欧洲小计
    private String totalEropean;
    //欧洲小计代码
    private Integer totalEropeanCode;
    //欧洲小计外联人次数
    private Integer totalEropeanOne;
    //欧洲小计接待人次数
   // @Excel(name="年份",needMerge=true)
    private Integer totalEropeanTwo;
    //欧洲小计外联人天数
    private Integer totalEropeanThree;
    //欧洲小计接待人天数
    private Integer totalEropeanFour;
    
  //英国
  private String england;
  //英国代码
  private Integer englandCode;
  //英国外联人次数
  @Excel(name="英国",needMerge=true)
  private Integer englandOne;
  //英国接待人次数
  private Integer englandTwo;
  
  //法国
  private String french;
  //法国代码
  private Integer frenchCode;
  //法国外联人次数
  @Excel(name="年份",needMerge=true)
  private Integer frenchOne;
  //法国接待人次数
  private Integer frenchTwo;
  
  //德国
  private String germany;
  //德国代码
  private Integer germanyCode;
  //德国外联人次数
  @Excel(name="法国",needMerge=true)
  private Integer germanyOne;
  //德国接待人次数
  private Integer germanyTwo;
  
  //意大利
  private String italy;
  //意大利代码
  private Integer italyCode;
  //意大利外联人次数
  @Excel(name="意大利",needMerge=true)
  private Integer italyOne;
  //意大利接待人次数
  private Integer italyTwo;
  
  //瑞士
  private String swiss;
  //瑞士代码
  private Integer swissCode;
  //瑞士外联人次数
  @Excel(name="年份",needMerge=true)
  private Integer swissOne;
  //瑞士接待人次数
  private Integer swissTwo;
  
  //瑞典
  private String swedish;
  //瑞典代码
  private Integer swedishCode;
  //瑞典外联人次数
  @Excel(name="瑞典",needMerge=true)
  private Integer swedishOne;
  //瑞典接待人次数
  private Integer swedishTwo;
  
  //俄罗斯
  private String russia;
  //俄罗斯代码
  private Integer russiaCode;
  //俄罗斯外联人次数
  @Excel(name="俄罗斯",needMerge=true)
  private Integer russiaOne;
  //俄罗斯接待人次数
  private Integer russiaTwo;
  
  //西班牙
  private String spain;
  //西班牙代码
  private Integer spainCode;
  //西班牙外联人次数
  @Excel(name="西班牙",needMerge=true)
  private Integer spainOne;
  //西班牙接待人次数
  private Integer spainTwo;
  
//荷兰
  @Excel(name="荷兰",needMerge=true)
 private Integer hollandOne;
 private Integer hollandTwo;
 private Integer hollandThree;
 private Integer hollandFour;
 
 // 丹麦
 @Excel(name="丹麦",needMerge=true)
 private Integer denmarkOne;
 private Integer denmarkTwo;
 private Integer denmarkThree;
 private Integer denmarkFour;
 
  //其他欧洲
  private String otherEuropean;
  //其他欧洲代码
  private Integer otherEuropeanCode;
  //其他欧洲外联人次数
  @Excel(name="欧洲其他",needMerge=true)
  private Integer otherEuropeanOne;
  //其他欧洲接待人次数
  private Integer otherEuropeanTwo;
  
  //美国小计
  private String totalAmerica;
  //美国小计代码
  private Integer totalAmericaCode;
  //美国小计外联人次数
  private Integer totalAmericaOne;
  //美国小计接待人次数
  
  private Integer totalAmericaTwo;
  
  //美国
  private String us;
  //美国代码
  private Integer usCode;
  //美国外联人次数
  @Excel(name="美国",needMerge=true)
  private Integer usOne;
  //美国接待人次数
  private Integer usTwo;
  
  //加拿大
  private String canada;
  //加拿大代码
  private Integer canadaCode;
  //加拿大外联人次数
  @Excel(name="加拿大",needMerge=true)
  private Integer canadaOne;
  //加拿大接待人次数
  private Integer canadaTwo;
  
  //美洲其他
  private String otherAmerican;
  //美洲其他代码
  private Integer otherAmericanCode;
  //美洲其他外联人次数
  @Excel(name="美洲其他",needMerge=true)
  private Integer otherAmericanOne;
  //美洲其他接待人次数
  private Integer otherAmericanTwo;
  
  //大洋洲小计
  private String totalOceania;
  //大洋洲小计代码
  private Integer totalOceaniaCode;
  //大洋洲小计外联人次数
  private Integer totalOceaniaOne;
  //大洋洲小计接待人次数
  private Integer totalOceaniaTwo;
  
  //澳大利亚
  private String australia;
  //澳大利亚代码
  private Integer australiaCode;
  //澳大利亚外联人次数
  @Excel(name="澳大利亚",needMerge=true)
  private Integer australiaOne;
  //澳大利亚接待人次数
  private Integer australiaTwo;
  
  //新西兰
  private String newland;
  //新西兰代码
  private Integer newlandCode;
  //新西兰外联人次数
  @Excel(name="新西兰",needMerge=true)
  private Integer newlandOne;
  //新西兰接待人次数
  private Integer newlandTwo;
  
  //大洋洲其他
  private String otherOceania;
  //大洋洲其他代码
  private Integer otherOceaniaCode;
  //大洋洲其他外联人次数
  @Excel(name="大洋洲其他",needMerge=true)
  private Integer otherOceaniaOne;
  //大洋洲其他接待人次数
  private Integer otherOceaniaTwo;
  
  //非洲小计
  private String totalAfrica;
  //非洲小计代码
  private Integer totalAfricaCode;
  //非洲小计外联人次数
  private Integer totalAfricaOne;
  //非洲小计接待人次数
  private Integer totalAfricaTwo;
  
  //其他小计
  private String totalOther;
  //其他小计代码
  private Integer totalOtherCode;
  //其他小计外联人次数
  private Integer totalOtherOne;
  //其他小计接待人次数
  private Integer totalOtherTwo;

  
  // 南非
  @Excel(name="南非",needMerge=true)
  private Integer southafricaOne;
  private Integer southafricaTwo;
  private Integer southafricaThree;
  private Integer southafricaFour;
  
  // 埃及
  @Excel(name="埃及",needMerge=true)
  private Integer egyptOne;
  private Integer egyptTwo;
  private Integer egyptThree;
  private Integer egyptFour;
  
  // 肯尼亚
  @Excel(name="肯尼亚",needMerge=true)
  private Integer kenyaOne;
  private Integer kenyaTwo;
  private Integer kenyaThree;
  private Integer kenyaFour;
  
  // 非洲其他
  @Excel(name="非洲其他",needMerge=true)
  private Integer africaotherOne;
  private Integer africaotherTwo;
  private Integer africaotherThree;
  private Integer africaotherFour;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="id",nullable=false,length=50)	
	public String getId() {
		return id;
	}	
	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="in_guest_name",nullable=true,length=20)
	public String getInGuestName() {
		return inGuestName;
	}
	public void setInGuestName(String inGuestName) {
		this.inGuestName = inGuestName;
	}
	@Column(name ="in_guest_code",nullable=true,length=50)
	public Integer getInGuestCode() {
		return inGuestCode;
	}
	public void setInGuestCode(Integer inGuestCode) {
		this.inGuestCode = inGuestCode;
	}
	@Column(name ="in_guest_one",nullable=true,length=50)
	public Integer getInGuestOne() {
		return inGuestOne;
	}
	public void setInGuestOne(Integer inGuestOne) {
		this.inGuestOne = inGuestOne;
	}
	@Column(name ="in_guest_two",nullable=true,length=50)
	public Integer getInGuestTwo() {
		return inGuestTwo;
	}
	public void setInGuestTwo(Integer inGuestTwo) {
		this.inGuestTwo = inGuestTwo;
	}  
	@Column(name ="in_guest_three",nullable=true,length=50)
	public Integer getInGuestThree() {
		return inGuestThree;
	}
	public void setInGuestThree(Integer inGuestThree) {
		this.inGuestThree = inGuestThree;
	}   
	@Column(name ="in_guest_four",nullable=true,length=50)
	public Integer getInGuestFour() {
		return inGuestFour;
	}
	public void setInGuestFour(Integer inGuestFour) {
		this.inGuestFour = inGuestFour;
	}
	@Column(name ="hk_com",nullable=true,length=20)
	public String getHkCom() {
		return hkCom;
	}
	public void setHkCom(String hkCom) {
		this.hkCom = hkCom;
	}
	@Column(name ="hk_com_code",nullable=true,length=50)
	public Integer getHkComCode() {
		return hkComCode;
	}
	public void setHkComCode(Integer hkComCode) {
		this.hkComCode = hkComCode;
	}
	@Column(name ="hk_com_one",nullable=true,length=50)
	public Integer getHkComOne() {
		return hkComOne;
	}
	public void setHkComOne(Integer hkComOne) {
		this.hkComOne = hkComOne;
	}
	@Column(name ="hk_com_two",nullable=true,length=50)
	public Integer getHkComTwo() {
		return hkComTwo;
	}
	public void setHkComTwo(Integer hkComTwo) {
		this.hkComTwo = hkComTwo;
	}
	@Column(name ="hk_com_three",nullable=true,length=50)
	public Integer getHkComThree() {
		return hkComThree;
	}
	public void setHkComThree(Integer hkComThree) {
		this.hkComThree = hkComThree;
	}
	@Column(name ="hk_com_four",nullable=true,length=50)
	public Integer getHkComFour() {
		return hkComFour;
	}
	public void setHkComFour(Integer hkComFour) {
		this.hkComFour = hkComFour;
	}
	@Column(name ="mc_com",nullable=true,length=20)
	public String getMcCom() {
		return mcCom;
	}
	public void setMcCom(String mcCom) {
		this.mcCom = mcCom;
	}
	@Column(name ="mc_com_code",nullable=true,length=50)
	public Integer getMcComCode() {
		return mcComCode;
	}
	public void setMcComCode(Integer mcComCode) {
		this.mcComCode = mcComCode;
	}
	@Column(name ="mc_com_one",nullable=true,length=50)
	public Integer getMcComOne() {
		return mcComOne;
	}
	public void setMcComOne(Integer mcComOne) {
		this.mcComOne = mcComOne;
	}
	@Column(name ="mc_com_two",nullable=true,length=50)
	public Integer getMcComTwo() {
		return mcComTwo;
	}
	public void setMcComTwo(Integer mcComTwo) {
		this.mcComTwo = mcComTwo;
	}
	@Column(name ="mc_com_three",nullable=true,length=50)
	public Integer getMcComThree() {
		return mcComThree;
	}
	public void setMcComThree(Integer mcComThree) {
		this.mcComThree = mcComThree;
	}
	@Column(name ="mc_com_four",nullable=true,length=50)
	public Integer getMcComFour() {
		return mcComFour;
	}
	public void setMcComFour(Integer mcComFour) {
		this.mcComFour = mcComFour;
	}
	@Column(name ="tw_com",nullable=true,length=20)
	public String getTwCom() {
		return twCom;
	}
	public void setTwCom(String twCom) {
		this.twCom = twCom;
	}
	@Column(name ="tw_com_code",nullable=true,length=50)
	public Integer getTwComCode() {
		return twComCode;
	}
	public void setTwComCode(Integer twComCode) {
		this.twComCode = twComCode;
	}
	@Column(name ="tw_com_one",nullable=true,length=50)
	public Integer getTwComOne() {
		return twComOne;
	}
	public void setTwComOne(Integer twComOne) {
		this.twComOne = twComOne;
	}
	@Column(name ="tw_com_two",nullable=true,length=50)
	public Integer getTwComTwo() {
		return twComTwo;
	}
	public void setTwComTwo(Integer twComTwo) {
		this.twComTwo = twComTwo;
	}
	@Column(name ="tw_com_three",nullable=true,length=50)
	public Integer getTwComThree() {
		return twComThree;
	}
	public void setTwComThree(Integer twComThree) {
		this.twComThree = twComThree;
	}
	@Column(name ="tw_com_four",nullable=true,length=50)
	public Integer getTwComFour() {
		return twComFour;
	}
	public void setTwComFour(Integer twComFour) {
		this.twComFour = twComFour;
	}
	@Column(name ="foreigners",nullable=true,length=20)
	public String getForeigners() {
		return foreigners;
	}
	public void setForeigners(String foreigners) {
		this.foreigners = foreigners;
	}
	@Column(name ="foreigners_code",nullable=true,length=50)
	public Integer getForeignersCode() {
		return foreignersCode;
	}
	public void setForeignersCode(Integer foreignersCode) {
		this.foreignersCode = foreignersCode;
	}
	@Column(name ="foreigners_one",nullable=true,length=50)
	public Integer getForeignersOne() {
		return foreignersOne;
	}
	public void setForeignersOne(Integer foreignersOne) {
		this.foreignersOne = foreignersOne;
	}
	@Column(name ="foreigners_two",nullable=true,length=50)
	public Integer getForeignersTwo() {
		return foreignersTwo;
	}
	public void setForeignersTwo(Integer foreignersTwo) {
		this.foreignersTwo = foreignersTwo;
	}
	@Column(name ="foreigners_three",nullable=true,length=50)
	public Integer getForeignersThree() {
		return foreignersThree;
	}
	public void setForeignersThree(Integer foreignersThree) {
		this.foreignersThree = foreignersThree;
	}
	@Column(name ="foreigners_four",nullable=true,length=50)
	public Integer getForeignersFour() {
		return foreignersFour;
	}
	public void setForeignersFour(Integer foreignersFour) {
		this.foreignersFour = foreignersFour;
	}
	@Column(name ="asiatotal",nullable=true,length=20)
	public String getAsiaTotal() {
		return asiaTotal;
	}
	public void setAsiaTotal(String asiaTotal) {
		this.asiaTotal = asiaTotal;
	}
	@Column(name ="asiatotal_code",nullable=true,length=50)
	public Integer getAsiaTotalCode() {
		return asiaTotalCode;
	}
	public void setAsiaTotalCode(Integer asiaTotalCode) {
		this.asiaTotalCode = asiaTotalCode;
	}
	@Column(name ="asiatotal_one",nullable=true,length=50)
	public Integer getAsiaTotalOne() {
		return asiaTotalOne;
	}
	public void setAsiaTotalOne(Integer asiaTotalOne) {
		this.asiaTotalOne = asiaTotalOne;
	}
	@Column(name ="asiatotal_two",nullable=true,length=50)
	public Integer getAsiaTotalTwo() {
		return asiaTotalTwo;
	}
	public void setAsiaTotalTwo(Integer asiaTotalTwo) {
		this.asiaTotalTwo = asiaTotalTwo;
	}
	@Column(name ="asiatotal_three",nullable=true,length=50)
	public Integer getAsiaTotalThree() {
		return asiaTotalThree;
	}
	public void setAsiaTotalThree(Integer asiaTotalThree) {
		this.asiaTotalThree = asiaTotalThree;
	}
	@Column(name ="asiatotal_four",nullable=true,length=50)
	public Integer getAsiaTotalFour() {
		return asiaTotalFour;
	}
	public void setAsiaTotalFour(Integer asiaTotalFour) {
		this.asiaTotalFour = asiaTotalFour;
	}
	@Column(name ="japan",nullable=true,length=20)
	public String getJapan() {
		return japan;
	}
	public void setJapan(String japan) {
		this.japan = japan;
	}
	@Column(name ="japan_code",nullable=true,length=50)
	public Integer getJapanCode() {
		return japanCode;
	}
	public void setJapanCode(Integer japanCode) {
		this.japanCode = japanCode;
	}
	@Column(name ="japan_one",nullable=true,length=50)
	public Integer getJapanOne() {
		return japanOne;
	}
	public void setJapanOne(Integer japanOne) {
		this.japanOne = japanOne;
	}
	@Column(name ="japan_two",nullable=true,length=50)
	public Integer getJapanTwo() {
		return japanTwo;
	}
	public void setJapanTwo(Integer japanTwo) {
		this.japanTwo = japanTwo;
	}
	@Column(name ="japan_three",nullable=true,length=50)
	public Integer getJapanThree() {
		return japanThree;
	}
	public void setJapanThree(Integer japanThree) {
		this.japanThree = japanThree;
	}
	@Column(name ="japan_four",nullable=true,length=50)
	public Integer getJapanFour() {
		return japanFour;
	}
	public void setJapanFour(Integer japanFour) {
		this.japanFour = japanFour;
	}
	@Column(name ="korea",nullable=true,length=20)
	public String getKorea() {
		return korea;
	}
	public void setKorea(String korea) {
		this.korea = korea;
	}
	@Column(name ="korea_code",nullable=true,length=50)
	public Integer getKoreaCode() {
		return koreaCode;
	}
	public void setKoreaCode(Integer koreaCode) {
		this.koreaCode = koreaCode;
	}
	@Column(name ="korea_one",nullable=true,length=50)
	public Integer getKoreaOne() {
		return koreaOne;
	}
	public void setKoreaOne(Integer koreaOne) {
		this.koreaOne = koreaOne;
	}
	@Column(name ="korea_two",nullable=true,length=50)
	public Integer getKoreaTwo() {
		return koreaTwo;
	}
	public void setKoreaTwo(Integer koreaTwo) {
		this.koreaTwo = koreaTwo;
	}
	@Column(name ="korea_three",nullable=true,length=50)
	public Integer getKoreaThree() {
		return koreaThree;
	}
	public void setKoreaThree(Integer koreaThree) {
		this.koreaThree = koreaThree;
	}
	@Column(name ="korea_four",nullable=true,length=50)
	public Integer getKoreaFour() {
		return koreaFour;
	}
	public void setKoreaFour(Integer koreaFour) {
		this.koreaFour = koreaFour;
	}
	@Column(name ="mongo",nullable=true,length=20)
	public String getMongo() {
		return mongo;
	}
	public void setMongo(String mongo) {
		this.mongo = mongo;
	}
	@Column(name ="mongo_code",nullable=true,length=50)
	public Integer getMongoCode() {
		return mongoCode;
	}
	public void setMongoCode(Integer mongoCode) {
		this.mongoCode = mongoCode;
	}
	@Column(name ="mongo_one",nullable=true,length=50)
	public Integer getMongoOne() {
		return mongoOne;
	}
	public void setMongoOne(Integer mongoOne) {
		this.mongoOne = mongoOne;
	}
	@Column(name ="mongo_two",nullable=true,length=50)
	public Integer getMongoTwo() {
		return mongoTwo;
	}
	public void setMongoTwo(Integer mongoTwo) {
		this.mongoTwo = mongoTwo;
	}
	@Column(name ="mongo_three",nullable=true,length=50)
	public Integer getMongoThree() {
		return mongoThree;
	}
	public void setMongoThree(Integer mongoThree) {
		this.mongoThree = mongoThree;
	}
	@Column(name ="mongo_four",nullable=true,length=50)
	public Integer getMongoFour() {
		return mongoFour;
	}
	public void setMongoFour(Integer mongoFour) {
		this.mongoFour = mongoFour;
	}
	@Column(name ="indonxy",nullable=true,length=20)
	public String getIndonxy() {
		return indonxy;
	}
	public void setIndonxy(String indonxy) {
		this.indonxy = indonxy;
	}
	@Column(name ="indonxy_code",nullable=true,length=50)
	public Integer getIndonxyCode() {
		return indonxyCode;
	}
	public void setIndonxyCode(Integer indonxyCode) {
		this.indonxyCode = indonxyCode;
	}
	@Column(name ="indonxy_one",nullable=true,length=50)
	public Integer getIndonxyOne() {
		return indonxyOne;
	}
	public void setIndonxyOne(Integer indonxyOne) {
		this.indonxyOne = indonxyOne;
	}
	@Column(name ="indonxy_two",nullable=true,length=50)
	public Integer getIndonxyTwo() {
		return indonxyTwo;
	}
	public void setIndonxyTwo(Integer indonxyTwo) {
		this.indonxyTwo = indonxyTwo;
	}
	@Column(name ="indonxy_three",nullable=true,length=50)
	public Integer getIndonxyThree() {
		return indonxyThree;
	}
	public void setIndonxyThree(Integer indonxyThree) {
		this.indonxyThree = indonxyThree;
	}
	@Column(name ="indonxy_four",nullable=true,length=50)
	public Integer getIndonxyFour() {
		return indonxyFour;
	}
	public void setIndonxyFour(Integer indonxyFour) {
		this.indonxyFour = indonxyFour;
	}
	@Column(name ="malaxy",nullable=true,length=20)
	public String getMalaxy() {
		return malaxy;
	}
	public void setMalaxy(String malaxy) {
		this.malaxy = malaxy;
	}
	@Column(name ="malaxy_code",nullable=true,length=50)
	public Integer getMalaxyCode() {
		return malaxyCode;
	}
	public void setMalaxyCode(Integer malaxyCode) {
		this.malaxyCode = malaxyCode;
	}
	@Column(name ="malaxy_one",nullable=true,length=50)
	public Integer getMalaxyOne() {
		return malaxyOne;
	}
	public void setMalaxyOne(Integer malaxyOne) {
		this.malaxyOne = malaxyOne;
	}
	@Column(name ="malaxy_two",nullable=true,length=50)
	public Integer getMalaxyTwo() {
		return malaxyTwo;
	}
	public void setMalaxyTwo(Integer malaxyTwo) {
		this.malaxyTwo = malaxyTwo;
	}
	@Column(name ="malaxy_three",nullable=true,length=50)
	public Integer getMalaxyThree() {
		return malaxyThree;
	}
	public void setMalaxyThree(Integer malaxyThree) {
		this.malaxyThree = malaxyThree;
	}
	@Column(name ="malaxy_four",nullable=true,length=50)
	public Integer getMalaxyFour() {
		return malaxyFour;
	}
	public void setMalaxyFour(Integer malaxyFour) {
		this.malaxyFour = malaxyFour;
	}
	@Column(name ="philipn",nullable=true,length=20)
	public String getPhilipn() {
		return philipn;
	}
	public void setPhilipn(String philipn) {
		this.philipn = philipn;
	}
	@Column(name ="philipn_code",nullable=true,length=50)
	public Integer getPhilipnCode() {
		return philipnCode;
	}
	public void setPhilipnCode(Integer philipnCode) {
		this.philipnCode = philipnCode;
	}
	@Column(name ="philipn_one",nullable=true,length=50)
	public Integer getPhilipnOne() {
		return philipnOne;
	}
	public void setPhilipnOne(Integer philipnOne) {
		this.philipnOne = philipnOne;
	}
	@Column(name ="philipn_two",nullable=true,length=50)
	public Integer getPhilipnTwo() {
		return philipnTwo;
	}
	public void setPhilipnTwo(Integer philipnTwo) {
		this.philipnTwo = philipnTwo;
	}
	@Column(name ="philipn_three",nullable=true,length=50)
	public Integer getPhilipnThree() {
		return philipnThree;
	}
	public void setPhilipnThree(Integer philipnThree) {
		this.philipnThree = philipnThree;
	}
	@Column(name ="philipn_four",nullable=true,length=50)
	public Integer getPhilipnFour() {
		return philipnFour;
	}
	public void setPhilipnFour(Integer philipnFour) {
		this.philipnFour = philipnFour;
	}
	@Column(name ="singapore",nullable=true,length=20)
	public String getSingapore() {
		return singapore;
	}
	public void setSingapore(String singapore) {
		this.singapore = singapore;
	}
	@Column(name ="singapore_code",nullable=true,length=50)
	public Integer getSingaporeCode() {
		return singaporeCode;
	}
	public void setSingaporeCode(Integer singaporeCode) {
		this.singaporeCode = singaporeCode;
	}
	@Column(name ="singapore_one",nullable=true,length=50)
	public Integer getSingaporeOne() {
		return singaporeOne;
	}
	public void setSingaporeOne(Integer singaporeOne) {
		this.singaporeOne = singaporeOne;
	}
	@Column(name ="singapore_two",nullable=true,length=50)

	public Integer getSingaporeTwo() {
		return singaporeTwo;
	}
	public void setSingaporeTwo(Integer singaporeTwo) {
		this.singaporeTwo = singaporeTwo;
	}
	@Column(name ="singapore_three",nullable=true,length=50)
	public Integer getSingaporeThree() {
		return singaporeThree;
	}
	public void setSingaporeThree(Integer singaporeThree) {
		this.singaporeThree = singaporeThree;
	}
	@Column(name ="singapore_four",nullable=true,length=50)
	public Integer getSingaporeFour() {
		return singaporeFour;
	}
	public void setSingaporeFour(Integer singaporeFour) {
		this.singaporeFour = singaporeFour;
	}
	@Column(name ="tailand",nullable=true,length=20)
	public String getTailand() {
		return tailand;
	}
	public void setTailand(String tailand) {
		this.tailand = tailand;
	}
	@Column(name ="tailand_code",nullable=true,length=50)
	public Integer getTailandCode() {
		return tailandCode;
	}
	public void setTailandCode(Integer tailandCode) {
		this.tailandCode = tailandCode;
	}
	@Column(name ="tailand_one",nullable=true,length=50)
	public Integer getTailandOne() {
		return tailandOne;
	}
	public void setTailandOne(Integer tailandOne) {
		this.tailandOne = tailandOne;
	}
	@Column(name ="tailand_two",nullable=true,length=50)
	public Integer getTailandTwo() {
		return tailandTwo;
	}
	public void setTailandTwo(Integer tailandTwo) {
		this.tailandTwo = tailandTwo;
	}
	@Column(name ="tailand_three",nullable=true,length=50)
	public Integer getTailandThree() {
		return tailandThree;
	}
	public void setTailandThree(Integer tailandThree) {
		this.tailandThree = tailandThree;
	}
	@Column(name ="tailand_four",nullable=true,length=50)
	public Integer getTailandFour() {
		return tailandFour;
	}
	public void setTailandFour(Integer tailandFour) {
		this.tailandFour = tailandFour;
	}
	@Column(name ="india",nullable=true,length=20)
	public String getIndia() {
		return india;
	}
	public void setIndia(String india) {
		this.india = india;
	}
	@Column(name ="india_code",nullable=true,length=50)
	public Integer getIndiaCode() {
		return indiaCode;
	}
	public void setIndiaCode(Integer indiaCode) {
		this.indiaCode = indiaCode;
	}
	@Column(name ="india_one",nullable=true,length=50)
	public Integer getIndiaOne() {
		return indiaOne;
	}
	public void setIndiaOne(Integer indiaOne) {
		this.indiaOne = indiaOne;
	}
	@Column(name ="india_two",nullable=true,length=50)
	public Integer getIndiaTwo() {
		return indiaTwo;
	}
	public void setIndiaTwo(Integer indiaTwo) {
		this.indiaTwo = indiaTwo;
	}
	@Column(name ="india_three",nullable=true,length=50)
	public Integer getIndiaThree() {
		return indiaThree;
	}
	public void setIndiaThree(Integer indiaThree) {
		this.indiaThree = indiaThree;
	}
	@Column(name ="india_four",nullable=true,length=50)
	public Integer getIndiaFour() {
		return indiaFour;
	}
	public void setIndiaFour(Integer indiaFour) {
		this.indiaFour = indiaFour;
	}
	@Column(name ="vietnam",nullable=true,length=20)
	public String getVietnam() {
		return vietnam;
	}
	public void setVietnam(String vietnam) {
		this.vietnam = vietnam;
	}
	@Column(name ="vietnam_code",nullable=true,length=50)
	public Integer getVietnamCode() {
		return vietnamCode;
	}
	public void setVietnamCode(Integer vietnamCode) {
		this.vietnamCode = vietnamCode;
	}
	@Column(name ="vietnam_one",nullable=true,length=50)
	public Integer getVietnamOne() {
		return vietnamOne;
	}
	public void setVietnamOne(Integer vietnamOne) {
		this.vietnamOne = vietnamOne;
	}
	@Column(name ="vietnam_two",nullable=true,length=50)
	public Integer getVietnamTwo() {
		return vietnamTwo;
	}
	public void setVietnamTwo(Integer vietnamTwo) {
		this.vietnamTwo = vietnamTwo;
	}
	@Column(name ="vietnam_three",nullable=true,length=50)
	public Integer getVietnamThree() {
		return vietnamThree;
	}
	public void setVietnamThree(Integer vietnamThree) {
		this.vietnamThree = vietnamThree;
	}
	@Column(name ="vietnam_four",nullable=true,length=50)
	public Integer getVietnamFour() {
		return vietnamFour;
	}
	public void setVietnamFour(Integer vietnamFour) {
		this.vietnamFour = vietnamFour;
	}
	@Column(name ="myanmar",nullable=true,length=20)
	public String getMyanamar() {
		return myanamar;
	}
	public void setMyanamar(String myanamar) {
		this.myanamar = myanamar;
	}
	@Column(name ="myanmar_code",nullable=true,length=50)
	public Integer getMyanamarCode() {
		return myanamarCode;
	}
	public void setMyanamarCode(Integer myanamarCode) {
		this.myanamarCode = myanamarCode;
	}
	@Column(name ="myanmar_one",nullable=true,length=50)
	public Integer getMyanamarOne() {
		return myanamarOne;
	}
	public void setMyanamarOne(Integer myanamarOne) {
		this.myanamarOne = myanamarOne;
	}
	@Column(name ="myanmar_two",nullable=true,length=50)
	public Integer getMyanamarTwo() {
		return myanamarTwo;
	}
	public void setMyanamarTwo(Integer myanamarTwo) {
		this.myanamarTwo = myanamarTwo;
	}
	@Column(name ="myanmar_three",nullable=true,length=50)
	public Integer getMyanamarThree() {
		return myanamarThree;
	}
	public void setMyanamarThree(Integer myanamarThree) {
		this.myanamarThree = myanamarThree;
	}
	@Column(name ="myanmar_four",nullable=true,length=50)
	public Integer getMyanamarFour() {
		return myanamarFour;
	}
	public void setMyanamarFour(Integer myanamarFour) {
		this.myanamarFour = myanamarFour;
	}
	@Column(name ="other_asian",nullable=true,length=20)
	public String getOtherAsian() {
		return otherAsian;
	}
	public void setOtherAsian(String otherAsian) {
		this.otherAsian = otherAsian;
	}
	@Column(name ="other_asian_code",nullable=true,length=50)
	public Integer getOtherAsianCode() {
		return otherAsianCode;
	}
	public void setOtherAsianCode(Integer otherAsianCode) {
		this.otherAsianCode = otherAsianCode;
	}
	@Column(name ="other_asian_one",nullable=true,length=50)
	public Integer getOtherAsianOne() {
		return otherAsianOne;
	}
	public void setOtherAsianOne(Integer otherAsianOne) {
		this.otherAsianOne = otherAsianOne;
	}
	@Column(name ="other_asian_two",nullable=true,length=50)
	public Integer getOtherAsianTwo() {
		return otherAsianTwo;
	}
	public void setOtherAsianTwo(Integer otherAsianTwo) {
		this.otherAsianTwo = otherAsianTwo;
	}
	@Column(name ="other_asian_three",nullable=true,length=50)
	public Integer getOtherAsianThree() {
		return otherAsianThree;
	}
	public void setOtherAsianThree(Integer otherAsianThree) {
		this.otherAsianThree = otherAsianThree;
	}
	@Column(name ="other_asian_four",nullable=true,length=50)
	public Integer getOtherAsianFour() {
		return otherAsianFour;
	}
	public void setOtherAsianFour(Integer otherAsianFour) {
		this.otherAsianFour = otherAsianFour;
	}
	@Column(name ="other_eropean",nullable=true,length=20)
	public String getTotalEropean() {
		return totalEropean;
	}
	public void setTotalEropean(String totalEropean) {
		this.totalEropean = totalEropean;
	}
	@Column(name ="other_eropean_code",nullable=true,length=50)
	public Integer getTotalEropeanCode() {
		return totalEropeanCode;
	}
	public void setTotalEropeanCode(Integer totalEropeanCode) {
		this.totalEropeanCode = totalEropeanCode;
	}
	@Column(name ="other_eropean_one",nullable=true,length=50)
	public Integer getTotalEropeanOne() {
		return totalEropeanOne;
	}
	public void setTotalEropeanOne(Integer totalEropeanOne) {
		this.totalEropeanOne = totalEropeanOne;
	}
	@Column(name ="other_eropean_two",nullable=true,length=50)
	public Integer getTotalEropeanTwo() {
		return totalEropeanTwo;
	}
	public void setTotalEropeanTwo(Integer totalEropeanTwo) {
		this.totalEropeanTwo = totalEropeanTwo;
	}
	@Column(name ="other_eropean_three",nullable=true,length=50)
	public Integer getTotalEropeanThree() {
		return totalEropeanThree;
	}
	public void setTotalEropeanThree(Integer totalEropeanThree) {
		this.totalEropeanThree = totalEropeanThree;
	}
	@Column(name ="other_eropean_four",nullable=true,length=50)
	public Integer getTotalEropeanFour() {
		return totalEropeanFour;
	}
	public void setTotalEropeanFour(Integer totalEropeanFour) {
		this.totalEropeanFour = totalEropeanFour;
	}
	@Column(name ="england",nullable=true,length=20)
	public String getEngland() {
		return england;
	}
	public void setEngland(String england) {
		this.england = england;
	}
	@Column(name ="england_code",nullable=true,length=50)
	public Integer getEnglandCode() {
		return englandCode;
	}
	public void setEnglandCode(Integer englandCode) {
		this.englandCode = englandCode;
	}
	@Column(name ="england_one",nullable=true,length=50)
	public Integer getEnglandOne() {
		return englandOne;
	}
	public void setEnglandOne(Integer englandOne) {
		this.englandOne = englandOne;
	}
	@Column(name ="england_two",nullable=true,length=50)
	public Integer getEnglandTwo() {
		return englandTwo;
	}
	public void setEnglandTwo(Integer englandTwo) {
		this.englandTwo = englandTwo;
	}
	@Column(name ="french",nullable=true,length=20)
	public String getFrench() {
		return french;
	}
	public void setFrench(String french) {
		this.french = french;
	}
	@Column(name ="french_code",nullable=true,length=50)
	public Integer getFrenchCode() {
		return frenchCode;
	}
	public void setFrenchCode(Integer frenchCode) {
		this.frenchCode = frenchCode;
	}
	@Column(name ="french_one",nullable=true,length=50)
	public Integer getFrenchOne() {
		return frenchOne;
	}
	public void setFrenchOne(Integer frenchOne) {
		this.frenchOne = frenchOne;
	}
	@Column(name ="french_two",nullable=true,length=50)
	public Integer getFrenchTwo() {
		return frenchTwo;
	}
	public void setFrenchTwo(Integer frenchTwo) {
		this.frenchTwo = frenchTwo;
	}
	@Column(name ="germany",nullable=true,length=20)
	public String getGermany() {
		return germany;
	}
	public void setGermany(String germany) {
		this.germany = germany;
	}
	@Column(name ="germany_code",nullable=true,length=50)
	public Integer getGermanyCode() {
		return germanyCode;
	}
	public void setGermanyCode(Integer germanyCode) {
		this.germanyCode = germanyCode;
	}
	@Column(name ="germany_one",nullable=true,length=50)
	public Integer getGermanyOne() {
		return germanyOne;
	}
	public void setGermanyOne(Integer germanyOne) {
		this.germanyOne = germanyOne;
	}
	@Column(name ="germany_two",nullable=true,length=50)
	public Integer getGermanyTwo() {
		return germanyTwo;
	}
	public void setGermanyTwo(Integer germanyTwo) {
		this.germanyTwo = germanyTwo;
	}
	@Column(name ="italy",nullable=true,length=20)
	public String getItaly() {
		return italy;
	}
	public void setItaly(String italy) {
		this.italy = italy;
	}
	@Column(name ="italy_code",nullable=true,length=50)
	public Integer getItalyCode() {
		return italyCode;
	}
	public void setItalyCode(Integer italyCode) {
		this.italyCode = italyCode;
	}
	@Column(name ="italy_one",nullable=true,length=50)
	public Integer getItalyOne() {
		return italyOne;
	}
	public void setItalyOne(Integer italyOne) {
		this.italyOne = italyOne;
	}
	@Column(name ="italy_two",nullable=true,length=50)
	public Integer getItalyTwo() {
		return italyTwo;
	}
	public void setItalyTwo(Integer italyTwo) {
		this.italyTwo = italyTwo;
	}
	@Column(name ="swiss",nullable=true,length=20)
	public String getSwiss() {
		return swiss;
	}
	public void setSwiss(String swiss) {
		this.swiss = swiss;
	}
	@Column(name ="swiss_code",nullable=true,length=50)
	public Integer getSwissCode() {
		return swissCode;
	}
	public void setSwissCode(Integer swissCode) {
		this.swissCode = swissCode;
	}
	@Column(name ="swiss_one",nullable=true,length=50)
	public Integer getSwissOne() {
		return swissOne;
	}
	public void setSwissOne(Integer swissOne) {
		this.swissOne = swissOne;
	}
	@Column(name ="swiss_two",nullable=true,length=50)
	public Integer getSwissTwo() {
		return swissTwo;
	}
	public void setSwissTwo(Integer swissTwo) {
		this.swissTwo = swissTwo;
	}
	@Column(name ="swedish",nullable=true,length=20)
	public String getSwedish() {
		return swedish;
	}
	public void setSwedish(String swedish) {
		this.swedish = swedish;
	}
	@Column(name ="swedish_code",nullable=true,length=50)
	public Integer getSwedishCode() {
		return swedishCode;
	}
	public void setSwedishCode(Integer swedishCode) {
		this.swedishCode = swedishCode;
	}
	@Column(name ="swedish_one",nullable=true,length=50)
	public Integer getSwedishOne() {
		return swedishOne;
	}
	public void setSwedishOne(Integer swedishOne) {
		this.swedishOne = swedishOne;
	}
	@Column(name ="swedish_two",nullable=true,length=50)
	public Integer getSwedishTwo() {
		return swedishTwo;
	}
	public void setSwedishTwo(Integer swedishTwo) {
		this.swedishTwo = swedishTwo;
	}
	@Column(name ="russia",nullable=true,length=20)
	public String getRussia() {
		return russia;
	}
	public void setRussia(String russia) {
		this.russia = russia;
	}
	@Column(name ="russia_code",nullable=true,length=50)
	public Integer getRussiaCode() {
		return russiaCode;
	}
	public void setRussiaCode(Integer russiaCode) {
		this.russiaCode = russiaCode;
	}
	@Column(name ="russia_one",nullable=true,length=50)
	public Integer getRussiaOne() {
		return russiaOne;
	}
	public void setRussiaOne(Integer russiaOne) {
		this.russiaOne = russiaOne;
	}
	@Column(name ="russia_two",nullable=true,length=50)
	public Integer getRussiaTwo() {
		return russiaTwo;
	}
	public void setRussiaTwo(Integer russiaTwo) {
		this.russiaTwo = russiaTwo;
	}
	@Column(name ="spain",nullable=true,length=20)
	public String getSpain() {
		return spain;
	}
	public void setSpain(String spain) {
		this.spain = spain;
	}
	@Column(name ="spain_code",nullable=true,length=50)
	public Integer getSpainCode() {
		return spainCode;
	}
	public void setSpainCode(Integer spainCode) {
		this.spainCode = spainCode;
	}
	@Column(name ="spain_one",nullable=true,length=50)
	public Integer getSpainOne() {
		return spainOne;
	}
	public void setSpainOne(Integer spainOne) {
		this.spainOne = spainOne;
	}
	@Column(name ="spain_two",nullable=true,length=50)
	public Integer getSpainTwo() {
		return spainTwo;
	}
	public void setSpainTwo(Integer spainTwo) {
		this.spainTwo = spainTwo;
	}
	@Column(name ="other_european",nullable=true,length=20)
	public String getOtherEuropean() {
		return otherEuropean;
	}
	public void setOtherEuropean(String otherEuropean) {
		this.otherEuropean = otherEuropean;
	}
	@Column(name ="other_european_code",nullable=true,length=50)
	public Integer getOtherEuropeanCode() {
		return otherEuropeanCode;
	}
	public void setOtherEuropeanCode(Integer otherEuropeanCode) {
		this.otherEuropeanCode = otherEuropeanCode;
	}
	@Column(name ="other_european_one",nullable=true,length=50)
	public Integer getOtherEuropeanOne() {
		return otherEuropeanOne;
	}
	public void setOtherEuropeanOne(Integer otherEuropeanOne) {
		this.otherEuropeanOne = otherEuropeanOne;
	}
	@Column(name ="other_european_two",nullable=true,length=50)
	public Integer getOtherEuropeanTwo() {
		return otherEuropeanTwo;
	}
	public void setOtherEuropeanTwo(Integer otherEuropeanTwo) {
		this.otherEuropeanTwo = otherEuropeanTwo;
	}
	@Column(name ="total_america",nullable=true,length=20)
	public String getTotalAmerica() {
		return totalAmerica;
	}
	public void setTotalAmerica(String totalAmerica) {
		this.totalAmerica = totalAmerica;
	}
	@Column(name ="total_america_code",nullable=true,length=50)
	public Integer getTotalAmericaCode() {
		return totalAmericaCode;
	}
	public void setTotalAmericaCode(Integer totalAmericaCode) {
		this.totalAmericaCode = totalAmericaCode;
	}
	@Column(name ="total_america_one",nullable=true,length=50)
	public Integer getTotalAmericaOne() {
		return totalAmericaOne;
	}
	public void setTotalAmericaOne(Integer totalAmericaOne) {
		this.totalAmericaOne = totalAmericaOne;
	}
	@Column(name ="total_america_two",nullable=true,length=50)
	public Integer getTotalAmericaTwo() {
		return totalAmericaTwo;
	}
	public void setTotalAmericaTwo(Integer totalAmericaTwo) {
		this.totalAmericaTwo = totalAmericaTwo;
	}
	@Column(name ="us",nullable=true,length=20)
	public String getUs() {
		return us;
	}
	public void setUs(String us) {
		this.us = us;
	}
	@Column(name ="us_code",nullable=true,length=50)
	public Integer getUsCode() {
		return usCode;
	}
	public void setUsCode(Integer usCode) {
		this.usCode = usCode;
	}
	@Column(name ="us_one",nullable=true,length=50)
	public Integer getUsOne() {
		return usOne;
	}
	public void setUsOne(Integer usOne) {
		this.usOne = usOne;
	}
	@Column(name ="us_two",nullable=true,length=50)
	public Integer getUsTwo() {
		return usTwo;
	}
	public void setUsTwo(Integer usTwo) {
		this.usTwo = usTwo;
	}
	@Column(name ="canada",nullable=true,length=20)
	public String getCanada() {
		return canada;
	}
	public void setCanada(String canada) {
		this.canada = canada;
	}
	@Column(name ="canada_code",nullable=true,length=50)
	public Integer getCanadaCode() {
		return canadaCode;
	}
	public void setCanadaCode(Integer canadaCode) {
		this.canadaCode = canadaCode;
	}
	@Column(name ="canada_one",nullable=true,length=50)
	public Integer getCanadaOne() {
		return canadaOne;
	}
	public void setCanadaOne(Integer canadaOne) {
		this.canadaOne = canadaOne;
	}
	@Column(name ="canada_two",nullable=true,length=50)
	public Integer getCanadaTwo() {
		return canadaTwo;
	}
	public void setCanadaTwo(Integer canadaTwo) {
		this.canadaTwo = canadaTwo;
	}
	@Column(name ="other_american",nullable=true,length=20)
	public String getOtherAmerican() {
		return otherAmerican;
	}
	public void setOtherAmerican(String otherAmerican) {
		this.otherAmerican = otherAmerican;
	}
	@Column(name ="other_american_code",nullable=true,length=50)
	public Integer getOtherAmericanCode() {
		return otherAmericanCode;
	}
	public void setOtherAmericanCode(Integer otherAmericanCode) {
		this.otherAmericanCode = otherAmericanCode;
	}
	@Column(name ="other_american_one",nullable=true,length=50)
	public Integer getOtherAmericanOne() {
		return otherAmericanOne;
	}
	public void setOtherAmericanOne(Integer otherAmericanOne) {
		this.otherAmericanOne = otherAmericanOne;
	}
	@Column(name ="other_american_two",nullable=true,length=50)
	public Integer getOtherAmericanTwo() {
		return otherAmericanTwo;
	}
	public void setOtherAmericanTwo(Integer otherAmericanTwo) {
		this.otherAmericanTwo = otherAmericanTwo;
	}
	@Column(name ="total_oceania",nullable=true,length=20)
	public String getTotalOceania() {
		return totalOceania;
	}
	public void setTotalOceania(String totalOceania) {
		this.totalOceania = totalOceania;
	}
	@Column(name ="total_oceania_code",nullable=true,length=50)
	public Integer getTotalOceaniaCode() {
		return totalOceaniaCode;
	}
	public void setTotalOceaniaCode(Integer totalOceaniaCode) {
		this.totalOceaniaCode = totalOceaniaCode;
	}
	@Column(name ="total_oceania_one",nullable=true,length=50)
	public Integer getTotalOceaniaOne() {
		return totalOceaniaOne;
	}
	public void setTotalOceaniaOne(Integer totalOceaniaOne) {
		this.totalOceaniaOne = totalOceaniaOne;
	}
	@Column(name ="total_oceania_two",nullable=true,length=50)
	public Integer getTotalOceaniaTwo() {
		return totalOceaniaTwo;
	}
	public void setTotalOceaniaTwo(Integer totalOceaniaTwo) {
		this.totalOceaniaTwo = totalOceaniaTwo;
	}
	@Column(name ="australia",nullable=true,length=20)
	public String getAustralia() {
		return australia;
	}
	public void setAustralia(String australia) {
		this.australia = australia;
	}
	@Column(name ="australia_code",nullable=true,length=50)
	public Integer getAustraliaCode() {
		return australiaCode;
	}
	public void setAustraliaCode(Integer australiaCode) {
		this.australiaCode = australiaCode;
	}
	@Column(name ="australia_one",nullable=true,length=50)
	public Integer getAustraliaOne() {
		return australiaOne;
	}
	public void setAustraliaOne(Integer australiaOne) {
		this.australiaOne = australiaOne;
	}
	@Column(name ="australia_two",nullable=true,length=50)
	public Integer getAustraliaTwo() {
		return australiaTwo;
	}
	public void setAustraliaTwo(Integer australiaTwo) {
		this.australiaTwo = australiaTwo;
	}
	@Column(name ="newland",nullable=true,length=20)
	public String getNewland() {
		return newland;
	}
	public void setNewland(String newland) {
		this.newland = newland;
	}
	@Column(name ="newland_code",nullable=true,length=50)
	public Integer getNewlandCode() {
		return newlandCode;
	}
	public void setNewlandCode(Integer newlandCode) {
		this.newlandCode = newlandCode;
	}
	@Column(name ="newland_one",nullable=true,length=50)
	public Integer getNewlandOne() {
		return newlandOne;
	}
	public void setNewlandOne(Integer newlandOne) {
		this.newlandOne = newlandOne;
	}
	@Column(name ="newland_two",nullable=true,length=50)
	public Integer getNewlandTwo() {
		return newlandTwo;
	}
	public void setNewlandTwo(Integer newlandTwo) {
		this.newlandTwo = newlandTwo;
	}
	@Column(name ="other_oceania",nullable=true,length=20)
	public String getOtherOceania() {
		return otherOceania;
	}
	public void setOtherOceania(String otherOceania) {
		this.otherOceania = otherOceania;
	}
	@Column(name ="other_oceania_code",nullable=true,length=50)
	public Integer getOtherOceaniaCode() {
		return otherOceaniaCode;
	}
	public void setOtherOceaniaCode(Integer otherOceaniaCode) {
		this.otherOceaniaCode = otherOceaniaCode;
	}
	@Column(name ="other_oceania_one",nullable=true,length=50)
	public Integer getOtherOceaniaOne() {
		return otherOceaniaOne;
	}
	public void setOtherOceaniaOne(Integer otherOceaniaOne) {
		this.otherOceaniaOne = otherOceaniaOne;
	}
	@Column(name ="other_oceania_two",nullable=true,length=50)
	public Integer getOtherOceaniaTwo() {
		return otherOceaniaTwo;
	}
	public void setOtherOceaniaTwo(Integer otherOceaniaTwo) {
		this.otherOceaniaTwo = otherOceaniaTwo;
	}
	@Column(name ="total_african",nullable=true,length=20)
	public String getTotalAfrica() {
		return totalAfrica;
	}
	public void setTotalAfrica(String totalAfrica) {
		this.totalAfrica = totalAfrica;
	}
	@Column(name ="total_african_code",nullable=true,length=50)
	public Integer getTotalAfricaCode() {
		return totalAfricaCode;
	}
	public void setTotalAfricaCode(Integer totalAfricaCode) {
		this.totalAfricaCode = totalAfricaCode;
	}
	@Column(name ="total_african_one",nullable=true,length=50)
	public Integer getTotalAfricaOne() {
		return totalAfricaOne;
	}
	public void setTotalAfricaOne(Integer totalAfricaOne) {
		this.totalAfricaOne = totalAfricaOne;
	}
	@Column(name ="total_african_two",nullable=true,length=50)
	public Integer getTotalAfricaTwo() {
		return totalAfricaTwo;
	}
	public void setTotalAfricaTwo(Integer totalAfricaTwo) {
		this.totalAfricaTwo = totalAfricaTwo;
	}
	@Column(name ="total_other",nullable=true,length=20)
	public String getTotalOther() {
		return totalOther;
	}
	public void setTotalOther(String totalOther) {
		this.totalOther = totalOther;
	}
	@Column(name ="total_other_code",nullable=true,length=50)
	public Integer getTotalOtherCode() {
		return totalOtherCode;
	}
	public void setTotalOtherCode(Integer totalOtherCode) {
		this.totalOtherCode = totalOtherCode;
	}
	@Column(name ="total_other_one",nullable=true,length=50)
	public Integer getTotalOtherOne() {
		return totalOtherOne;
	}
	public void setTotalOtherOne(Integer totalOtherOne) {
		this.totalOtherOne = totalOtherOne;
	}
	@Column(name ="total_other_two",nullable=true,length=50)
	public Integer getTotalOtherTwo() {
		return totalOtherTwo;
	}
	public void setTotalOtherTwo(Integer totalOtherTwo) {
		this.totalOtherTwo = totalOtherTwo;
	}

//	@Column(name ="tid",nullable=true,length=50)
//	public String getTid() {
//		return tid;
//	}
//	public void setTid(String tid) {    
//		this.tid = tid;
//	}
	@Column(name ="indexname",nullable=true,length=60)
	public String getIndexname() {
		return indexname;
	}
	public void setIndexname(String indexname) {
		this.indexname = indexname;
	}
	@Column(name ="wailian_person_time",nullable=true,length=120)
	public Integer getWailianPersonTime() {
		return wailianPersonTime;
	}
	public void setWailianPersonTime(Integer wailianPersonTime) {
		this.wailianPersonTime = wailianPersonTime;
	}
	@Column(name ="jiedai_person_time",nullable=true,length=120)
	public Integer getJiedaiPersonTime() {
		return jiedaiPersonTime;
	}
	public void setJiedaiPersonTime(Integer jiedaiPersonTime) {
		this.jiedaiPersonTime = jiedaiPersonTime;
	}
	@Column(name ="code",nullable=true,length=60)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name ="report_person",nullable=true,length=60)
	public String getReportPerson() {
		return reportPerson;
	}
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}
	@Column(name ="report_telephone",nullable=true,length=20)
	public String getReportTelephone() {
		return reportTelephone;
	}
	public void setReportTelephone(String reportTelephone) {
		this.reportTelephone = reportTelephone;
	}
	@Column(name ="report_date",nullable=true)
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	@Column(name ="status",nullable=true,length=10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name = "quarter")
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="tid",insertable=false,updatable=false)
	public Traveldata getTraveldata() {
		return traveldata;
	}
	public void setTraveldata(Traveldata traveldata) {
		this.traveldata = traveldata;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	@Column(name="holland_one")
	public Integer getHollandOne() {
		return hollandOne;
	}
	public void setHollandOne(Integer hollandOne) {
		this.hollandOne = hollandOne;
	}
	@Column(name="holland_two")
	public Integer getHollandTwo() {
		return hollandTwo;
	}
	public void setHollandTwo(Integer hollandTwo) {
		this.hollandTwo = hollandTwo;
	}
	@Column(name="holland_three")
	public Integer getHollandThree() {
		return hollandThree;
	}
	public void setHollandThree(Integer hollandThree) {
		this.hollandThree = hollandThree;
	}
	@Column(name="holland_four")
	public Integer getHollandFour() {
		return hollandFour;
	}
	public void setHollandFour(Integer hollandFour) {
		this.hollandFour = hollandFour;
	}
	@Column(name="denmark_one")
	public Integer getDenmarkOne() {
		return denmarkOne;
	}
	public void setDenmarkOne(Integer denmarkOne) {
		this.denmarkOne = denmarkOne;
	}
	@Column(name="denmark_two")
	public Integer getDenmarkTwo() {
		return denmarkTwo;
	}
	public void setDenmarkTwo(Integer denmarkTwo) {
		this.denmarkTwo = denmarkTwo;
	}
	@Column(name="denmark_three")
	public Integer getDenmarkThree() {
		return denmarkThree;
	}
	public void setDenmarkThree(Integer denmarkThree) {
		this.denmarkThree = denmarkThree;
	}
	@Column(name="denmark_four")
	public Integer getDenmarkFour() {
		return denmarkFour;
	}
	public void setDenmarkFour(Integer denmarkFour) {
		this.denmarkFour = denmarkFour;
	}
	@Column(name="southafrica_one")
	public Integer getSouthafricaOne() {
		return southafricaOne;
	}
	public void setSouthafricaOne(Integer southafricaOne) {
		this.southafricaOne = southafricaOne;
	}
	@Column(name="southafrica_two")
	public Integer getSouthafricaTwo() {
		return southafricaTwo;
	}
	public void setSouthafricaTwo(Integer southafricaTwo) {
		this.southafricaTwo = southafricaTwo;
	}
	@Column(name="southafrica_three")
	public Integer getSouthafricaThree() {
		return southafricaThree;
	}
	public void setSouthafricaThree(Integer southafricaThree) {
		this.southafricaThree = southafricaThree;
	}
	@Column(name="southafrica_four")
	public Integer getSouthafricaFour() {
		return southafricaFour;
	}
	public void setSouthafricaFour(Integer southafricaFour) {
		this.southafricaFour = southafricaFour;
	}
	@Column(name="egypt_one")
	public Integer getEgyptOne() {
		return egyptOne;
	}
	public void setEgyptOne(Integer egyptOne) {
		this.egyptOne = egyptOne;
	}
	@Column(name="egypt_two")
	public Integer getEgyptTwo() {
		return egyptTwo;
	}
	public void setEgyptTwo(Integer egyptTwo) {
		this.egyptTwo = egyptTwo;
	}
	@Column(name="egypt_three")
	public Integer getEgyptThree() {
		return egyptThree;
	}
	public void setEgyptThree(Integer egyptThree) {
		this.egyptThree = egyptThree;
	}
	@Column(name="egypt_four")
	public Integer getEgyptFour() {
		return egyptFour;
	}
	public void setEgyptFour(Integer egyptFour) {
		this.egyptFour = egyptFour;
	}
	@Column(name="kenya_one")
	public Integer getKenyaOne() {
		return kenyaOne;
	}
	public void setKenyaOne(Integer kenyaOne) {
		this.kenyaOne = kenyaOne;
	}
	@Column(name="kenya_two")
	public Integer getKenyaTwo() {
		return kenyaTwo;
	}
	public void setKenyaTwo(Integer kenyaTwo) {
		this.kenyaTwo = kenyaTwo;
	}
	@Column(name="kenya_three")
	public Integer getKenyaThree() {
		return kenyaThree;
	}
	public void setKenyaThree(Integer kenyaThree) {
		this.kenyaThree = kenyaThree;
	}
	@Column(name="kenya_four")
	public Integer getKenyaFour() {
		return kenyaFour;
	}
	public void setKenyaFour(Integer kenyaFour) {
		this.kenyaFour = kenyaFour;
	}
	@Column(name="africaother_one")
	public Integer getAfricaotherOne() {
		return africaotherOne;
	}
	public void setAfricaotherOne(Integer africaotherOne) {
		this.africaotherOne = africaotherOne;
	}
	@Column(name="africaother_two")
	public Integer getAfricaotherTwo() {
		return africaotherTwo;
	}
	public void setAfricaotherTwo(Integer africaotherTwo) {
		this.africaotherTwo = africaotherTwo;
	}
	@Column(name="africaother_three")
	public Integer getAfricaotherThree() {
		return africaotherThree;
	}
	public void setAfricaotherThree(Integer africaotherThree) {
		this.africaotherThree = africaotherThree;
	}
	@Column(name="africaother_four")
	public Integer getAfricaotherFour() {
		return africaotherFour;
	}
	public void setAfricaotherFour(Integer africaotherFour) {
		this.africaotherFour = africaotherFour;
	}
   
	public String getAsia() {
		return asia;
	}
	public void setAsia(String asia) {
		this.asia = asia;
	}
	public String getEurope() {
		return europe;
	}
	public void setEurope(String europe) {
		this.europe = europe;
	}
	public String getAfrica() {
		return africa;
	}
	public void setAfrica(String africa) {
		this.africa = africa;
	}
	public String getOceania() {
		return oceania;
	}
	public void setOceania(String oceania) {
		this.oceania = oceania;
	}
	public String getAmerica() {
		return america;
	}
	public void setAmerica(String america) {
		this.america = america;
	}
	/**
	 * 获取亚洲人次
	 * @return 亚洲人次
	 */
	public int asiaSeason(){
		Integer japan = japanOne + japanTwo;
		Integer korea = koreaOne + koreaTwo;
		Integer mongo = mongoOne + mongoTwo;
		Integer indonxy = indonxyOne + indonxyTwo;
		Integer malaxy = malaxyOne + malaxyTwo;
		Integer philipn = philipnOne + philipnTwo;
		Integer singapore = singaporeOne + singaporeTwo;
		Integer tailand = tailandOne + tailandTwo;
		Integer vietnam = vietnamOne + vietnamTwo;
		Integer myanamar = myanamarOne + myanamarTwo;
		Integer otherAsian = otherAsianOne + otherAsianTwo;
		Integer hk = hkComOne + hkComTwo;
		Integer mc = mcComOne + mcComTwo;
		Integer tw = twComOne + twComTwo;
		int count = japan+korea+mongo+indonxy+malaxy+philipn+singapore+tailand+vietnam+myanamar+otherAsian+hk+mc+tw;
		return count;
	}
	/**
	 * 获取亚洲人天
	 * @return 亚洲人天
	 */
	public int asiaSeasonDay(){
		Integer japan = japanThree + japanFour;
		Integer korea = koreaThree + koreaFour;
		Integer mongo = mongoThree + mongoFour;
		Integer indonxy = indonxyThree + indonxyFour;
		Integer malaxy = malaxyThree + malaxyFour;
		Integer philipn = philipnThree + philipnFour;
		Integer singapore = singaporeThree + singaporeFour;
		Integer tailand = tailandThree + tailandFour;
		Integer vietnam = vietnamThree + vietnamFour;
		Integer myanamar = myanamarThree + myanamarFour;
		Integer otherAsian = otherAsianThree + otherAsianFour;
		Integer hk = hkComThree + hkComFour;
		Integer mc = mcComThree + mcComFour;
		Integer tw = twComThree + twComFour;
		int count = japan+korea+mongo+indonxy+malaxy+philipn+singapore+tailand+vietnam+myanamar+otherAsian+hk+mc+tw;
		return count;
	}
	
	/**
	 * 获取欧洲人次
	 * @return 欧洲人次
	 */
	public int europeSeason(){
		Integer england = englandOne + englandTwo;
		Integer french = frenchOne + frenchTwo;
		Integer germany = germanyOne + germanyTwo;
		Integer italy = italyOne + italyTwo;
		Integer swiss = swissOne + swissTwo;
		Integer swedish = swedishOne + swedishTwo;
		Integer russia = russiaOne + russiaTwo;
		Integer spain = spainOne + spainTwo;
		Integer otherEuropean = otherEuropeanOne + otherEuropeanTwo;
		Integer denmark = denmarkOne + denmarkTwo;
		Integer holland = hollandOne + hollandTwo;
		int count = england + french + germany + italy + swiss + swedish + russia + spain + otherEuropean + denmark + holland;
		return count;
	}
	
	/**
	 * 获取美洲人次
	 * @return 美洲人次
	 */
	public int americaSeason(){
		Integer us = usOne + usTwo;
		Integer canada = canadaOne + canadaTwo;
		Integer otherAmerican = otherAmericanOne + otherAmericanTwo;
		int count = us+canada+otherAmerican;
		return count;
	}
	
	/**
	 * 获取大洋洲人次
	 * @return 大洋洲人次
	 */
	public int oceaniaSeason(){
		Integer australia = australiaOne + australiaTwo;
		Integer newland = newlandOne + newlandTwo;
		Integer otherOceania = otherOceaniaOne + otherOceaniaTwo;
		int count = australia+newland+otherOceania;
		return count;
	}
	
	/**
	 * 获取非洲人次
	 * @return 非洲人次
	 */
	public int africaSeason(){
		Integer india = indiaOne + indiaTwo;
		Integer southafrica = southafricaOne + southafricaTwo;
		Integer kenya = kenyaOne + kenyaTwo;
		Integer egypt = egyptOne + egyptTwo;
		Integer africaother = africaotherOne + africaotherTwo;
		int count = india+southafrica+kenya+egypt+africaother;
		return count;
	}
}
