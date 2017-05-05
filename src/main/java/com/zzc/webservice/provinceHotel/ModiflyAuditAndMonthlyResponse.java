/**
 * ModiflyAuditAndMonthlyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class ModiflyAuditAndMonthlyResponse  implements java.io.Serializable {
    private com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult modiflyAuditAndMonthlyResult;

    public ModiflyAuditAndMonthlyResponse() {
    }

    public ModiflyAuditAndMonthlyResponse(
           com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult modiflyAuditAndMonthlyResult) {
           this.modiflyAuditAndMonthlyResult = modiflyAuditAndMonthlyResult;
    }


    /**
     * Gets the modiflyAuditAndMonthlyResult value for this ModiflyAuditAndMonthlyResponse.
     * 
     * @return modiflyAuditAndMonthlyResult
     */
    public com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult getModiflyAuditAndMonthlyResult() {
        return modiflyAuditAndMonthlyResult;
    }


    /**
     * Sets the modiflyAuditAndMonthlyResult value for this ModiflyAuditAndMonthlyResponse.
     * 
     * @param modiflyAuditAndMonthlyResult
     */
    public void setModiflyAuditAndMonthlyResult(com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult modiflyAuditAndMonthlyResult) {
        this.modiflyAuditAndMonthlyResult = modiflyAuditAndMonthlyResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ModiflyAuditAndMonthlyResponse)) return false;
        ModiflyAuditAndMonthlyResponse other = (ModiflyAuditAndMonthlyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.modiflyAuditAndMonthlyResult==null && other.getModiflyAuditAndMonthlyResult()==null) || 
             (this.modiflyAuditAndMonthlyResult!=null &&
              this.modiflyAuditAndMonthlyResult.equals(other.getModiflyAuditAndMonthlyResult())));
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
        if (getModiflyAuditAndMonthlyResult() != null) {
            _hashCode += getModiflyAuditAndMonthlyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ModiflyAuditAndMonthlyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ModiflyAuditAndMonthlyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modiflyAuditAndMonthlyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ModiflyAuditAndMonthlyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>ModiflyAuditAndMonthlyResponse>ModiflyAuditAndMonthlyResult"));
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
