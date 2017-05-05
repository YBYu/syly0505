/**
 * AddDragonBoatFestivalResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class AddDragonBoatFestivalResponse  implements java.io.Serializable {
    private com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult addDragonBoatFestivalResult;

    public AddDragonBoatFestivalResponse() {
    }

    public AddDragonBoatFestivalResponse(
           com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult addDragonBoatFestivalResult) {
           this.addDragonBoatFestivalResult = addDragonBoatFestivalResult;
    }


    /**
     * Gets the addDragonBoatFestivalResult value for this AddDragonBoatFestivalResponse.
     * 
     * @return addDragonBoatFestivalResult
     */
    public com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult getAddDragonBoatFestivalResult() {
        return addDragonBoatFestivalResult;
    }


    /**
     * Sets the addDragonBoatFestivalResult value for this AddDragonBoatFestivalResponse.
     * 
     * @param addDragonBoatFestivalResult
     */
    public void setAddDragonBoatFestivalResult(com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult addDragonBoatFestivalResult) {
        this.addDragonBoatFestivalResult = addDragonBoatFestivalResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddDragonBoatFestivalResponse)) return false;
        AddDragonBoatFestivalResponse other = (AddDragonBoatFestivalResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addDragonBoatFestivalResult==null && other.getAddDragonBoatFestivalResult()==null) || 
             (this.addDragonBoatFestivalResult!=null &&
              this.addDragonBoatFestivalResult.equals(other.getAddDragonBoatFestivalResult())));
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
        if (getAddDragonBoatFestivalResult() != null) {
            _hashCode += getAddDragonBoatFestivalResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddDragonBoatFestivalResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AddDragonBoatFestivalResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addDragonBoatFestivalResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AddDragonBoatFestivalResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>AddDragonBoatFestivalResponse>AddDragonBoatFestivalResult"));
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
