/**
 * GetDragonBoatFestival.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class GetDragonBoatFestival  implements java.io.Serializable {
    private java.lang.String key;

    private com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dragonBoatFestivalInfo;

    public GetDragonBoatFestival() {
    }

    public GetDragonBoatFestival(
           java.lang.String key,
           com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dragonBoatFestivalInfo) {
           this.key = key;
           this.dragonBoatFestivalInfo = dragonBoatFestivalInfo;
    }


    /**
     * Gets the key value for this GetDragonBoatFestival.
     * 
     * @return key
     */
    public java.lang.String getKey() {
        return key;
    }


    /**
     * Sets the key value for this GetDragonBoatFestival.
     * 
     * @param key
     */
    public void setKey(java.lang.String key) {
        this.key = key;
    }


    /**
     * Gets the dragonBoatFestivalInfo value for this GetDragonBoatFestival.
     * 
     * @return dragonBoatFestivalInfo
     */
    public com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo getDragonBoatFestivalInfo() {
        return dragonBoatFestivalInfo;
    }


    /**
     * Sets the dragonBoatFestivalInfo value for this GetDragonBoatFestival.
     * 
     * @param dragonBoatFestivalInfo
     */
    public void setDragonBoatFestivalInfo(com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dragonBoatFestivalInfo) {
        this.dragonBoatFestivalInfo = dragonBoatFestivalInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDragonBoatFestival)) return false;
        GetDragonBoatFestival other = (GetDragonBoatFestival) obj;
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
            ((this.dragonBoatFestivalInfo==null && other.getDragonBoatFestivalInfo()==null) || 
             (this.dragonBoatFestivalInfo!=null &&
              this.dragonBoatFestivalInfo.equals(other.getDragonBoatFestivalInfo())));
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
        if (getDragonBoatFestivalInfo() != null) {
            _hashCode += getDragonBoatFestivalInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDragonBoatFestival.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetDragonBoatFestival"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dragonBoatFestivalInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DragonBoatFestivalInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "DragonBoatFestivalInfo"));
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
