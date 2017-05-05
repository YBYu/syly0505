/**
 * GetFinanceStateResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class GetFinanceStateResponse  implements java.io.Serializable {
    private com.zzc.webservice.travelagency.GetFinanceStateResponseGetFinanceStateResult getFinanceStateResult;

    public GetFinanceStateResponse() {
    }

    public GetFinanceStateResponse(
           com.zzc.webservice.travelagency.GetFinanceStateResponseGetFinanceStateResult getFinanceStateResult) {
           this.getFinanceStateResult = getFinanceStateResult;
    }


    /**
     * Gets the getFinanceStateResult value for this GetFinanceStateResponse.
     * 
     * @return getFinanceStateResult
     */
    public com.zzc.webservice.travelagency.GetFinanceStateResponseGetFinanceStateResult getGetFinanceStateResult() {
        return getFinanceStateResult;
    }


    /**
     * Sets the getFinanceStateResult value for this GetFinanceStateResponse.
     * 
     * @param getFinanceStateResult
     */
    public void setGetFinanceStateResult(com.zzc.webservice.travelagency.GetFinanceStateResponseGetFinanceStateResult getFinanceStateResult) {
        this.getFinanceStateResult = getFinanceStateResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetFinanceStateResponse)) return false;
        GetFinanceStateResponse other = (GetFinanceStateResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getFinanceStateResult==null && other.getGetFinanceStateResult()==null) || 
             (this.getFinanceStateResult!=null &&
              this.getFinanceStateResult.equals(other.getGetFinanceStateResult())));
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
        if (getGetFinanceStateResult() != null) {
            _hashCode += getGetFinanceStateResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetFinanceStateResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">GetFinanceStateResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getFinanceStateResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "GetFinanceStateResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">>GetFinanceStateResponse>GetFinanceStateResult"));
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
