/**
 * AddDragonBoatFestival.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class AddDragonBoatFestival  implements java.io.Serializable {
    private java.lang.String key;

    private com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dBFInfo;

    private java.lang.String userAccount;

    public AddDragonBoatFestival() {
    }

    public AddDragonBoatFestival(
           java.lang.String key,
           com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dBFInfo,
           java.lang.String userAccount) {
           this.key = key;
           this.dBFInfo = dBFInfo;
           this.userAccount = userAccount;
    }


    /**
     * Gets the key value for this AddDragonBoatFestival.
     * 
     * @return key
     */
    public java.lang.String getKey() {
        return key;
    }


    /**
     * Sets the key value for this AddDragonBoatFestival.
     * 
     * @param key
     */
    public void setKey(java.lang.String key) {
        this.key = key;
    }


    /**
     * Gets the dBFInfo value for this AddDragonBoatFestival.
     * 
     * @return dBFInfo
     */
    public com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo getDBFInfo() {
        return dBFInfo;
    }


    /**
     * Sets the dBFInfo value for this AddDragonBoatFestival.
     * 
     * @param dBFInfo
     */
    public void setDBFInfo(com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dBFInfo) {
        this.dBFInfo = dBFInfo;
    }


    /**
     * Gets the userAccount value for this AddDragonBoatFestival.
     * 
     * @return userAccount
     */
    public java.lang.String getUserAccount() {
        return userAccount;
    }


    /**
     * Sets the userAccount value for this AddDragonBoatFestival.
     * 
     * @param userAccount
     */
    public void setUserAccount(java.lang.String userAccount) {
        this.userAccount = userAccount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddDragonBoatFestival)) return false;
        AddDragonBoatFestival other = (AddDragonBoatFestival) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.dBFInfo==null && other.getDBFInfo()==null) || 
             (this.dBFInfo!=null &&
              this.dBFInfo.equals(other.getDBFInfo()))) &&
            ((this.userAccount==null && other.getUserAccount()==null) || 
             (this.userAccount!=null &&
              this.userAccount.equals(other.getUserAccount())));
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getDBFInfo() != null) {
            _hashCode += getDBFInfo().hashCode();
        }
        if (getUserAccount() != null) {
            _hashCode += getUserAccount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddDragonBoatFestival.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AddDragonBoatFestival"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DBFInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "dBFInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "DragonBoatFestivalInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "userAccount"));
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
