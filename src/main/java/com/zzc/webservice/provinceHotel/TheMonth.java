/**
 * TheMonth.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class TheMonth  implements java.io.Serializable {
    private java.lang.String id;

    private int year;

    private int month;

    private int peopleCount;

    private int dayCount;

    private java.lang.String userId;

    private java.lang.String userAccount;

    private java.lang.String orcanCode;

    private java.lang.String countyCode;

    private java.lang.String indexId;

    private java.lang.String indexName;

    private java.lang.String indexCode;

    private int sumPeopleCount;

    private int sumDayCount;

    private java.lang.String auditId;

    private int state;

    private java.util.Calendar createTime;

    public TheMonth() {
    }

    public TheMonth(
           java.lang.String id,
           int year,
           int month,
           int peopleCount,
           int dayCount,
           java.lang.String userId,
           java.lang.String userAccount,
           java.lang.String orcanCode,
           java.lang.String countyCode,
           java.lang.String indexId,
           java.lang.String indexName,
           java.lang.String indexCode,
           int sumPeopleCount,
           int sumDayCount,
           java.lang.String auditId,
           int state,
           java.util.Calendar createTime) {
           this.id = id;
           this.year = year;
           this.month = month;
           this.peopleCount = peopleCount;
           this.dayCount = dayCount;
           this.userId = userId;
           this.userAccount = userAccount;
           this.orcanCode = orcanCode;
           this.countyCode = countyCode;
           this.indexId = indexId;
           this.indexName = indexName;
           this.indexCode = indexCode;
           this.sumPeopleCount = sumPeopleCount;
           this.sumDayCount = sumDayCount;
           this.auditId = auditId;
           this.state = state;
           this.createTime = createTime;
    }


    /**
     * Gets the id value for this TheMonth.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this TheMonth.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the year value for this TheMonth.
     * 
     * @return year
     */
    public int getYear() {
        return year;
    }


    /**
     * Sets the year value for this TheMonth.
     * 
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }


    /**
     * Gets the month value for this TheMonth.
     * 
     * @return month
     */
    public int getMonth() {
        return month;
    }


    /**
     * Sets the month value for this TheMonth.
     * 
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }


    /**
     * Gets the peopleCount value for this TheMonth.
     * 
     * @return peopleCount
     */
    public int getPeopleCount() {
        return peopleCount;
    }


    /**
     * Sets the peopleCount value for this TheMonth.
     * 
     * @param peopleCount
     */
    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }


    /**
     * Gets the dayCount value for this TheMonth.
     * 
     * @return dayCount
     */
    public int getDayCount() {
        return dayCount;
    }


    /**
     * Sets the dayCount value for this TheMonth.
     * 
     * @param dayCount
     */
    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }


    /**
     * Gets the userId value for this TheMonth.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this TheMonth.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the userAccount value for this TheMonth.
     * 
     * @return userAccount
     */
    public java.lang.String getUserAccount() {
        return userAccount;
    }


    /**
     * Sets the userAccount value for this TheMonth.
     * 
     * @param userAccount
     */
    public void setUserAccount(java.lang.String userAccount) {
        this.userAccount = userAccount;
    }


    /**
     * Gets the orcanCode value for this TheMonth.
     * 
     * @return orcanCode
     */
    public java.lang.String getOrcanCode() {
        return orcanCode;
    }


    /**
     * Sets the orcanCode value for this TheMonth.
     * 
     * @param orcanCode
     */
    public void setOrcanCode(java.lang.String orcanCode) {
        this.orcanCode = orcanCode;
    }


    /**
     * Gets the countyCode value for this TheMonth.
     * 
     * @return countyCode
     */
    public java.lang.String getCountyCode() {
        return countyCode;
    }


    /**
     * Sets the countyCode value for this TheMonth.
     * 
     * @param countyCode
     */
    public void setCountyCode(java.lang.String countyCode) {
        this.countyCode = countyCode;
    }


    /**
     * Gets the indexId value for this TheMonth.
     * 
     * @return indexId
     */
    public java.lang.String getIndexId() {
        return indexId;
    }


    /**
     * Sets the indexId value for this TheMonth.
     * 
     * @param indexId
     */
    public void setIndexId(java.lang.String indexId) {
        this.indexId = indexId;
    }


    /**
     * Gets the indexName value for this TheMonth.
     * 
     * @return indexName
     */
    public java.lang.String getIndexName() {
        return indexName;
    }


    /**
     * Sets the indexName value for this TheMonth.
     * 
     * @param indexName
     */
    public void setIndexName(java.lang.String indexName) {
        this.indexName = indexName;
    }


    /**
     * Gets the indexCode value for this TheMonth.
     * 
     * @return indexCode
     */
    public java.lang.String getIndexCode() {
        return indexCode;
    }


    /**
     * Sets the indexCode value for this TheMonth.
     * 
     * @param indexCode
     */
    public void setIndexCode(java.lang.String indexCode) {
        this.indexCode = indexCode;
    }


    /**
     * Gets the sumPeopleCount value for this TheMonth.
     * 
     * @return sumPeopleCount
     */
    public int getSumPeopleCount() {
        return sumPeopleCount;
    }


    /**
     * Sets the sumPeopleCount value for this TheMonth.
     * 
     * @param sumPeopleCount
     */
    public void setSumPeopleCount(int sumPeopleCount) {
        this.sumPeopleCount = sumPeopleCount;
    }


    /**
     * Gets the sumDayCount value for this TheMonth.
     * 
     * @return sumDayCount
     */
    public int getSumDayCount() {
        return sumDayCount;
    }


    /**
     * Sets the sumDayCount value for this TheMonth.
     * 
     * @param sumDayCount
     */
    public void setSumDayCount(int sumDayCount) {
        this.sumDayCount = sumDayCount;
    }


    /**
     * Gets the auditId value for this TheMonth.
     * 
     * @return auditId
     */
    public java.lang.String getAuditId() {
        return auditId;
    }


    /**
     * Sets the auditId value for this TheMonth.
     * 
     * @param auditId
     */
    public void setAuditId(java.lang.String auditId) {
        this.auditId = auditId;
    }


    /**
     * Gets the state value for this TheMonth.
     * 
     * @return state
     */
    public int getState() {
        return state;
    }


    /**
     * Sets the state value for this TheMonth.
     * 
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }


    /**
     * Gets the createTime value for this TheMonth.
     * 
     * @return createTime
     */
    public java.util.Calendar getCreateTime() {
        return createTime;
    }


    /**
     * Sets the createTime value for this TheMonth.
     * 
     * @param createTime
     */
    public void setCreateTime(java.util.Calendar createTime) {
        this.createTime = createTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TheMonth)) return false;
        TheMonth other = (TheMonth) obj;
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
            this.year == other.getYear() &&
            this.month == other.getMonth() &&
            this.peopleCount == other.getPeopleCount() &&
            this.dayCount == other.getDayCount() &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.userAccount==null && other.getUserAccount()==null) || 
             (this.userAccount!=null &&
              this.userAccount.equals(other.getUserAccount()))) &&
            ((this.orcanCode==null && other.getOrcanCode()==null) || 
             (this.orcanCode!=null &&
              this.orcanCode.equals(other.getOrcanCode()))) &&
            ((this.countyCode==null && other.getCountyCode()==null) || 
             (this.countyCode!=null &&
              this.countyCode.equals(other.getCountyCode()))) &&
            ((this.indexId==null && other.getIndexId()==null) || 
             (this.indexId!=null &&
              this.indexId.equals(other.getIndexId()))) &&
            ((this.indexName==null && other.getIndexName()==null) || 
             (this.indexName!=null &&
              this.indexName.equals(other.getIndexName()))) &&
            ((this.indexCode==null && other.getIndexCode()==null) || 
             (this.indexCode!=null &&
              this.indexCode.equals(other.getIndexCode()))) &&
            this.sumPeopleCount == other.getSumPeopleCount() &&
            this.sumDayCount == other.getSumDayCount() &&
            ((this.auditId==null && other.getAuditId()==null) || 
             (this.auditId!=null &&
              this.auditId.equals(other.getAuditId()))) &&
            this.state == other.getState() &&
            ((this.createTime==null && other.getCreateTime()==null) || 
             (this.createTime!=null &&
              this.createTime.equals(other.getCreateTime())));
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
        _hashCode += getYear();
        _hashCode += getMonth();
        _hashCode += getPeopleCount();
        _hashCode += getDayCount();
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getUserAccount() != null) {
            _hashCode += getUserAccount().hashCode();
        }
        if (getOrcanCode() != null) {
            _hashCode += getOrcanCode().hashCode();
        }
        if (getCountyCode() != null) {
            _hashCode += getCountyCode().hashCode();
        }
        if (getIndexId() != null) {
            _hashCode += getIndexId().hashCode();
        }
        if (getIndexName() != null) {
            _hashCode += getIndexName().hashCode();
        }
        if (getIndexCode() != null) {
            _hashCode += getIndexCode().hashCode();
        }
        _hashCode += getSumPeopleCount();
        _hashCode += getSumDayCount();
        if (getAuditId() != null) {
            _hashCode += getAuditId().hashCode();
        }
        _hashCode += getState();
        if (getCreateTime() != null) {
            _hashCode += getCreateTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TheMonth.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "TheMonth"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Id"));
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
        elemField.setFieldName("peopleCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PeopleCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dayCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DayCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("userAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("countyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CountyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indexId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IndexId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indexName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IndexName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indexCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IndexCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sumPeopleCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SumPeopleCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sumDayCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SumDayCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auditId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AuditId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "State"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CreateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
