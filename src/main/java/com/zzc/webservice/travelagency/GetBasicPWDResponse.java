/**
 * GetBasicPWDResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class GetBasicPWDResponse  implements java.io.Serializable {
    private com.zzc.webservice.travelagency.GetBasicPWDResponseGetBasicPWDResult getBasicPWDResult;

    public GetBasicPWDResponse() {
    }

    public GetBasicPWDResponse(
           com.zzc.webservice.travelagency.GetBasicPWDResponseGetBasicPWDResult getBasicPWDResult) {
           this.getBasicPWDResult = getBasicPWDResult;
    }


    /**
     * Gets the getBasicPWDResult value for this GetBasicPWDResponse.
     * 
     * @return getBasicPWDResult
     */
    public com.zzc.webservice.travelagency.GetBasicPWDResponseGetBasicPWDResult getGetBasicPWDResult() {
        return getBasicPWDResult;
    }


    /**
     * Sets the getBasicPWDResult value for this GetBasicPWDResponse.
     * 
     * @param getBasicPWDResult
     */
    public void setGetBasicPWDResult(com.zzc.webservice.travelagency.GetBasicPWDResponseGetBasicPWDResult getBasicPWDResult) {
        this.getBasicPWDResult = getBasicPWDResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBasicPWDResponse)) return false;
        GetBasicPWDResponse other = (GetBasicPWDResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getBasicPWDResult==null && other.getGetBasicPWDResult()==null) || 
             (this.getBasicPWDResult!=null &&
              this.getBasicPWDResult.equals(other.getGetBasicPWDResult())));
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
        if (getGetBasicPWDResult() != null) {
            _hashCode += getGetBasicPWDResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetBasicPWDResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">GetBasicPWDResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getBasicPWDResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "GetBasicPWDResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">>GetBasicPWDResponse>GetBasicPWDResult"));
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
