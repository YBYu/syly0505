/**
 * GetMonthlyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class GetMonthlyResponse  implements java.io.Serializable {
    private com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult getMonthlyResult;

    public GetMonthlyResponse() {
    }

    public GetMonthlyResponse(
           com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult getMonthlyResult) {
           this.getMonthlyResult = getMonthlyResult;
    }


    /**
     * Gets the getMonthlyResult value for this GetMonthlyResponse.
     * 
     * @return getMonthlyResult
     */
    public com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult getGetMonthlyResult() {
        return getMonthlyResult;
    }


    /**
     * Sets the getMonthlyResult value for this GetMonthlyResponse.
     * 
     * @param getMonthlyResult
     */
    public void setGetMonthlyResult(com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult getMonthlyResult) {
        this.getMonthlyResult = getMonthlyResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMonthlyResponse)) return false;
        GetMonthlyResponse other = (GetMonthlyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getMonthlyResult==null && other.getGetMonthlyResult()==null) || 
             (this.getMonthlyResult!=null &&
              this.getMonthlyResult.equals(other.getGetMonthlyResult())));
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
        if (getGetMonthlyResult() != null) {
            _hashCode += getGetMonthlyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetMonthlyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetMonthlyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getMonthlyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetMonthlyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetMonthlyResponse>GetMonthlyResult"));
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