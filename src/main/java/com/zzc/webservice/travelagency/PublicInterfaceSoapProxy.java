package com.zzc.webservice.travelagency;

public class PublicInterfaceSoapProxy implements com.zzc.webservice.travelagency.PublicInterfaceSoap {
  private String _endpoint = null;
  private com.zzc.webservice.travelagency.PublicInterfaceSoap publicInterfaceSoap = null;
  
  public PublicInterfaceSoapProxy() {
    _initPublicInterfaceSoapProxy();
  }
  
  public PublicInterfaceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initPublicInterfaceSoapProxy();
  }
  
  private void _initPublicInterfaceSoapProxy() {
    try {
      publicInterfaceSoap = (new com.zzc.webservice.travelagency.PublicInterfaceLocator()).getPublicInterfaceSoap();
      if (publicInterfaceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)publicInterfaceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)publicInterfaceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (publicInterfaceSoap != null)
      ((javax.xml.rpc.Stub)publicInterfaceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.zzc.webservice.travelagency.PublicInterfaceSoap getPublicInterfaceSoap() {
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap;
  }
  
  public com.zzc.webservice.travelagency.GetBasicInfoResponseGetBasicInfoResult getBasicInfo(java.lang.String areaID, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.getBasicInfo(areaID, verificationCode);
  }
  
  public com.zzc.webservice.travelagency.GetBasicPWDResponseGetBasicPWDResult getBasicPWD(java.lang.String areaID, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.getBasicPWD(areaID, verificationCode);
  }
  
  public java.lang.String insertIntoBasic(java.lang.String areaID, java.lang.String code, java.lang.String tableDate, java.lang.String unitName, java.lang.String delegate, java.lang.String userName, java.lang.String unitMaster, java.lang.String filler, java.lang.String fillerTel, java.lang.String adessr, java.lang.String province, java.lang.String city, java.lang.String postcode, java.lang.String regionalismCode, java.lang.String mobile, java.lang.String tel, java.lang.String telNo, java.lang.String fax, java.lang.String faxNo, java.lang.String webSite, java.lang.String email, java.lang.String registrationType, java.lang.String unitType, java.lang.String accommodationStar, java.lang.String travelAgencyType, java.lang.String sceneSpotLevel, java.lang.String outBoundTourism, java.lang.String borderTour, java.lang.String englishName, java.lang.String QQ, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.insertIntoBasic(areaID, code, tableDate, unitName, delegate, userName, unitMaster, filler, fillerTel, adessr, province, city, postcode, regionalismCode, mobile, tel, telNo, fax, faxNo, webSite, email, registrationType, unitType, accommodationStar, travelAgencyType, sceneSpotLevel, outBoundTourism, borderTour, englishName, QQ, verificationCode);
  }
  
  public com.zzc.webservice.travelagency.GetBasicStateResponseGetBasicStateResult getBasicState(java.lang.String areaID, java.lang.String userName, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.getBasicState(areaID, userName, verificationCode);
  }
  
  public java.lang.String insertIntoInternalTourism(java.lang.String areaID, java.lang.String code, java.lang.String unitMaster, java.lang.String filler, java.lang.String fillerTel, java.lang.String sortID, java.lang.String seasonDay, java.lang.String seasonDay_boy, java.lang.String season, java.lang.String season_boy, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.insertIntoInternalTourism(areaID, code, unitMaster, filler, fillerTel, sortID, seasonDay, seasonDay_boy, season, season_boy, verificationCode);
  }
  
  public java.lang.String insertIntoOutboundTravel(java.lang.String areaID, java.lang.String code, java.lang.String unitMaster, java.lang.String filler, java.lang.String fillerTel, java.lang.String sortID, java.lang.String seasonDay, java.lang.String season, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.insertIntoOutboundTravel(areaID, code, unitMaster, filler, fillerTel, sortID, seasonDay, season, verificationCode);
  }
  
  public java.lang.String insertIntoInboundTravel(java.lang.String areaID, java.lang.String code, java.lang.String unitMaster, java.lang.String filler, java.lang.String fillerTel, java.lang.String sortID, java.lang.String seasonDay, java.lang.String seasonAdmitDay, java.lang.String season, java.lang.String seasonAdmit, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.insertIntoInboundTravel(areaID, code, unitMaster, filler, fillerTel, sortID, seasonDay, seasonAdmitDay, season, seasonAdmit, verificationCode);
  }
  
  public com.zzc.webservice.travelagency.GetInternalTourismResponseGetInternalTourismResult getInternalTourism(java.lang.String areaID, java.lang.String code, java.lang.String seaYear, java.lang.String sea, java.lang.String sortID, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.getInternalTourism(areaID, code, seaYear, sea, sortID, verificationCode);
  }
  
  public com.zzc.webservice.travelagency.GetOutboundTravelResponseGetOutboundTravelResult getOutboundTravel(java.lang.String areaID, java.lang.String code, java.lang.String seaYear, java.lang.String sea, java.lang.String sortID, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.getOutboundTravel(areaID, code, seaYear, sea, sortID, verificationCode);
  }
  
  public com.zzc.webservice.travelagency.GetInboundTravelResponseGetInboundTravelResult getInboundTravel(java.lang.String areaID, java.lang.String code, java.lang.String seaYear, java.lang.String sea, java.lang.String sortID, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.getInboundTravel(areaID, code, seaYear, sea, sortID, verificationCode);
  }
  
  public com.zzc.webservice.travelagency.GetQuarterlyStateResponseGetQuarterlyStateResult getQuarterlyState(java.lang.String areaID, java.lang.String code, java.lang.String seaYear, java.lang.String sea, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.getQuarterlyState(areaID, code, seaYear, sea, verificationCode);
  }
  
  public java.lang.String insertIntoFinance(java.lang.String areaID, java.lang.String code, java.lang.String tableDate, java.lang.String unitName, java.lang.String unitMaster, java.lang.String filler, java.lang.String fillerTel, java.lang.String currentAssetsTotal, java.lang.String longTermInvest, java.lang.String fixAssetsTotal, java.lang.String fixAssetsInitialPrice, java.lang.String capitalTotal, java.lang.String totalLiabilities, java.lang.String ownerBenefitTotal, java.lang.String realCapital, java.lang.String operationIncome, java.lang.String inboundRevenue, java.lang.String outboundRevenues, java.lang.String domesticRevenue, java.lang.String operationCost, java.lang.String operationFee, java.lang.String operationTaxAddition, java.lang.String businessProfit, java.lang.String inbundProfit, java.lang.String outboundProfit, java.lang.String domesticProfit, java.lang.String managementFee, java.lang.String tax, java.lang.String financeFee, java.lang.String interestPay, java.lang.String operationProfit, java.lang.String profitTotal, java.lang.String incomeTax, java.lang.String annualPayTotal, java.lang.String yearendEmployment, java.lang.String juniorEmployment, java.lang.String accumulatedDepreciation, java.lang.String yearDepreciation, java.lang.String travelExpenses, java.lang.String gains, java.lang.String returnInvestment, java.lang.String whetherPerform, java.lang.String nonIncome, java.lang.String subsidies, java.lang.String costSales, java.lang.String grossProfit, java.lang.String theVAT, java.lang.String laborContract, java.lang.String leaderNumber, java.lang.String storesNumber, java.lang.String branchNumber, java.lang.String subsidiariesNumber, java.lang.String loss, java.lang.String city, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.insertIntoFinance(areaID, code, tableDate, unitName, unitMaster, filler, fillerTel, currentAssetsTotal, longTermInvest, fixAssetsTotal, fixAssetsInitialPrice, capitalTotal, totalLiabilities, ownerBenefitTotal, realCapital, operationIncome, inboundRevenue, outboundRevenues, domesticRevenue, operationCost, operationFee, operationTaxAddition, businessProfit, inbundProfit, outboundProfit, domesticProfit, managementFee, tax, financeFee, interestPay, operationProfit, profitTotal, incomeTax, annualPayTotal, yearendEmployment, juniorEmployment, accumulatedDepreciation, yearDepreciation, travelExpenses, gains, returnInvestment, whetherPerform, nonIncome, subsidies, costSales, grossProfit, theVAT, laborContract, leaderNumber, storesNumber, branchNumber, subsidiariesNumber, loss, city, verificationCode);
  }
  
  public com.zzc.webservice.travelagency.GetFinanceResponseGetFinanceResult getFinance(java.lang.String areaID, java.lang.String code, java.lang.String year, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.getFinance(areaID, code, year, verificationCode);
  }
  
  public com.zzc.webservice.travelagency.GetFinanceStateResponseGetFinanceStateResult getFinanceState(java.lang.String areaID, java.lang.String code, java.lang.String year, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.getFinanceState(areaID, code, year, verificationCode);
  }
  
  public com.zzc.webservice.travelagency.GetNoticeResponseGetNoticeResult getNotice(java.lang.String areaID, java.lang.String verificationCode) throws java.rmi.RemoteException{
    if (publicInterfaceSoap == null)
      _initPublicInterfaceSoapProxy();
    return publicInterfaceSoap.getNotice(areaID, verificationCode);
  }
  
  
}