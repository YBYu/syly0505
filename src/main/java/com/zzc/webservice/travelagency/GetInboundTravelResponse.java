/**
 * GetInboundTravelResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class GetInboundTravelResponse  implements java.io.Serializable {
    private com.zzc.webservice.travelagency.GetInboundTravelResponseGetInboundTravelResult getInboundTravelResult;

    public GetInboundTravelResponse() {
    }

    public GetInboundTravelResponse(
           com.zzc.webservice.travelagency.GetInboundTravelResponseGetInboundTravelResult getInboundTravelResult) {
           this.getInboundTravelResult = getInboundTravelResult;
    }


    /**
     * Gets the getInboundTravelResult value for this GetInboundTravelResponse.
     * 
     * @return getInboundTravelResult
     */
    public com.zzc.webservice.travelagency.GetInboundTravelResponseGetInboundTravelResult getGetInboundTravelResult() {
        return getInboundTravelResult;
    }


    /**
     * Sets the getInboundTravelResult value for this GetInboundTravelResponse.
     * 
     * @param getInboundTravelResult
     */
    public void setGetInboundTravelResult(com.zzc.webservice.travelagency.GetInboundTravelResponseGetInboundTravelResult getInboundTravelResult) {
        this.getInboundTravelResult = getInboundTravelResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetInboundTravelResponse)) return false;
        GetInboundTravelResponse other = (GetInboundTravelResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getInboundTravelResult==null && other.getGetInboundTravelResult()==null) || 
             (this.getInboundTravelResult!=null &&
              this.getInboundTravelResult.equals(other.getGetInboundTravelResult())));
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
        if (getGetInboundTravelResult() != null) {
            _hashCode += getGetInboundTravelResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetInboundTravelResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">GetInboundTravelResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getInboundTravelResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "GetInboundTravelResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">>GetInboundTravelResponse>GetInboundTravelResult"));
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
