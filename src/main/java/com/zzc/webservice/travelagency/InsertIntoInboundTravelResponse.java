/**
 * InsertIntoInboundTravelResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class InsertIntoInboundTravelResponse  implements java.io.Serializable {
    private java.lang.String insertIntoInboundTravelResult;

    public InsertIntoInboundTravelResponse() {
    }

    public InsertIntoInboundTravelResponse(
           java.lang.String insertIntoInboundTravelResult) {
           this.insertIntoInboundTravelResult = insertIntoInboundTravelResult;
    }


    /**
     * Gets the insertIntoInboundTravelResult value for this InsertIntoInboundTravelResponse.
     * 
     * @return insertIntoInboundTravelResult
     */
    public java.lang.String getInsertIntoInboundTravelResult() {
        return insertIntoInboundTravelResult;
    }


    /**
     * Sets the insertIntoInboundTravelResult value for this InsertIntoInboundTravelResponse.
     * 
     * @param insertIntoInboundTravelResult
     */
    public void setInsertIntoInboundTravelResult(java.lang.String insertIntoInboundTravelResult) {
        this.insertIntoInboundTravelResult = insertIntoInboundTravelResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsertIntoInboundTravelResponse)) return false;
        InsertIntoInboundTravelResponse other = (InsertIntoInboundTravelResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.insertIntoInboundTravelResult==null && other.getInsertIntoInboundTravelResult()==null) || 
             (this.insertIntoInboundTravelResult!=null &&
              this.insertIntoInboundTravelResult.equals(other.getInsertIntoInboundTravelResult())));
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
        if (getInsertIntoInboundTravelResult() != null) {
            _hashCode += getInsertIntoInboundTravelResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsertIntoInboundTravelResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">InsertIntoInboundTravelResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insertIntoInboundTravelResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "InsertIntoInboundTravelResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
