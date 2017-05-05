/**
 * Audit.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class Audit  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String userId;

    private int year;

    private int month;

    private int state;

    private int areaLevel;

    private java.lang.String createUser;

    private java.lang.String phone;

    private java.math.BigDecimal realityCount;

    private java.math.BigDecimal maxCount;

    private java.math.BigDecimal hireRate;

    private com.zzc.webservice.provinceHotel.TraveAgenciesBasic TAB;

    private java.util.Calendar createTime;

    private java.lang.String countyCode;

    private com.zzc.webservice.provinceHotel.TheMonth[] theMonthList;

    public Audit() {
    }

    public Audit(
           java.lang.String id,
           java.lang.String userId,
           int year,
           int month,
           int state,
           int areaLevel,
           java.lang.String createUser,
           java.lang.String phone,
           java.math.BigDecimal realityCount,
           java.math.BigDecimal maxCount,
           java.math.BigDecimal hireRate,
           com.zzc.webservice.provinceHotel.TraveAgenciesBasic TAB,
           java.util.Calendar createTime,
           java.lang.String countyCode,
           com.zzc.webservice.provinceHotel.TheMonth[] theMonthList) {
           this.id = id;
           this.userId = userId;
           this.year = year;
           this.month = month;
           this.state = state;
           this.areaLevel = areaLevel;
           this.createUser = createUser;
           this.phone = phone;
           this.realityCount = realityCount;
           this.maxCount = maxCount;
           this.hireRate = hireRate;
           this.TAB = TAB;
           this.createTime = createTime;
           this.countyCode = countyCode;
           this.theMonthList = theMonthList;
    }


    /**
     * Gets the id value for this Audit.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Audit.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the userId value for this Audit.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this Audit.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the year value for this Audit.
     * 
     * @return year
     */
    public int getYear() {
        return year;
    }


    /**
     * Sets the year value for this Audit.
     * 
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }


    /**
     * Gets the month value for this Audit.
     * 
     * @return month
     */
    public int getMonth() {
        return month;
    }


    /**
     * Sets the month value for this Audit.
     * 
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }


    /**
     * Gets the state value for this Audit.
     * 
     * @return state
     */
    public int getState() {
        return state;
    }


    /**
     * Sets the state value for this Audit.
     * 
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }


    /**
     * Gets the areaLevel value for this Audit.
     * 
     * @return areaLevel
     */
    public int getAreaLevel() {
        return areaLevel;
    }


    /**
     * Sets the areaLevel value for this Audit.
     * 
     * @param areaLevel
     */
    public void setAreaLevel(int areaLevel) {
        this.areaLevel = areaLevel;
    }


    /**
     * Gets the createUser value for this Audit.
     * 
     * @return createUser
     */
    public java.lang.String getCreateUser() {
        return createUser;
    }


    /**
     * Sets the createUser value for this Audit.
     * 
     * @param createUser
     */
    public void setCreateUser(java.lang.String createUser) {
        this.createUser = createUser;
    }


    /**
     * Gets the phone value for this Audit.
     * 
     * @return phone
     */
    public java.lang.String getPhone() {
        return phone;
    }


    /**
     * Sets the phone value for this Audit.
     * 
     * @param phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


    /**
     * Gets the realityCount value for this Audit.
     * 
     * @return realityCount
     */
    public java.math.BigDecimal getRealityCount() {
        return realityCount;
    }


    /**
     * Sets the realityCount value for this Audit.
     * 
     * @param realityCount
     */
    public void setRealityCount(java.math.BigDecimal realityCount) {
        this.realityCount = realityCount;
    }


    /**
     * Gets the maxCount value for this Audit.
     * 
     * @return maxCount
     */
    public java.math.BigDecimal getMaxCount() {
        return maxCount;
    }


    /**
     * Sets the maxCount value for this Audit.
     * 
     * @param maxCount
     */
    public void setMaxCount(java.math.BigDecimal maxCount) {
        this.maxCount = maxCount;
    }


    /**
     * Gets the hireRate value for this Audit.
     * 
     * @return hireRate
     */
    public java.math.BigDecimal getHireRate() {
        return hireRate;
    }


    /**
     * Sets the hireRate value for this Audit.
     * 
     * @param hireRate
     */
    public void setHireRate(java.math.BigDecimal hireRate) {
        this.hireRate = hireRate;
    }


    /**
     * Gets the TAB value for this Audit.
     * 
     * @return TAB
     */
    public com.zzc.webservice.provinceHotel.TraveAgenciesBasic getTAB() {
        return TAB;
    }


    /**
     * Sets the TAB value for this Audit.
     * 
     * @param TAB
     */
    public void setTAB(com.zzc.webservice.provinceHotel.TraveAgenciesBasic TAB) {
        this.TAB = TAB;
    }


    /**
     * Gets the createTime value for this Audit.
     * 
     * @return createTime
     */
    public java.util.Calendar getCreateTime() {
        return createTime;
    }


    /**
     * Sets the createTime value for this Audit.
     * 
     * @param createTime
     */
    public void setCreateTime(java.util.Calendar createTime) {
        this.createTime = createTime;
    }


    /**
     * Gets the countyCode value for this Audit.
     * 
     * @return countyCode
     */
    public java.lang.String getCountyCode() {
        return countyCode;
    }


    /**
     * Sets the countyCode value for this Audit.
     * 
     * @param countyCode
     */
    public void setCountyCode(java.lang.String countyCode) {
        this.countyCode = countyCode;
    }


    /**
     * Gets the theMonthList value for this Audit.
     * 
     * @return theMonthList
     */
    public com.zzc.webservice.provinceHotel.TheMonth[] getTheMonthList() {
        return theMonthList;
    }


    /**
     * Sets the theMonthList value for this Audit.
     * 
     * @param theMonthList
     */
    public void setTheMonthList(com.zzc.webservice.provinceHotel.TheMonth[] theMonthList) {
        this.theMonthList = theMonthList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Audit)) return false;
        Audit other = (Audit) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            this.year == other.getYear() &&
            this.month == other.getMonth() &&
            this.state == other.getState() &&
            this.areaLevel == other.getAreaLevel() &&
            ((this.createUser==null && other.getCreateUser()==null) || 
             (this.createUser!=null &&
              this.createUser.equals(other.getCreateUser()))) &&
            ((this.phone==null && other.getPhone()==null) || 
             (this.phone!=null &&
              this.phone.equals(other.getPhone()))) &&
            ((this.realityCount==null && other.getRealityCount()==null) || 
             (this.realityCount!=null &&
              this.realityCount.equals(other.getRealityCount()))) &&
            ((this.maxCount==null && other.getMaxCount()==null) || 
             (this.maxCount!=null &&
              this.maxCount.equals(other.getMaxCount()))) &&
            ((this.hireRate==null && other.getHireRate()==null) || 
             (this.hireRate!=null &&
              this.hireRate.equals(other.getHireRate()))) &&
            ((this.TAB==null && other.getTAB()==null) || 
             (this.TAB!=null &&
              this.TAB.equals(other.getTAB()))) &&
            ((this.createTime==null && other.getCreateTime()==null) || 
             (this.createTime!=null &&
              this.createTime.equals(other.getCreateTime()))) &&
            ((this.countyCode==null && other.getCountyCode()==null) || 
             (this.countyCode!=null &&
              this.countyCode.equals(other.getCountyCode()))) &&
            ((this.theMonthList==null && other.getTheMonthList()==null) || 
             (this.theMonthList!=null &&
              java.util.Arrays.equals(this.theMonthList, other.getTheMonthList())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        _hashCode += getYear();
        _hashCode += getMonth();
        _hashCode += getState();
        _hashCode += getAreaLevel();
        if (getCreateUser() != null) {
            _hashCode += getCreateUser().hashCode();
        }
        if (getPhone() != null) {
            _hashCode += getPhone().hashCode();
        }
        if (getRealityCount() != null) {
            _hashCode += getRealityCount().hashCode();
        }
        if (getMaxCount() != null) {
            _hashCode += getMaxCount().hashCode();
        }
        if (getHireRate() != null) {
            _hashCode += getHireRate().hashCode();
        }
        if (getTAB() != null) {
            _hashCode += getTAB().hashCode();
        }
        if (getCreateTime() != null) {
            _hashCode += getCreateTime().hashCode();
        }
        if (getCountyCode() != null) {
            _hashCode += getCountyCode().hashCode();
        }
        if (getTheMonthList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTheMonthList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTheMonthList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Audit.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Audit"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("year");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Year"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("month");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Month"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "State"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AreaLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CreateUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Phone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("realityCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RealityCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MaxCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hireRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "HireRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TAB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TAB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "TraveAgenciesBasic"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CreateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CountyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("theMonthList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TheMonthList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "TheMonth"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "TheMonth"));
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
