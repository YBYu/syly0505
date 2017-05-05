/**
 * ModiflyHotelResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class ModiflyHotelResponse  implements java.io.Serializable {
    private com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult modiflyHotelResult;

    public ModiflyHotelResponse() {
    }

    public ModiflyHotelResponse(
           com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult modiflyHotelResult) {
           this.modiflyHotelResult = modiflyHotelResult;
    }


    /**
     * Gets the modiflyHotelResult value for this ModiflyHotelResponse.
     * 
     * @return modiflyHotelResult
     */
    public com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult getModiflyHotelResult() {
        return modiflyHotelResult;
    }


    /**
     * Sets the modiflyHotelResult value for this ModiflyHotelResponse.
     * 
     * @param modiflyHotelResult
     */
    public void setModiflyHotelResult(com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult modiflyHotelResult) {
        this.modiflyHotelResult = modiflyHotelResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ModiflyHotelResponse)) return false;
        ModiflyHotelResponse other = (ModiflyHotelResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.modiflyHotelResult==null && other.getModiflyHotelResult()==null) || 
             (this.modiflyHotelResult!=null &&
              this.modiflyHotelResult.equals(other.getModiflyHotelResult())));
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
        if (getModiflyHotelResult() != null) {
            _hashCode += getModiflyHotelResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ModiflyHotelResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ModiflyHotelResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modiflyHotelResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ModiflyHotelResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>ModiflyHotelResponse>ModiflyHotelResult"));
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
