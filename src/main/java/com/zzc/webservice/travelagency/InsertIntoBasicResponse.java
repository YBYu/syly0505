/**
 * InsertIntoBasicResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class InsertIntoBasicResponse  implements java.io.Serializable {
    private java.lang.String insertIntoBasicResult;

    public InsertIntoBasicResponse() {
    }

    public InsertIntoBasicResponse(
           java.lang.String insertIntoBasicResult) {
           this.insertIntoBasicResult = insertIntoBasicResult;
    }


    /**
     * Gets the insertIntoBasicResult value for this InsertIntoBasicResponse.
     * 
     * @return insertIntoBasicResult
     */
    public java.lang.String getInsertIntoBasicResult() {
        return insertIntoBasicResult;
    }


    /**
     * Sets the insertIntoBasicResult value for this InsertIntoBasicResponse.
     * 
     * @param insertIntoBasicResult
     */
    public void setInsertIntoBasicResult(java.lang.String insertIntoBasicResult) {
        this.insertIntoBasicResult = insertIntoBasicResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsertIntoBasicResponse)) return false;
        InsertIntoBasicResponse other = (InsertIntoBasicResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.insertIntoBasicResult==null && other.getInsertIntoBasicResult()==null) || 
             (this.insertIntoBasicResult!=null &&
              this.insertIntoBasicResult.equals(other.getInsertIntoBasicResult())));
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
        if (getInsertIntoBasicResult() != null) {
            _hashCode += getInsertIntoBasicResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsertIntoBasicResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">InsertIntoBasicResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insertIntoBasicResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "InsertIntoBasicResult"));
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
