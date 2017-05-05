/**
 * HotelEstimateInMonth.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class HotelEstimateInMonth  implements java.io.Serializable {
    private java.lang.String countryCode;

    private java.lang.Integer year;

    private java.lang.Integer month;

    private com.zzc.webservice.provinceHotel.HotelEstimateValues peopleNum;

    private com.zzc.webservice.provinceHotel.HotelEstimateValues peopleDay;

    private java.lang.String optUser;

    public HotelEstimateInMonth() {
    }

    public HotelEstimateInMonth(
           java.lang.String countryCode,
           java.lang.Integer year,
           java.lang.Integer month,
           com.zzc.webservice.provinceHotel.HotelEstimateValues peopleNum,
           com.zzc.webservice.provinceHotel.HotelEstimateValues peopleDay,
           java.lang.String optUser) {
           this.countryCode = countryCode;
           this.year = year;
           this.month = month;
           this.peopleNum = peopleNum;
           this.peopleDay = peopleDay;
           this.optUser = optUser;
    }


    /**
     * Gets the countryCode value for this HotelEstimateInMonth.
     * 
     * @return countryCode
     */
    public java.lang.String getCountryCode() {
        return countryCode;
    }


    /**
     * Sets the countryCode value for this HotelEstimateInMonth.
     * 
     * @param countryCode
     */
    public void setCountryCode(java.lang.String countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * Gets the year value for this HotelEstimateInMonth.
     * 
     * @return year
     */
    public java.lang.Integer getYear() {
        return year;
    }


    /**
     * Sets the year value for this HotelEstimateInMonth.
     * 
     * @param year
     */
    public void setYear(java.lang.Integer year) {
        this.year = year;
    }


    /**
     * Gets the month value for this HotelEstimateInMonth.
     * 
     * @return month
     */
    public java.lang.Integer getMonth() {
        return month;
    }


    /**
     * Sets the month value for this HotelEstimateInMonth.
     * 
     * @param month
     */
    public void setMonth(java.lang.Integer month) {
        this.month = month;
    }


    /**
     * Gets the peopleNum value for this HotelEstimateInMonth.
     * 
     * @return peopleNum
     */
    public com.zzc.webservice.provinceHotel.HotelEstimateValues getPeopleNum() {
        return peopleNum;
    }


    /**
     * Sets the peopleNum value for this HotelEstimateInMonth.
     * 
     * @param peopleNum
     */
    public void setPeopleNum(com.zzc.webservice.provinceHotel.HotelEstimateValues peopleNum) {
        this.peopleNum = peopleNum;
    }


    /**
     * Gets the peopleDay value for this HotelEstimateInMonth.
     * 
     * @return peopleDay
     */
    public com.zzc.webservice.provinceHotel.HotelEstimateValues getPeopleDay() {
        return peopleDay;
    }


    /**
     * Sets the peopleDay value for this HotelEstimateInMonth.
     * 
     * @param peopleDay
     */
    public void setPeopleDay(com.zzc.webservice.provinceHotel.HotelEstimateValues peopleDay) {
        this.peopleDay = peopleDay;
    }


    /**
     * Gets the optUser value for this HotelEstimateInMonth.
     * 
     * @return optUser
     */
    public java.lang.String getOptUser() {
        return optUser;
    }


    /**
     * Sets the optUser value for this HotelEstimateInMonth.
     * 
     * @param optUser
     */
    public void setOptUser(java.lang.String optUser) {
        this.optUser = optUser;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HotelEstimateInMonth)) return false;
        HotelEstimateInMonth other = (HotelEstimateInMonth) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.countryCode==null && other.getCountryCode()==null) || 
             (this.countryCode!=null &&
              this.countryCode.equals(other.getCountryCode()))) &&
            ((this.year==null && other.getYear()==null) || 
             (this.year!=null &&
              this.year.equals(other.getYear()))) &&
            ((this.month==null && other.getMonth()==null) || 
             (this.month!=null &&
              this.month.equals(other.getMonth()))) &&
            ((this.peopleNum==null && other.getPeopleNum()==null) || 
             (this.peopleNum!=null &&
              this.peopleNum.equals(other.getPeopleNum()))) &&
            ((this.peopleDay==null && other.getPeopleDay()==null) || 
             (this.peopleDay!=null &&
              this.peopleDay.equals(other.getPeopleDay()))) &&
            ((this.optUser==null && other.getOptUser()==null) || 
             (this.optUser!=null &&
              this.optUser.equals(other.getOptUser())));
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
        if (getCountryCode() != null) {
            _hashCode += getCountryCode().hashCode();
        }
        if (getYear() != null) {
            _hashCode += getYear().hashCode();
        }
        if (getMonth() != null) {
            _hashCode += getMonth().hashCode();
        }
        if (getPeopleNum() != null) {
            _hashCode += getPeopleNum().hashCode();
        }
        if (getPeopleDay() != null) {
            _hashCode += getPeopleDay().hashCode();
        }
        if (getOptUser() != null) {
            _hashCode += getOptUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HotelEstimateInMonth.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimateInMonth"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CountryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("year");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Year"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("month");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Month"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("peopleNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PeopleNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimateValues"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("peopleDay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PeopleDay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimateValues"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("optUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OptUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
