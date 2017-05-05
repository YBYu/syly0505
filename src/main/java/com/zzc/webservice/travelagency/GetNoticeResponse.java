/**
 * GetNoticeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class GetNoticeResponse  implements java.io.Serializable {
    private com.zzc.webservice.travelagency.GetNoticeResponseGetNoticeResult getNoticeResult;

    public GetNoticeResponse() {
    }

    public GetNoticeResponse(
           com.zzc.webservice.travelagency.GetNoticeResponseGetNoticeResult getNoticeResult) {
           this.getNoticeResult = getNoticeResult;
    }


    /**
     * Gets the getNoticeResult value for this GetNoticeResponse.
     * 
     * @return getNoticeResult
     */
    public com.zzc.webservice.travelagency.GetNoticeResponseGetNoticeResult getGetNoticeResult() {
        return getNoticeResult;
    }


    /**
     * Sets the getNoticeResult value for this GetNoticeResponse.
     * 
     * @param getNoticeResult
     */
    public void setGetNoticeResult(com.zzc.webservice.travelagency.GetNoticeResponseGetNoticeResult getNoticeResult) {
        this.getNoticeResult = getNoticeResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNoticeResponse)) return false;
        GetNoticeResponse other = (GetNoticeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getNoticeResult==null && other.getGetNoticeResult()==null) || 
             (this.getNoticeResult!=null &&
              this.getNoticeResult.equals(other.getGetNoticeResult())));
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
        if (getGetNoticeResult() != null) {
            _hashCode += getGetNoticeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetNoticeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">GetNoticeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getNoticeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "GetNoticeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">>GetNoticeResponse>GetNoticeResult"));
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
