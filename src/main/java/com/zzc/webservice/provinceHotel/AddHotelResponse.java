/**
 * AddHotelResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class AddHotelResponse  implements java.io.Serializable {
    private com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult addHotelResult;

    public AddHotelResponse() {
    }

    public AddHotelResponse(
           com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult addHotelResult) {
           this.addHotelResult = addHotelResult;
    }


    /**
     * Gets the addHotelResult value for this AddHotelResponse.
     * 
     * @return addHotelResult
     */
    public com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult getAddHotelResult() {
        return addHotelResult;
    }


    /**
     * Sets the addHotelResult value for this AddHotelResponse.
     * 
     * @param addHotelResult
     */
    public void setAddHotelResult(com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult addHotelResult) {
        this.addHotelResult = addHotelResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddHotelResponse)) return false;
        AddHotelResponse other = (AddHotelResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addHotelResult==null && other.getAddHotelResult()==null) || 
             (this.addHotelResult!=null &&
              this.addHotelResult.equals(other.getAddHotelResult())));
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
        if (getAddHotelResult() != null) {
            _hashCode += getAddHotelResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddHotelResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AddHotelResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addHotelResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AddHotelResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>AddHotelResponse>AddHotelResult"));
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
