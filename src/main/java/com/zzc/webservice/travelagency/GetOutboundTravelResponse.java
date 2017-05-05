/**
 * GetOutboundTravelResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class GetOutboundTravelResponse  implements java.io.Serializable {
    private com.zzc.webservice.travelagency.GetOutboundTravelResponseGetOutboundTravelResult getOutboundTravelResult;

    public GetOutboundTravelResponse() {
    }

    public GetOutboundTravelResponse(
           com.zzc.webservice.travelagency.GetOutboundTravelResponseGetOutboundTravelResult getOutboundTravelResult) {
           this.getOutboundTravelResult = getOutboundTravelResult;
    }


    /**
     * Gets the getOutboundTravelResult value for this GetOutboundTravelResponse.
     * 
     * @return getOutboundTravelResult
     */
    public com.zzc.webservice.travelagency.GetOutboundTravelResponseGetOutboundTravelResult getGetOutboundTravelResult() {
        return getOutboundTravelResult;
    }


    /**
     * Sets the getOutboundTravelResult value for this GetOutboundTravelResponse.
     * 
     * @param getOutboundTravelResult
     */
    public void setGetOutboundTravelResult(com.zzc.webservice.travelagency.GetOutboundTravelResponseGetOutboundTravelResult getOutboundTravelResult) {
        this.getOutboundTravelResult = getOutboundTravelResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetOutboundTravelResponse)) return false;
        GetOutboundTravelResponse other = (GetOutboundTravelResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getOutboundTravelResult==null && other.getGetOutboundTravelResult()==null) || 
             (this.getOutboundTravelResult!=null &&
              this.getOutboundTravelResult.equals(other.getGetOutboundTravelResult())));
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
        if (getGetOutboundTravelResult() != null) {
            _hashCode += getGetOutboundTravelResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetOutboundTravelResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">GetOutboundTravelResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getOutboundTravelResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "GetOutboundTravelResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">>GetOutboundTravelResponse>GetOutboundTravelResult"));
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
