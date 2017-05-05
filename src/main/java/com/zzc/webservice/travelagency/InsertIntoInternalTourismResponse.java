/**
 * InsertIntoInternalTourismResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class InsertIntoInternalTourismResponse  implements java.io.Serializable {
    private java.lang.String insertIntoInternalTourismResult;

    public InsertIntoInternalTourismResponse() {
    }

    public InsertIntoInternalTourismResponse(
           java.lang.String insertIntoInternalTourismResult) {
           this.insertIntoInternalTourismResult = insertIntoInternalTourismResult;
    }


    /**
     * Gets the insertIntoInternalTourismResult value for this InsertIntoInternalTourismResponse.
     * 
     * @return insertIntoInternalTourismResult
     */
    public java.lang.String getInsertIntoInternalTourismResult() {
        return insertIntoInternalTourismResult;
    }


    /**
     * Sets the insertIntoInternalTourismResult value for this InsertIntoInternalTourismResponse.
     * 
     * @param insertIntoInternalTourismResult
     */
    public void setInsertIntoInternalTourismResult(java.lang.String insertIntoInternalTourismResult) {
        this.insertIntoInternalTourismResult = insertIntoInternalTourismResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsertIntoInternalTourismResponse)) return false;
        InsertIntoInternalTourismResponse other = (InsertIntoInternalTourismResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.insertIntoInternalTourismResult==null && other.getInsertIntoInternalTourismResult()==null) || 
             (this.insertIntoInternalTourismResult!=null &&
              this.insertIntoInternalTourismResult.equals(other.getInsertIntoInternalTourismResult())));
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
        if (getInsertIntoInternalTourismResult() != null) {
            _hashCode += getInsertIntoInternalTourismResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsertIntoInternalTourismResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">InsertIntoInternalTourismResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insertIntoInternalTourismResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "InsertIntoInternalTourismResult"));
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
