/**
 * AddMoonFestivalResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class AddMoonFestivalResponse  implements java.io.Serializable {
    private com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult addMoonFestivalResult;

    public AddMoonFestivalResponse() {
    }

    public AddMoonFestivalResponse(
           com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult addMoonFestivalResult) {
           this.addMoonFestivalResult = addMoonFestivalResult;
    }


    /**
     * Gets the addMoonFestivalResult value for this AddMoonFestivalResponse.
     * 
     * @return addMoonFestivalResult
     */
    public com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult getAddMoonFestivalResult() {
        return addMoonFestivalResult;
    }


    /**
     * Sets the addMoonFestivalResult value for this AddMoonFestivalResponse.
     * 
     * @param addMoonFestivalResult
     */
    public void setAddMoonFestivalResult(com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult addMoonFestivalResult) {
        this.addMoonFestivalResult = addMoonFestivalResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddMoonFestivalResponse)) return false;
        AddMoonFestivalResponse other = (AddMoonFestivalResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addMoonFestivalResult==null && other.getAddMoonFestivalResult()==null) || 
             (this.addMoonFestivalResult!=null &&
              this.addMoonFestivalResult.equals(other.getAddMoonFestivalResult())));
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
        if (getAddMoonFestivalResult() != null) {
            _hashCode += getAddMoonFestivalResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddMoonFestivalResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AddMoonFestivalResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addMoonFestivalResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AddMoonFestivalResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>AddMoonFestivalResponse>AddMoonFestivalResult"));
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
