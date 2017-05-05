/**
 * WebService_SanYaSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public interface WebService_SanYaSoap extends java.rmi.Remote {
    public java.lang.String a1_Test(java.lang.String uuid) throws java.rmi.RemoteException;

    /**
     * <b><i>获取三亚所有酒店基本信息：GetHotel</i></b></br><font color='blue'>string</font>
     * Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.GetHotelResponseGetHotelResult getHotel(java.lang.String key, com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic) throws java.rmi.RemoteException;

    /**
     * <b><i>获取三亚酒店预计接待情况历史数据：GetHotel</i></b></br><font color='blue'>string</font>
     * Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.GetHotelEstimateResponseGetHotelEstimateResult getHotelEstimate(java.lang.String key, com.zzc.webservice.provinceHotel.HotelEstimate hotelEstimate) throws java.rmi.RemoteException;

    /**
     * <b><i>获取三亚酒店住宿接待情况历史数据（酒店月报数据）：GetMonthly</i></b></br><font
     * color='blue'>string</font> Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult getMonthly(java.lang.String key, com.zzc.webservice.provinceHotel.Audit a, java.lang.String userAccount) throws java.rmi.RemoteException;

    /**
     * <b><i>获取三亚端午节旅游接待数据：GetDragonBoatFestival</i></b></br><font
     * color='blue'>string</font> Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.GetDragonBoatFestivalResponseGetDragonBoatFestivalResult getDragonBoatFestival(java.lang.String key, com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dragonBoatFestivalInfo) throws java.rmi.RemoteException;

    /**
     * <b><i>获取三亚中秋节旅游接待数据：GetMoonFestival</i></b></br><font color='blue'>string</font>
     * Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.GetMoonFestivalResponseGetMoonFestivalResult getMoonFestival(java.lang.String key, com.zzc.webservice.provinceHotel.MoonFestivalInfo moonFestivalInfo) throws java.rmi.RemoteException;

    /**
     * <b><i>新增三亚酒店基本信息：AddHotel</i></b></br><font color='blue'>string</font>
     * Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult addHotel(java.lang.String key, com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic) throws java.rmi.RemoteException;

    /**
     * <b><i>更新三亚酒店基本信息：ModiflyHotel</i></b></br><font color='blue'>string</font>
     * Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult modiflyHotel(java.lang.String key, java.lang.String userAccount, com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic) throws java.rmi.RemoteException;

    /**
     * <b><i>新增酒店预计接待：ModiflyHotel</i></b></br><font color='blue'>string</font>
     * Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.AddHotelEstimateResponseAddHotelEstimateResult addHotelEstimate(java.lang.String key, com.zzc.webservice.provinceHotel.HotelEstimateInMonth hotelEstimate) throws java.rmi.RemoteException;

    /**
     * <b><i>新增酒店住宿接待情况：AddAuditAndMonthly</i></b></br><font color='blue'>string</font>
     * Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.AddAuditAndMonthlyResponseAddAuditAndMonthlyResult addAuditAndMonthly(java.lang.String key, com.zzc.webservice.provinceHotel.Audit audit, com.zzc.webservice.provinceHotel.TheMonth[] listTheMonth, java.lang.String reportedEnterpriseUserAccount) throws java.rmi.RemoteException;

    /**
     * <b><i>修改酒店住宿接待情况：ModiflyAuditAndMonthly</i></b></br><font color='blue'>string</font>
     * Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult modiflyAuditAndMonthly(java.lang.String key, com.zzc.webservice.provinceHotel.Audit audit, com.zzc.webservice.provinceHotel.TheMonth[] newlistTheMonth, java.lang.String reportedEnterpriseUserAccount) throws java.rmi.RemoteException;

    /**
     * <b><i>新增三亚端午节旅游接待数据：AddDragonBoatFestival</i></b></br><font
     * color='blue'>string</font> Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult addDragonBoatFestival(java.lang.String key, com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dBFInfo, java.lang.String userAccount) throws java.rmi.RemoteException;

    /**
     * <b><i>新增三亚中秋节旅游接待数据：AddMoonFestival</i></b></br><font color='blue'>string</font>
     * Key:必填</br>
     */
    public com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult addMoonFestival(java.lang.String key, com.zzc.webservice.provinceHotel.MoonFestivalInfo moonFInfo, java.lang.String userAccount) throws java.rmi.RemoteException;
}
