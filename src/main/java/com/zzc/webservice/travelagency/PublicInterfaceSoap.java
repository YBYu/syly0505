/**
 * PublicInterfaceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public interface PublicInterfaceSoap extends java.rmi.Remote {
    public com.zzc.webservice.travelagency.GetBasicInfoResponseGetBasicInfoResult getBasicInfo(java.lang.String areaID, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public com.zzc.webservice.travelagency.GetBasicPWDResponseGetBasicPWDResult getBasicPWD(java.lang.String areaID, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public java.lang.String insertIntoBasic(java.lang.String areaID, java.lang.String code, java.lang.String tableDate, java.lang.String unitName, java.lang.String delegate, java.lang.String userName, java.lang.String unitMaster, java.lang.String filler, java.lang.String fillerTel, java.lang.String adessr, java.lang.String province, java.lang.String city, java.lang.String postcode, java.lang.String regionalismCode, java.lang.String mobile, java.lang.String tel, java.lang.String telNo, java.lang.String fax, java.lang.String faxNo, java.lang.String webSite, java.lang.String email, java.lang.String registrationType, java.lang.String unitType, java.lang.String accommodationStar, java.lang.String travelAgencyType, java.lang.String sceneSpotLevel, java.lang.String outBoundTourism, java.lang.String borderTour, java.lang.String englishName, java.lang.String QQ, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public com.zzc.webservice.travelagency.GetBasicStateResponseGetBasicStateResult getBasicState(java.lang.String areaID, java.lang.String userName, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public java.lang.String insertIntoInternalTourism(java.lang.String areaID, java.lang.String code, java.lang.String unitMaster, java.lang.String filler, java.lang.String fillerTel, java.lang.String sortID, java.lang.String seasonDay, java.lang.String seasonDay_boy, java.lang.String season, java.lang.String season_boy, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public java.lang.String insertIntoOutboundTravel(java.lang.String areaID, java.lang.String code, java.lang.String unitMaster, java.lang.String filler, java.lang.String fillerTel, java.lang.String sortID, java.lang.String seasonDay, java.lang.String season, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public java.lang.String insertIntoInboundTravel(java.lang.String areaID, java.lang.String code, java.lang.String unitMaster, java.lang.String filler, java.lang.String fillerTel, java.lang.String sortID, java.lang.String seasonDay, java.lang.String seasonAdmitDay, java.lang.String season, java.lang.String seasonAdmit, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public com.zzc.webservice.travelagency.GetInternalTourismResponseGetInternalTourismResult getInternalTourism(java.lang.String areaID, java.lang.String code, java.lang.String seaYear, java.lang.String sea, java.lang.String sortID, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public com.zzc.webservice.travelagency.GetOutboundTravelResponseGetOutboundTravelResult getOutboundTravel(java.lang.String areaID, java.lang.String code, java.lang.String seaYear, java.lang.String sea, java.lang.String sortID, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public com.zzc.webservice.travelagency.GetInboundTravelResponseGetInboundTravelResult getInboundTravel(java.lang.String areaID, java.lang.String code, java.lang.String seaYear, java.lang.String sea, java.lang.String sortID, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public com.zzc.webservice.travelagency.GetQuarterlyStateResponseGetQuarterlyStateResult getQuarterlyState(java.lang.String areaID, java.lang.String code, java.lang.String seaYear, java.lang.String sea, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public java.lang.String insertIntoFinance(java.lang.String areaID, java.lang.String code, java.lang.String tableDate, java.lang.String unitName, java.lang.String unitMaster, java.lang.String filler, java.lang.String fillerTel, java.lang.String currentAssetsTotal, java.lang.String longTermInvest, java.lang.String fixAssetsTotal, java.lang.String fixAssetsInitialPrice, java.lang.String capitalTotal, java.lang.String totalLiabilities, java.lang.String ownerBenefitTotal, java.lang.String realCapital, java.lang.String operationIncome, java.lang.String inboundRevenue, java.lang.String outboundRevenues, java.lang.String domesticRevenue, java.lang.String operationCost, java.lang.String operationFee, java.lang.String operationTaxAddition, java.lang.String businessProfit, java.lang.String inbundProfit, java.lang.String outboundProfit, java.lang.String domesticProfit, java.lang.String managementFee, java.lang.String tax, java.lang.String financeFee, java.lang.String interestPay, java.lang.String operationProfit, java.lang.String profitTotal, java.lang.String incomeTax, java.lang.String annualPayTotal, java.lang.String yearendEmployment, java.lang.String juniorEmployment, java.lang.String accumulatedDepreciation, java.lang.String yearDepreciation, java.lang.String travelExpenses, java.lang.String gains, java.lang.String returnInvestment, java.lang.String whetherPerform, java.lang.String nonIncome, java.lang.String subsidies, java.lang.String costSales, java.lang.String grossProfit, java.lang.String theVAT, java.lang.String laborContract, java.lang.String leaderNumber, java.lang.String storesNumber, java.lang.String branchNumber, java.lang.String subsidiariesNumber, java.lang.String loss, java.lang.String city, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public com.zzc.webservice.travelagency.GetFinanceResponseGetFinanceResult getFinance(java.lang.String areaID, java.lang.String code, java.lang.String year, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public com.zzc.webservice.travelagency.GetFinanceStateResponseGetFinanceStateResult getFinanceState(java.lang.String areaID, java.lang.String code, java.lang.String year, java.lang.String verificationCode) throws java.rmi.RemoteException;
    public com.zzc.webservice.travelagency.GetNoticeResponseGetNoticeResult getNotice(java.lang.String areaID, java.lang.String verificationCode) throws java.rmi.RemoteException;
}
