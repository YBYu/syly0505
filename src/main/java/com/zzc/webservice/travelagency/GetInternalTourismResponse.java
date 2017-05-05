/**
 * GetInternalTourismResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class GetInternalTourismResponse  implements java.io.Serializable {
    private com.zzc.webservice.travelagency.GetInternalTourismResponseGetInternalTourismResult getInternalTourismResult;

    public GetInternalTourismResponse() {
    }

    public GetInternalTourismResponse(
           com.zzc.webservice.travelagency.GetInternalTourismResponseGetInternalTourismResult getInternalTourismResult) {
           this.getInternalTourismResult = getInternalTourismResult;
    }


    /**
     * Gets the getInternalTourismResult value for this GetInternalTourismResponse.
     * 
     * @return getInternalTourismResult
     */
    public com.zzc.webservice.travelagency.GetInternalTourismResponseGetInternalTourismResult getGetInternalTourismResult() {
        return getInternalTourismResult;
    }


    /**
     * Sets the getInternalTourismResult value for this GetInternalTourismResponse.
     * 
     * @param getInternalTourismResult
     */
    public void setGetInternalTourismResult(com.zzc.webservice.travelagency.GetInternalTourismResponseGetInternalTourismResult getInternalTourismResult) {
        this.getInternalTourismResult = getInternalTourismResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetInternalTourismResponse)) return false;
        GetInternalTourismResponse other = (GetInternalTourismResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getInternalTourismResult==null && other.getGetInternalTourismResult()==null) || 
             (this.getInternalTourismResult!=null &&
              this.getInternalTourismResult.equals(other.getGetInternalTourismResult())));
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
        if (getGetInternalTourismResult() != null) {
            _hashCode += getGetInternalTourismResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetInternalTourismResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">GetInternalTourismResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getInternalTourismResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "GetInternalTourismResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">>GetInternalTourismResponse>GetInternalTourismResult"));
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
