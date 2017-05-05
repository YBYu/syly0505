/**
 * GetFinanceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class GetFinanceResponse  implements java.io.Serializable {
    private com.zzc.webservice.travelagency.GetFinanceResponseGetFinanceResult getFinanceResult;

    public GetFinanceResponse() {
    }

    public GetFinanceResponse(
           com.zzc.webservice.travelagency.GetFinanceResponseGetFinanceResult getFinanceResult) {
           this.getFinanceResult = getFinanceResult;
    }


    /**
     * Gets the getFinanceResult value for this GetFinanceResponse.
     * 
     * @return getFinanceResult
     */
    public com.zzc.webservice.travelagency.GetFinanceResponseGetFinanceResult getGetFinanceResult() {
        return getFinanceResult;
    }


    /**
     * Sets the getFinanceResult value for this GetFinanceResponse.
     * 
     * @param getFinanceResult
     */
    public void setGetFinanceResult(com.zzc.webservice.travelagency.GetFinanceResponseGetFinanceResult getFinanceResult) {
        this.getFinanceResult = getFinanceResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetFinanceResponse)) return false;
        GetFinanceResponse other = (GetFinanceResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getFinanceResult==null && other.getGetFinanceResult()==null) || 
             (this.getFinanceResult!=null &&
              this.getFinanceResult.equals(other.getGetFinanceResult())));
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
        if (getGetFinanceResult() != null) {
            _hashCode += getGetFinanceResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetFinanceResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">GetFinanceResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getFinanceResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "GetFinanceResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">>GetFinanceResponse>GetFinanceResult"));
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
