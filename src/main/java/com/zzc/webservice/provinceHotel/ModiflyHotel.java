/**
 * ModiflyHotel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class ModiflyHotel  implements java.io.Serializable {
    private java.lang.String key;

    private java.lang.String userAccount;

    private com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic;

    public ModiflyHotel() {
    }

    public ModiflyHotel(
           java.lang.String key,
           java.lang.String userAccount,
           com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic) {
           this.key = key;
           this.userAccount = userAccount;
           this.traveAgenciesBasic = traveAgenciesBasic;
    }


    /**
     * Gets the key value for this ModiflyHotel.
     * 
     * @return key
     */
    public java.lang.String getKey() {
        return key;
    }


    /**
     * Sets the key value for this ModiflyHotel.
     * 
     * @param key
     */
    public void setKey(java.lang.String key) {
        this.key = key;
    }


    /**
     * Gets the userAccount value for this ModiflyHotel.
     * 
     * @return userAccount
     */
    public java.lang.String getUserAccount() {
        return userAccount;
    }


    /**
     * Sets the userAccount value for this ModiflyHotel.
     * 
     * @param userAccount
     */
    public void setUserAccount(java.lang.String userAccount) {
        this.userAccount = userAccount;
    }


    /**
     * Gets the traveAgenciesBasic value for this ModiflyHotel.
     * 
     * @return traveAgenciesBasic
     */
    public com.zzc.webservice.provinceHotel.TraveAgenciesBasic getTraveAgenciesBasic() {
        return traveAgenciesBasic;
    }


    /**
     * Sets the traveAgenciesBasic value for this ModiflyHotel.
     * 
     * @param traveAgenciesBasic
     */
    public void setTraveAgenciesBasic(com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic) {
        this.traveAgenciesBasic = traveAgenciesBasic;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ModiflyHotel)) return false;
        ModiflyHotel other = (ModiflyHotel) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.userAccount==null && other.getUserAccount()==null) || 
             (this.userAccount!=null &&
              this.userAccount.equals(other.getUserAccount()))) &&
            ((this.traveAgenciesBasic==null && other.getTraveAgenciesBasic()==null) || 
             (this.traveAgenciesBasic!=null &&
              this.traveAgenciesBasic.equals(other.getTraveAgenciesBasic())));
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getUserAccount() != null) {
            _hashCode += getUserAccount().hashCode();
        }
        if (getTraveAgenciesBasic() != null) {
            _hashCode += getTraveAgenciesBasic().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ModiflyHotel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ModiflyHotel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traveAgenciesBasic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TraveAgenciesBasic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "TraveAgenciesBasic"));
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
