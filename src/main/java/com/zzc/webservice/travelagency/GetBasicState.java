/**
 * GetBasicState.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class GetBasicState  implements java.io.Serializable {
    private java.lang.String areaID;

    private java.lang.String userName;

    private java.lang.String verificationCode;

    public GetBasicState() {
    }

    public GetBasicState(
           java.lang.String areaID,
           java.lang.String userName,
           java.lang.String verificationCode) {
           this.areaID = areaID;
           this.userName = userName;
           this.verificationCode = verificationCode;
    }


    /**
     * Gets the areaID value for this GetBasicState.
     * 
     * @return areaID
     */
    public java.lang.String getAreaID() {
        return areaID;
    }


    /**
     * Sets the areaID value for this GetBasicState.
     * 
     * @param areaID
     */
    public void setAreaID(java.lang.String areaID) {
        this.areaID = areaID;
    }


    /**
     * Gets the userName value for this GetBasicState.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this GetBasicState.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the verificationCode value for this GetBasicState.
     * 
     * @return verificationCode
     */
    public java.lang.String getVerificationCode() {
        return verificationCode;
    }


    /**
     * Sets the verificationCode value for this GetBasicState.
     * 
     * @param verificationCode
     */
    public void setVerificationCode(java.lang.String verificationCode) {
        this.verificationCode = verificationCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBasicState)) return false;
        GetBasicState other = (GetBasicState) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.areaID==null && other.getAreaID()==null) || 
             (this.areaID!=null &&
              this.areaID.equals(other.getAreaID()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.verificationCode==null && other.getVerificationCode()==null) || 
             (this.verificationCode!=null &&
              this.verificationCode.equals(other.getVerificationCode())));
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
        if (getAreaID() != null) {
            _hashCode += getAreaID().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getVerificationCode() != null) {
            _hashCode += getVerificationCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetBasicState.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">GetBasicState"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "AreaID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "UserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("verificationCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "VerificationCode"));
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