/**
 * TraveAgenciesBasic.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class TraveAgenciesBasic  extends com.zzc.webservice.provinceHotel.AbstractObject  implements java.io.Serializable {
    private int uuid;

    private java.lang.String userAccount;

    private java.lang.String userPassword;

    private java.lang.String userPassword2;

    private int userRole;

    private java.lang.String userRoleName;

    private int orderNumber;

    private java.lang.String orcanCode;

    private java.lang.String orcanName;

    private java.lang.String legalRepresentative;

    private java.util.Calendar orcanRegistertime;

    private java.lang.String countyCode;

    private java.lang.String provinceName;

    private java.lang.String cityName;

    private java.lang.String countyName;

    private java.lang.String downName;

    private java.lang.String streetName;

    private java.lang.String areaCode;

    private java.lang.String telephoneNumber;

    private java.lang.String extensionNumber;

    private java.lang.String phototelephony;

    private java.lang.String phototelephonyExtensionNumber;

    private java.lang.String mobileTelephone;

    private java.lang.String postalCode;

    private java.lang.String EMail;

    private java.lang.String intenetUrl;

    private int orcanType;

    private java.lang.String orcanTypeName;

    private int starHotel;

    private java.lang.String starHotelName;

    private int shareHolding;

    private java.lang.String shareHoldingName;

    private int roomNumber;

    private int bedNumber;

    private java.lang.String unitLeader;

    private java.lang.String operateUser;

    private java.lang.String operateTelephone;

    private java.lang.String operateType;

    private int isRatified;

    private java.lang.String ratifiedUser;

    private java.util.Calendar ratifiedTime;

    private java.util.Calendar createTime;

    private java.util.Calendar updateTime;

    private int flag;

    public TraveAgenciesBasic() {
    }

    public TraveAgenciesBasic(
           int uuid,
           java.lang.String userAccount,
           java.lang.String userPassword,
           java.lang.String userPassword2,
           int userRole,
           java.lang.String userRoleName,
           int orderNumber,
           java.lang.String orcanCode,
           java.lang.String orcanName,
           java.lang.String legalRepresentative,
           java.util.Calendar orcanRegistertime,
           java.lang.String countyCode,
           java.lang.String provinceName,
           java.lang.String cityName,
           java.lang.String countyName,
           java.lang.String downName,
           java.lang.String streetName,
           java.lang.String areaCode,
           java.lang.String telephoneNumber,
           java.lang.String extensionNumber,
           java.lang.String phototelephony,
           java.lang.String phototelephonyExtensionNumber,
           java.lang.String mobileTelephone,
           java.lang.String postalCode,
           java.lang.String EMail,
           java.lang.String intenetUrl,
           int orcanType,
           java.lang.String orcanTypeName,
           int starHotel,
           java.lang.String starHotelName,
           int shareHolding,
           java.lang.String shareHoldingName,
           int roomNumber,
           int bedNumber,
           java.lang.String unitLeader,
           java.lang.String operateUser,
           java.lang.String operateTelephone,
           java.lang.String operateType,
           int isRatified,
           java.lang.String ratifiedUser,
           java.util.Calendar ratifiedTime,
           java.util.Calendar createTime,
           java.util.Calendar updateTime,
           int flag) {
        this.uuid = uuid;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userPassword2 = userPassword2;
        this.userRole = userRole;
        this.userRoleName = userRoleName;
        this.orderNumber = orderNumber;
        this.orcanCode = orcanCode;
        this.orcanName = orcanName;
        this.legalRepresentative = legalRepresentative;
        this.orcanRegistertime = orcanRegistertime;
        this.countyCode = countyCode;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.countyName = countyName;
        this.downName = downName;
        this.streetName = streetName;
        this.areaCode = areaCode;
        this.telephoneNumber = telephoneNumber;
        this.extensionNumber = extensionNumber;
        this.phototelephony = phototelephony;
        this.phototelephonyExtensionNumber = phototelephonyExtensionNumber;
        this.mobileTelephone = mobileTelephone;
        this.postalCode = postalCode;
        this.EMail = EMail;
        this.intenetUrl = intenetUrl;
        this.orcanType = orcanType;
        this.orcanTypeName = orcanTypeName;
        this.starHotel = starHotel;
        this.starHotelName = starHotelName;
        this.shareHolding = shareHolding;
        this.shareHoldingName = shareHoldingName;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.unitLeader = unitLeader;
        this.operateUser = operateUser;
        this.operateTelephone = operateTelephone;
        this.operateType = operateType;
        this.isRatified = isRatified;
        this.ratifiedUser = ratifiedUser;
        this.ratifiedTime = ratifiedTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.flag = flag;
    }


    /**
     * Gets the uuid value for this TraveAgenciesBasic.
     * 
     * @return uuid
     */
    public int getUuid() {
        return uuid;
    }


    /**
     * Sets the uuid value for this TraveAgenciesBasic.
     * 
     * @param uuid
     */
    public void setUuid(int uuid) {
        this.uuid = uuid;
    }


    /**
     * Gets the userAccount value for this TraveAgenciesBasic.
     * 
     * @return userAccount
     */
    public java.lang.String getUserAccount() {
        return userAccount;
    }


    /**
     * Sets the userAccount value for this TraveAgenciesBasic.
     * 
     * @param userAccount
     */
    public void setUserAccount(java.lang.String userAccount) {
        this.userAccount = userAccount;
    }


    /**
     * Gets the userPassword value for this TraveAgenciesBasic.
     * 
     * @return userPassword
     */
    public java.lang.String getUserPassword() {
        return userPassword;
    }


    /**
     * Sets the userPassword value for this TraveAgenciesBasic.
     * 
     * @param userPassword
     */
    public void setUserPassword(java.lang.String userPassword) {
        this.userPassword = userPassword;
    }


    /**
     * Gets the userPassword2 value for this TraveAgenciesBasic.
     * 
     * @return userPassword2
     */
    public java.lang.String getUserPassword2() {
        return userPassword2;
    }


    /**
     * Sets the userPassword2 value for this TraveAgenciesBasic.
     * 
     * @param userPassword2
     */
    public void setUserPassword2(java.lang.String userPassword2) {
        this.userPassword2 = userPassword2;
    }


    /**
     * Gets the userRole value for this TraveAgenciesBasic.
     * 
     * @return userRole
     */
    public int getUserRole() {
        return userRole;
    }


    /**
     * Sets the userRole value for this TraveAgenciesBasic.
     * 
     * @param userRole
     */
    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }


    /**
     * Gets the userRoleName value for this TraveAgenciesBasic.
     * 
     * @return userRoleName
     */
    public java.lang.String getUserRoleName() {
        return userRoleName;
    }


    /**
     * Sets the userRoleName value for this TraveAgenciesBasic.
     * 
     * @param userRoleName
     */
    public void setUserRoleName(java.lang.String userRoleName) {
        this.userRoleName = userRoleName;
    }


    /**
     * Gets the orderNumber value for this TraveAgenciesBasic.
     * 
     * @return orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }


    /**
     * Sets the orderNumber value for this TraveAgenciesBasic.
     * 
     * @param orderNumber
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }


    /**
     * Gets the orcanCode value for this TraveAgenciesBasic.
     * 
     * @return orcanCode
     */
    public java.lang.String getOrcanCode() {
        return orcanCode;
    }


    /**
     * Sets the orcanCode value for this TraveAgenciesBasic.
     * 
     * @param orcanCode
     */
    public void setOrcanCode(java.lang.String orcanCode) {
        this.orcanCode = orcanCode;
    }


    /**
     * Gets the orcanName value for this TraveAgenciesBasic.
     * 
     * @return orcanName
     */
    public java.lang.String getOrcanName() {
        return orcanName;
    }


    /**
     * Sets the orcanName value for this TraveAgenciesBasic.
     * 
     * @param orcanName
     */
    public void setOrcanName(java.lang.String orcanName) {
        this.orcanName = orcanName;
    }


    /**
     * Gets the legalRepresentative value for this TraveAgenciesBasic.
     * 
     * @return legalRepresentative
     */
    public java.lang.String getLegalRepresentative() {
        return legalRepresentative;
    }


    /**
     * Sets the legalRepresentative value for this TraveAgenciesBasic.
     * 
     * @param legalRepresentative
     */
    public void setLegalRepresentative(java.lang.String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }


    /**
     * Gets the orcanRegistertime value for this TraveAgenciesBasic.
     * 
     * @return orcanRegistertime
     */
    public java.util.Calendar getOrcanRegistertime() {
        return orcanRegistertime;
    }


    /**
     * Sets the orcanRegistertime value for this TraveAgenciesBasic.
     * 
     * @param orcanRegistertime
     */
    public void setOrcanRegistertime(java.util.Calendar orcanRegistertime) {
        this.orcanRegistertime = orcanRegistertime;
    }


    /**
     * Gets the countyCode value for this TraveAgenciesBasic.
     * 
     * @return countyCode
     */
    public java.lang.String getCountyCode() {
        return countyCode;
    }


    /**
     * Sets the countyCode value for this TraveAgenciesBasic.
     * 
     * @param countyCode
     */
    public void setCountyCode(java.lang.String countyCode) {
        this.countyCode = countyCode;
    }


    /**
     * Gets the provinceName value for this TraveAgenciesBasic.
     * 
     * @return provinceName
     */
    public java.lang.String getProvinceName() {
        return provinceName;
    }


    /**
     * Sets the provinceName value for this TraveAgenciesBasic.
     * 
     * @param provinceName
     */
    public void setProvinceName(java.lang.String provinceName) {
        this.provinceName = provinceName;
    }


    /**
     * Gets the cityName value for this TraveAgenciesBasic.
     * 
     * @return cityName
     */
    public java.lang.String getCityName() {
        return cityName;
    }


    /**
     * Sets the cityName value for this TraveAgenciesBasic.
     * 
     * @param cityName
     */
    public void setCityName(java.lang.String cityName) {
        this.cityName = cityName;
    }


    /**
     * Gets the countyName value for this TraveAgenciesBasic.
     * 
     * @return countyName
     */
    public java.lang.String getCountyName() {
        return countyName;
    }


    /**
     * Sets the countyName value for this TraveAgenciesBasic.
     * 
     * @param countyName
     */
    public void setCountyName(java.lang.String countyName) {
        this.countyName = countyName;
    }


    /**
     * Gets the downName value for this TraveAgenciesBasic.
     * 
     * @return downName
     */
    public java.lang.String getDownName() {
        return downName;
    }


    /**
     * Sets the downName value for this TraveAgenciesBasic.
     * 
     * @param downName
     */
    public void setDownName(java.lang.String downName) {
        this.downName = downName;
    }


    /**
     * Gets the streetName value for this TraveAgenciesBasic.
     * 
     * @return streetName
     */
    public java.lang.String getStreetName() {
        return streetName;
    }


    /**
     * Sets the streetName value for this TraveAgenciesBasic.
     * 
     * @param streetName
     */
    public void setStreetName(java.lang.String streetName) {
        this.streetName = streetName;
    }


    /**
     * Gets the areaCode value for this TraveAgenciesBasic.
     * 
     * @return areaCode
     */
    public java.lang.String getAreaCode() {
        return areaCode;
    }


    /**
     * Sets the areaCode value for this TraveAgenciesBasic.
     * 
     * @param areaCode
     */
    public void setAreaCode(java.lang.String areaCode) {
        this.areaCode = areaCode;
    }


    /**
     * Gets the telephoneNumber value for this TraveAgenciesBasic.
     * 
     * @return telephoneNumber
     */
    public java.lang.String getTelephoneNumber() {
        return telephoneNumber;
    }


    /**
     * Sets the telephoneNumber value for this TraveAgenciesBasic.
     * 
     * @param telephoneNumber
     */
    public void setTelephoneNumber(java.lang.String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }


    /**
     * Gets the extensionNumber value for this TraveAgenciesBasic.
     * 
     * @return extensionNumber
     */
    public java.lang.String getExtensionNumber() {
        return extensionNumber;
    }


    /**
     * Sets the extensionNumber value for this TraveAgenciesBasic.
     * 
     * @param extensionNumber
     */
    public void setExtensionNumber(java.lang.String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }


    /**
     * Gets the phototelephony value for this TraveAgenciesBasic.
     * 
     * @return phototelephony
     */
    public java.lang.String getPhototelephony() {
        return phototelephony;
    }


    /**
     * Sets the phototelephony value for this TraveAgenciesBasic.
     * 
     * @param phototelephony
     */
    public void setPhototelephony(java.lang.String phototelephony) {
        this.phototelephony = phototelephony;
    }


    /**
     * Gets the phototelephonyExtensionNumber value for this TraveAgenciesBasic.
     * 
     * @return phototelephonyExtensionNumber
     */
    public java.lang.String getPhototelephonyExtensionNumber() {
        return phototelephonyExtensionNumber;
    }


    /**
     * Sets the phototelephonyExtensionNumber value for this TraveAgenciesBasic.
     * 
     * @param phototelephonyExtensionNumber
     */
    public void setPhototelephonyExtensionNumber(java.lang.String phototelephonyExtensionNumber) {
        this.phototelephonyExtensionNumber = phototelephonyExtensionNumber;
    }


    /**
     * Gets the mobileTelephone value for this TraveAgenciesBasic.
     * 
     * @return mobileTelephone
     */
    public java.lang.String getMobileTelephone() {
        return mobileTelephone;
    }


    /**
     * Sets the mobileTelephone value for this TraveAgenciesBasic.
     * 
     * @param mobileTelephone
     */
    public void setMobileTelephone(java.lang.String mobileTelephone) {
        this.mobileTelephone = mobileTelephone;
    }


    /**
     * Gets the postalCode value for this TraveAgenciesBasic.
     * 
     * @return postalCode
     */
    public java.lang.String getPostalCode() {
        return postalCode;
    }


    /**
     * Sets the postalCode value for this TraveAgenciesBasic.
     * 
     * @param postalCode
     */
    public void setPostalCode(java.lang.String postalCode) {
        this.postalCode = postalCode;
    }


    /**
     * Gets the EMail value for this TraveAgenciesBasic.
     * 
     * @return EMail
     */
    public java.lang.String getEMail() {
        return EMail;
    }


    /**
     * Sets the EMail value for this TraveAgenciesBasic.
     * 
     * @param EMail
     */
    public void setEMail(java.lang.String EMail) {
        this.EMail = EMail;
    }


    /**
     * Gets the intenetUrl value for this TraveAgenciesBasic.
     * 
     * @return intenetUrl
     */
    public java.lang.String getIntenetUrl() {
        return intenetUrl;
    }


    /**
     * Sets the intenetUrl value for this TraveAgenciesBasic.
     * 
     * @param intenetUrl
     */
    public void setIntenetUrl(java.lang.String intenetUrl) {
        this.intenetUrl = intenetUrl;
    }


    /**
     * Gets the orcanType value for this TraveAgenciesBasic.
     * 
     * @return orcanType
     */
    public int getOrcanType() {
        return orcanType;
    }


    /**
     * Sets the orcanType value for this TraveAgenciesBasic.
     * 
     * @param orcanType
     */
    public void setOrcanType(int orcanType) {
        this.orcanType = orcanType;
    }


    /**
     * Gets the orcanTypeName value for this TraveAgenciesBasic.
     * 
     * @return orcanTypeName
     */
    public java.lang.String getOrcanTypeName() {
        return orcanTypeName;
    }


    /**
     * Sets the orcanTypeName value for this TraveAgenciesBasic.
     * 
     * @param orcanTypeName
     */
    public void setOrcanTypeName(java.lang.String orcanTypeName) {
        this.orcanTypeName = orcanTypeName;
    }


    /**
     * Gets the starHotel value for this TraveAgenciesBasic.
     * 
     * @return starHotel
     */
    public int getStarHotel() {
        return starHotel;
    }


    /**
     * Sets the starHotel value for this TraveAgenciesBasic.
     * 
     * @param starHotel
     */
    public void setStarHotel(int starHotel) {
        this.starHotel = starHotel;
    }


    /**
     * Gets the starHotelName value for this TraveAgenciesBasic.
     * 
     * @return starHotelName
     */
    public java.lang.String getStarHotelName() {
        return starHotelName;
    }


    /**
     * Sets the starHotelName value for this TraveAgenciesBasic.
     * 
     * @param starHotelName
     */
    public void setStarHotelName(java.lang.String starHotelName) {
        this.starHotelName = starHotelName;
    }


    /**
     * Gets the shareHolding value for this TraveAgenciesBasic.
     * 
     * @return shareHolding
     */
    public int getShareHolding() {
        return shareHolding;
    }


    /**
     * Sets the shareHolding value for this TraveAgenciesBasic.
     * 
     * @param shareHolding
     */
    public void setShareHolding(int shareHolding) {
        this.shareHolding = shareHolding;
    }


    /**
     * Gets the shareHoldingName value for this TraveAgenciesBasic.
     * 
     * @return shareHoldingName
     */
    public java.lang.String getShareHoldingName() {
        return shareHoldingName;
    }


    /**
     * Sets the shareHoldingName value for this TraveAgenciesBasic.
     * 
     * @param shareHoldingName
     */
    public void setShareHoldingName(java.lang.String shareHoldingName) {
        this.shareHoldingName = shareHoldingName;
    }


    /**
     * Gets the roomNumber value for this TraveAgenciesBasic.
     * 
     * @return roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }


    /**
     * Sets the roomNumber value for this TraveAgenciesBasic.
     * 
     * @param roomNumber
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }


    /**
     * Gets the bedNumber value for this TraveAgenciesBasic.
     * 
     * @return bedNumber
     */
    public int getBedNumber() {
        return bedNumber;
    }


    /**
     * Sets the bedNumber value for this TraveAgenciesBasic.
     * 
     * @param bedNumber
     */
    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }


    /**
     * Gets the unitLeader value for this TraveAgenciesBasic.
     * 
     * @return unitLeader
     */
    public java.lang.String getUnitLeader() {
        return unitLeader;
    }


    /**
     * Sets the unitLeader value for this TraveAgenciesBasic.
     * 
     * @param unitLeader
     */
    public void setUnitLeader(java.lang.String unitLeader) {
        this.unitLeader = unitLeader;
    }


    /**
     * Gets the operateUser value for this TraveAgenciesBasic.
     * 
     * @return operateUser
     */
    public java.lang.String getOperateUser() {
        return operateUser;
    }


    /**
     * Sets the operateUser value for this TraveAgenciesBasic.
     * 
     * @param operateUser
     */
    public void setOperateUser(java.lang.String operateUser) {
        this.operateUser = operateUser;
    }


    /**
     * Gets the operateTelephone value for this TraveAgenciesBasic.
     * 
     * @return operateTelephone
     */
    public java.lang.String getOperateTelephone() {
        return operateTelephone;
    }


    /**
     * Sets the operateTelephone value for this TraveAgenciesBasic.
     * 
     * @param operateTelephone
     */
    public void setOperateTelephone(java.lang.String operateTelephone) {
        this.operateTelephone = operateTelephone;
    }


    /**
     * Gets the operateType value for this TraveAgenciesBasic.
     * 
     * @return operateType
     */
    public java.lang.String getOperateType() {
        return operateType;
    }


    /**
     * Sets the operateType value for this TraveAgenciesBasic.
     * 
     * @param operateType
     */
    public void setOperateType(java.lang.String operateType) {
        this.operateType = operateType;
    }


    /**
     * Gets the isRatified value for this TraveAgenciesBasic.
     * 
     * @return isRatified
     */
    public int getIsRatified() {
        return isRatified;
    }


    /**
     * Sets the isRatified value for this TraveAgenciesBasic.
     * 
     * @param isRatified
     */
    public void setIsRatified(int isRatified) {
        this.isRatified = isRatified;
    }


    /**
     * Gets the ratifiedUser value for this TraveAgenciesBasic.
     * 
     * @return ratifiedUser
     */
    public java.lang.String getRatifiedUser() {
        return ratifiedUser;
    }


    /**
     * Sets the ratifiedUser value for this TraveAgenciesBasic.
     * 
     * @param ratifiedUser
     */
    public void setRatifiedUser(java.lang.String ratifiedUser) {
        this.ratifiedUser = ratifiedUser;
    }


    /**
     * Gets the ratifiedTime value for this TraveAgenciesBasic.
     * 
     * @return ratifiedTime
     */
    public java.util.Calendar getRatifiedTime() {
        return ratifiedTime;
    }


    /**
     * Sets the ratifiedTime value for this TraveAgenciesBasic.
     * 
     * @param ratifiedTime
     */
    public void setRatifiedTime(java.util.Calendar ratifiedTime) {
        this.ratifiedTime = ratifiedTime;
    }


    /**
     * Gets the createTime value for this TraveAgenciesBasic.
     * 
     * @return createTime
     */
    public java.util.Calendar getCreateTime() {
        return createTime;
    }


    /**
     * Sets the createTime value for this TraveAgenciesBasic.
     * 
     * @param createTime
     */
    public void setCreateTime(java.util.Calendar createTime) {
        this.createTime = createTime;
    }


    /**
     * Gets the updateTime value for this TraveAgenciesBasic.
     * 
     * @return updateTime
     */
    public java.util.Calendar getUpdateTime() {
        return updateTime;
    }


    /**
     * Sets the updateTime value for this TraveAgenciesBasic.
     * 
     * @param updateTime
     */
    public void setUpdateTime(java.util.Calendar updateTime) {
        this.updateTime = updateTime;
    }


    /**
     * Gets the flag value for this TraveAgenciesBasic.
     * 
     * @return flag
     */
    public int getFlag() {
        return flag;
    }


    /**
     * Sets the flag value for this TraveAgenciesBasic.
     * 
     * @param flag
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TraveAgenciesBasic)) return false;
        TraveAgenciesBasic other = (TraveAgenciesBasic) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.uuid == other.getUuid() &&
            ((this.userAccount==null && other.getUserAccount()==null) || 
             (this.userAccount!=null &&
              this.userAccount.equals(other.getUserAccount()))) &&
            ((this.userPassword==null && other.getUserPassword()==null) || 
             (this.userPassword!=null &&
              this.userPassword.equals(other.getUserPassword()))) &&
            ((this.userPassword2==null && other.getUserPassword2()==null) || 
             (this.userPassword2!=null &&
              this.userPassword2.equals(other.getUserPassword2()))) &&
            this.userRole == other.getUserRole() &&
            ((this.userRoleName==null && other.getUserRoleName()==null) || 
             (this.userRoleName!=null &&
              this.userRoleName.equals(other.getUserRoleName()))) &&
            this.orderNumber == other.getOrderNumber() &&
            ((this.orcanCode==null && other.getOrcanCode()==null) || 
             (this.orcanCode!=null &&
              this.orcanCode.equals(other.getOrcanCode()))) &&
            ((this.orcanName==null && other.getOrcanName()==null) || 
             (this.orcanName!=null &&
              this.orcanName.equals(other.getOrcanName()))) &&
            ((this.legalRepresentative==null && other.getLegalRepresentative()==null) || 
             (this.legalRepresentative!=null &&
              this.legalRepresentative.equals(other.getLegalRepresentative()))) &&
            ((this.orcanRegistertime==null && other.getOrcanRegistertime()==null) || 
             (this.orcanRegistertime!=null &&
              this.orcanRegistertime.equals(other.getOrcanRegistertime()))) &&
            ((this.countyCode==null && other.getCountyCode()==null) || 
             (this.countyCode!=null &&
              this.countyCode.equals(other.getCountyCode()))) &&
            ((this.provinceName==null && other.getProvinceName()==null) || 
             (this.provinceName!=null &&
              this.provinceName.equals(other.getProvinceName()))) &&
            ((this.cityName==null && other.getCityName()==null) || 
             (this.cityName!=null &&
              this.cityName.equals(other.getCityName()))) &&
            ((this.countyName==null && other.getCountyName()==null) || 
             (this.countyName!=null &&
              this.countyName.equals(other.getCountyName()))) &&
            ((this.downName==null && other.getDownName()==null) || 
             (this.downName!=null &&
              this.downName.equals(other.getDownName()))) &&
            ((this.streetName==null && other.getStreetName()==null) || 
             (this.streetName!=null &&
              this.streetName.equals(other.getStreetName()))) &&
            ((this.areaCode==null && other.getAreaCode()==null) || 
             (this.areaCode!=null &&
              this.areaCode.equals(other.getAreaCode()))) &&
            ((this.telephoneNumber==null && other.getTelephoneNumber()==null) || 
             (this.telephoneNumber!=null &&
              this.telephoneNumber.equals(other.getTelephoneNumber()))) &&
            ((this.extensionNumber==null && other.getExtensionNumber()==null) || 
             (this.extensionNumber!=null &&
              this.extensionNumber.equals(other.getExtensionNumber()))) &&
            ((this.phototelephony==null && other.getPhototelephony()==null) || 
             (this.phototelephony!=null &&
              this.phototelephony.equals(other.getPhototelephony()))) &&
            ((this.phototelephonyExtensionNumber==null && other.getPhototelephonyExtensionNumber()==null) || 
             (this.phototelephonyExtensionNumber!=null &&
              this.phototelephonyExtensionNumber.equals(other.getPhototelephonyExtensionNumber()))) &&
            ((this.mobileTelephone==null && other.getMobileTelephone()==null) || 
             (this.mobileTelephone!=null &&
              this.mobileTelephone.equals(other.getMobileTelephone()))) &&
            ((this.postalCode==null && other.getPostalCode()==null) || 
             (this.postalCode!=null &&
              this.postalCode.equals(other.getPostalCode()))) &&
            ((this.EMail==null && other.getEMail()==null) || 
             (this.EMail!=null &&
              this.EMail.equals(other.getEMail()))) &&
            ((this.intenetUrl==null && other.getIntenetUrl()==null) || 
             (this.intenetUrl!=null &&
              this.intenetUrl.equals(other.getIntenetUrl()))) &&
            this.orcanType == other.getOrcanType() &&
            ((this.orcanTypeName==null && other.getOrcanTypeName()==null) || 
             (this.orcanTypeName!=null &&
              this.orcanTypeName.equals(other.getOrcanTypeName()))) &&
            this.starHotel == other.getStarHotel() &&
            ((this.starHotelName==null && other.getStarHotelName()==null) || 
             (this.starHotelName!=null &&
              this.starHotelName.equals(other.getStarHotelName()))) &&
            this.shareHolding == other.getShareHolding() &&
            ((this.shareHoldingName==null && other.getShareHoldingName()==null) || 
             (this.shareHoldingName!=null &&
              this.shareHoldingName.equals(other.getShareHoldingName()))) &&
            this.roomNumber == other.getRoomNumber() &&
            this.bedNumber == other.getBedNumber() &&
            ((this.unitLeader==null && other.getUnitLeader()==null) || 
             (this.unitLeader!=null &&
              this.unitLeader.equals(other.getUnitLeader()))) &&
            ((this.operateUser==null && other.getOperateUser()==null) || 
             (this.operateUser!=null &&
              this.operateUser.equals(other.getOperateUser()))) &&
            ((this.operateTelephone==null && other.getOperateTelephone()==null) || 
             (this.operateTelephone!=null &&
              this.operateTelephone.equals(other.getOperateTelephone()))) &&
            ((this.operateType==null && other.getOperateType()==null) || 
             (this.operateType!=null &&
              this.operateType.equals(other.getOperateType()))) &&
            this.isRatified == other.getIsRatified() &&
            ((this.ratifiedUser==null && other.getRatifiedUser()==null) || 
             (this.ratifiedUser!=null &&
              this.ratifiedUser.equals(other.getRatifiedUser()))) &&
            ((this.ratifiedTime==null && other.getRatifiedTime()==null) || 
             (this.ratifiedTime!=null &&
              this.ratifiedTime.equals(other.getRatifiedTime()))) &&
            ((this.createTime==null && other.getCreateTime()==null) || 
             (this.createTime!=null &&
              this.createTime.equals(other.getCreateTime()))) &&
            ((this.updateTime==null && other.getUpdateTime()==null) || 
             (this.updateTime!=null &&
              this.updateTime.equals(other.getUpdateTime()))) &&
            this.flag == other.getFlag();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        _hashCode += getUuid();
        if (getUserAccount() != null) {
            _hashCode += getUserAccount().hashCode();
        }
        if (getUserPassword() != null) {
            _hashCode += getUserPassword().hashCode();
        }
        if (getUserPassword2() != null) {
            _hashCode += getUserPassword2().hashCode();
        }
        _hashCode += getUserRole();
        if (getUserRoleName() != null) {
            _hashCode += getUserRoleName().hashCode();
        }
        _hashCode += getOrderNumber();
        if (getOrcanCode() != null) {
            _hashCode += getOrcanCode().hashCode();
        }
        if (getOrcanName() != null) {
            _hashCode += getOrcanName().hashCode();
        }
        if (getLegalRepresentative() != null) {
            _hashCode += getLegalRepresentative().hashCode();
        }
        if (getOrcanRegistertime() != null) {
            _hashCode += getOrcanRegistertime().hashCode();
        }
        if (getCountyCode() != null) {
            _hashCode += getCountyCode().hashCode();
        }
        if (getProvinceName() != null) {
            _hashCode += getProvinceName().hashCode();
        }
        if (getCityName() != null) {
            _hashCode += getCityName().hashCode();
        }
        if (getCountyName() != null) {
            _hashCode += getCountyName().hashCode();
        }
        if (getDownName() != null) {
            _hashCode += getDownName().hashCode();
        }
        if (getStreetName() != null) {
            _hashCode += getStreetName().hashCode();
        }
        if (getAreaCode() != null) {
            _hashCode += getAreaCode().hashCode();
        }
        if (getTelephoneNumber() != null) {
            _hashCode += getTelephoneNumber().hashCode();
        }
        if (getExtensionNumber() != null) {
            _hashCode += getExtensionNumber().hashCode();
        }
        if (getPhototelephony() != null) {
            _hashCode += getPhototelephony().hashCode();
        }
        if (getPhototelephonyExtensionNumber() != null) {
            _hashCode += getPhototelephonyExtensionNumber().hashCode();
        }
        if (getMobileTelephone() != null) {
            _hashCode += getMobileTelephone().hashCode();
        }
        if (getPostalCode() != null) {
            _hashCode += getPostalCode().hashCode();
        }
        if (getEMail() != null) {
            _hashCode += getEMail().hashCode();
        }
        if (getIntenetUrl() != null) {
            _hashCode += getIntenetUrl().hashCode();
        }
        _hashCode += getOrcanType();
        if (getOrcanTypeName() != null) {
            _hashCode += getOrcanTypeName().hashCode();
        }
        _hashCode += getStarHotel();
        if (getStarHotelName() != null) {
            _hashCode += getStarHotelName().hashCode();
        }
        _hashCode += getShareHolding();
        if (getShareHoldingName() != null) {
            _hashCode += getShareHoldingName().hashCode();
        }
        _hashCode += getRoomNumber();
        _hashCode += getBedNumber();
        if (getUnitLeader() != null) {
            _hashCode += getUnitLeader().hashCode();
        }
        if (getOperateUser() != null) {
            _hashCode += getOperateUser().hashCode();
        }
        if (getOperateTelephone() != null) {
            _hashCode += getOperateTelephone().hashCode();
        }
        if (getOperateType() != null) {
            _hashCode += getOperateType().hashCode();
        }
        _hashCode += getIsRatified();
        if (getRatifiedUser() != null) {
            _hashCode += getRatifiedUser().hashCode();
        }
        if (getRatifiedTime() != null) {
            _hashCode += getRatifiedTime().hashCode();
        }
        if (getCreateTime() != null) {
            _hashCode += getCreateTime().hashCode();
        }
        if (getUpdateTime() != null) {
            _hashCode += getUpdateTime().hashCode();
        }
        _hashCode += getFlag();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TraveAgenciesBasic.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "TraveAgenciesBasic"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Uuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userPassword2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserPassword2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userRole");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserRole"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userRoleName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserRoleName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OrderNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orcanCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OrcanCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orcanName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OrcanName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("legalRepresentative");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LegalRepresentative"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orcanRegistertime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OrcanRegistertime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CountyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("provinceName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ProvinceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cityName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CityName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countyName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CountyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("downName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DownName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("streetName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "StreetName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AreaCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telephoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TelephoneNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extensionNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ExtensionNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phototelephony");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Phototelephony"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phototelephonyExtensionNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PhototelephonyExtensionNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobileTelephone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MobileTelephone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PostalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EMail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intenetUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IntenetUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orcanType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OrcanType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orcanTypeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OrcanTypeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("starHotel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "StarHotel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("starHotelName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "StarHotelName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shareHolding");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ShareHolding"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shareHoldingName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ShareHoldingName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("roomNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RoomNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bedNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BedNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitLeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UnitLeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operateUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OperateUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operateTelephone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OperateTelephone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operateType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OperateType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isRatified");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IsRatified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ratifiedUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RatifiedUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ratifiedTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RatifiedTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CreateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UpdateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Flag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
