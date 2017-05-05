package com.zzc.webservice.provinceHotel;

public class WebService_SanYaSoapProxy implements com.zzc.webservice.provinceHotel.WebService_SanYaSoap {
  private String _endpoint = null;
  private com.zzc.webservice.provinceHotel.WebService_SanYaSoap webService_SanYaSoap = null;
  
  public WebService_SanYaSoapProxy() {
    _initWebService_SanYaSoapProxy();
  }
  
  public WebService_SanYaSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebService_SanYaSoapProxy();
  }
  
  private void _initWebService_SanYaSoapProxy() {
    try {
      webService_SanYaSoap = (new com.zzc.webservice.provinceHotel.WebService_SanYaLocator()).getWebService_SanYaSoap();
      if (webService_SanYaSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webService_SanYaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webService_SanYaSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webService_SanYaSoap != null)
      ((javax.xml.rpc.Stub)webService_SanYaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.zzc.webservice.provinceHotel.WebService_SanYaSoap getWebService_SanYaSoap() {
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap;
  }
  
  public java.lang.String a1_Test(java.lang.String uuid) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.a1_Test(uuid);
  }
  
  public com.zzc.webservice.provinceHotel.GetHotelResponseGetHotelResult getHotel(java.lang.String key, com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.getHotel(key, traveAgenciesBasic);
  }
  
  public com.zzc.webservice.provinceHotel.GetHotelEstimateResponseGetHotelEstimateResult getHotelEstimate(java.lang.String key, com.zzc.webservice.provinceHotel.HotelEstimate hotelEstimate) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.getHotelEstimate(key, hotelEstimate);
  }
  
  public com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult getMonthly(java.lang.String key, com.zzc.webservice.provinceHotel.Audit a, java.lang.String userAccount) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.getMonthly(key, a, userAccount);
  }
  
  public com.zzc.webservice.provinceHotel.GetDragonBoatFestivalResponseGetDragonBoatFestivalResult getDragonBoatFestival(java.lang.String key, com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dragonBoatFestivalInfo) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.getDragonBoatFestival(key, dragonBoatFestivalInfo);
  }
  
  public com.zzc.webservice.provinceHotel.GetMoonFestivalResponseGetMoonFestivalResult getMoonFestival(java.lang.String key, com.zzc.webservice.provinceHotel.MoonFestivalInfo moonFestivalInfo) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.getMoonFestival(key, moonFestivalInfo);
  }
  
  public com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult addHotel(java.lang.String key, com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.addHotel(key, traveAgenciesBasic);
  }
  
  public com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult modiflyHotel(java.lang.String key, java.lang.String userAccount, com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.modiflyHotel(key, userAccount, traveAgenciesBasic);
  }
  
  public com.zzc.webservice.provinceHotel.AddHotelEstimateResponseAddHotelEstimateResult addHotelEstimate(java.lang.String key, com.zzc.webservice.provinceHotel.HotelEstimateInMonth hotelEstimate) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.addHotelEstimate(key, hotelEstimate);
  }
  
  public com.zzc.webservice.provinceHotel.AddAuditAndMonthlyResponseAddAuditAndMonthlyResult addAuditAndMonthly(java.lang.String key, com.zzc.webservice.provinceHotel.Audit audit, com.zzc.webservice.provinceHotel.TheMonth[] listTheMonth, java.lang.String reportedEnterpriseUserAccount) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.addAuditAndMonthly(key, audit, listTheMonth, reportedEnterpriseUserAccount);
  }
  
  public com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult modiflyAuditAndMonthly(java.lang.String key, com.zzc.webservice.provinceHotel.Audit audit, com.zzc.webservice.provinceHotel.TheMonth[] newlistTheMonth, java.lang.String reportedEnterpriseUserAccount) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.modiflyAuditAndMonthly(key, audit, newlistTheMonth, reportedEnterpriseUserAccount);
  }
  
  public com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult addDragonBoatFestival(java.lang.String key, com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dBFInfo, java.lang.String userAccount) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.addDragonBoatFestival(key, dBFInfo, userAccount);
  }
  
  public com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult addMoonFestival(java.lang.String key, com.zzc.webservice.provinceHotel.MoonFestivalInfo moonFInfo, java.lang.String userAccount) throws java.rmi.RemoteException{
    if (webService_SanYaSoap == null)
      _initWebService_SanYaSoapProxy();
    return webService_SanYaSoap.addMoonFestival(key, moonFInfo, userAccount);
  }
  
  
}