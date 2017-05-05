<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<t:datagrid name="leaveGrid" title="" queryMode="group"
	actionUrl="statisticTravelquarterController.do?outAimbyareaDay"
	idField="id" fit="true">

	<t:dgCol title="地区" field="area"
		replace="市辖区_0,吉阳区_1,崖州区_2,天涯区_3,海棠区_4"></t:dgCol>
	<t:dgCol title="年份" field="year" sortable="ture"></t:dgCol>
	<t:dgCol title="季度" field="quarter" sortable="ture"
		replace="第一季度_1,第二季度_2,第三季度_3,第四季度_4"></t:dgCol>
	<t:dgCol title="香港同胞" field="sumhongkong" sortable="ture"></t:dgCol>
	<t:dgCol title="澳门同胞" field="summacau" sortable="ture"></t:dgCol>
	<t:dgCol title="台湾同胞" field="sumtaiwan" sortable="ture"></t:dgCol>
	<t:dgCol title="日本" field="sumjapan" sortable="ture"></t:dgCol>
	<t:dgCol title="韩国" field="sumkorea" sortable="ture"></t:dgCol>
	<t:dgCol title="蒙古" field="sumMongolia" sortable="ture"></t:dgCol>
	<t:dgCol title="印度尼西亚" field="sumIndonesia" sortable="ture"></t:dgCol>
	<t:dgCol title="马来西亚" field="sumMalaysia" sortable="ture"></t:dgCol>
	<t:dgCol title="菲律宾" field="sumPhilippines" sortable="ture"></t:dgCol>
	<t:dgCol title="新加坡" field="sumSingapore" sortable="ture"></t:dgCol>
	<t:dgCol title="泰国" field="sumThailand" sortable="ture"></t:dgCol>
	<t:dgCol title="印度" field="sumIndia" sortable="ture"></t:dgCol>
	<t:dgCol title="越南" field="sumVietnam" sortable="ture"></t:dgCol>
	<t:dgCol title="缅甸" field="sumBurma" sortable="ture"></t:dgCol>
	<t:dgCol title="亚洲其他" field="sumAsianOther" sortable="ture"></t:dgCol>
	<t:dgCol title="英国" field="sumenglish" sortable="ture"></t:dgCol>
	<t:dgCol title="法国" field="sumFrance" sortable="ture"></t:dgCol>
	<t:dgCol title="德国" field="sumGermany" sortable="ture"></t:dgCol>
	<%-- <t:dgCol title="意大利" field="sumItaly"  sortable="ture" ></t:dgCol>
	<t:dgCol title="瑞士" field="sumSwitzerland"  sortable="ture" ></t:dgCol>
	<t:dgCol title="瑞典" field="sumSweden"  sortable="ture" ></t:dgCol>
	<t:dgCol title="俄罗斯" field="sumRussia"  sortable="ture" ></t:dgCol>	
	<t:dgCol title="西班牙" field="sumSpain"  sortable="ture" ></t:dgCol>	
	<t:dgCol title="荷兰" field="sumHolland"  sortable="ture" ></t:dgCol>	
	<t:dgCol title="丹麦" field="sumDenmark"  sortable="ture" ></t:dgCol>	
	<t:dgCol title="欧洲其他" field="sumEuropeother"  sortable="ture" ></t:dgCol>	
	<t:dgCol title="美国" field="sumus"  sortable="ture" ></t:dgCol>	
	<t:dgCol title="加拿大" field="sumcanada"  sortable="ture" ></t:dgCol>	
	<t:dgCol title="美洲其他" field="sumUsOther"  sortable="ture" ></t:dgCol>
	<t:dgCol title="澳大利亚" field="sumAustralian"  sortable="ture" ></t:dgCol>
	<t:dgCol title="新西兰" field="sumZealand"  sortable="ture" ></t:dgCol>
	<t:dgCol title="大洋洲其他" field="sumOceaniaother"  sortable="ture" ></t:dgCol>
	<t:dgCol title="南非" field="sumSouthAfrica"  sortable="ture" ></t:dgCol>
	<t:dgCol title="埃及" field="sumEgypt"  sortable="ture" ></t:dgCol>
	<t:dgCol title="肯尼亚" field="sumKenya"  sortable="ture" ></t:dgCol>
	<t:dgCol title="非洲其他" field="sumAfricaother"  sortable="ture" ></t:dgCol>
	<t:dgCol title="其他小计" field="sumOthertotal"  sortable="ture" ></t:dgCol> --%>
</t:datagrid>



