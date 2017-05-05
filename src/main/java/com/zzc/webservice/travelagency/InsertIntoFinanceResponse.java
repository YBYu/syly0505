/**
 * InsertIntoFinanceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class InsertIntoFinanceResponse  implements java.io.Serializable {
    private java.lang.String insertIntoFinanceResult;

    public InsertIntoFinanceResponse() {
    }

    public InsertIntoFinanceResponse(
           java.lang.String insertIntoFinanceResult) {
           this.insertIntoFinanceResult = insertIntoFinanceResult;
    }


    /**
     * Gets the insertIntoFinanceResult value for this InsertIntoFinanceResponse.
     * 
     * @return insertIntoFinanceResult
     */
    public java.lang.String getInsertIntoFinanceResult() {
        return insertIntoFinanceResult;
    }


    /**
     * Sets the insertIntoFinanceResult value for this InsertIntoFinanceResponse.
     * 
     * @param insertIntoFinanceResult
     */
    public void setInsertIntoFinanceResult(java.lang.String insertIntoFinanceResult) {
        this.insertIntoFinanceResult = insertIntoFinanceResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsertIntoFinanceResponse)) return false;
        InsertIntoFinanceResponse other = (InsertIntoFinanceResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.insertIntoFinanceResult==null && other.getInsertIntoFinanceResult()==null) || 
             (this.insertIntoFinanceResult!=null &&
              this.insertIntoFinanceResult.equals(other.getInsertIntoFinanceResult())));
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
        if (getInsertIntoFinanceResult() != null) {
            _hashCode += getInsertIntoFinanceResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsertIntoFinanceResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">InsertIntoFinanceResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insertIntoFinanceResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "InsertIntoFinanceResult"));
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
