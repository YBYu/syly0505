/**
 * AddAuditAndMonthly.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class AddAuditAndMonthly  implements java.io.Serializable {
    private java.lang.String key;

    private com.zzc.webservice.provinceHotel.Audit audit;

    private com.zzc.webservice.provinceHotel.TheMonth[] listTheMonth;

    private java.lang.String reportedEnterpriseUserAccount;

    public AddAuditAndMonthly() {
    }

    public AddAuditAndMonthly(
           java.lang.String key,
           com.zzc.webservice.provinceHotel.Audit audit,
           com.zzc.webservice.provinceHotel.TheMonth[] listTheMonth,
           java.lang.String reportedEnterpriseUserAccount) {
           this.key = key;
           this.audit = audit;
           this.listTheMonth = listTheMonth;
           this.reportedEnterpriseUserAccount = reportedEnterpriseUserAccount;
    }


    /**
     * Gets the key value for this AddAuditAndMonthly.
     * 
     * @return key
     */
    public java.lang.String getKey() {
        return key;
    }


    /**
     * Sets the key value for this AddAuditAndMonthly.
     * 
     * @param key
     */
    public void setKey(java.lang.String key) {
        this.key = key;
    }


    /**
     * Gets the audit value for this AddAuditAndMonthly.
     * 
     * @return audit
     */
    public com.zzc.webservice.provinceHotel.Audit getAudit() {
        return audit;
    }


    /**
     * Sets the audit value for this AddAuditAndMonthly.
     * 
     * @param audit
     */
    public void setAudit(com.zzc.webservice.provinceHotel.Audit audit) {
        this.audit = audit;
    }


    /**
     * Gets the listTheMonth value for this AddAuditAndMonthly.
     * 
     * @return listTheMonth
     */
    public com.zzc.webservice.provinceHotel.TheMonth[] getListTheMonth() {
        return listTheMonth;
    }


    /**
     * Sets the listTheMonth value for this AddAuditAndMonthly.
     * 
     * @param listTheMonth
     */
    public void setListTheMonth(com.zzc.webservice.provinceHotel.TheMonth[] listTheMonth) {
        this.listTheMonth = listTheMonth;
    }


    /**
     * Gets the reportedEnterpriseUserAccount value for this AddAuditAndMonthly.
     * 
     * @return reportedEnterpriseUserAccount
     */
    public java.lang.String getReportedEnterpriseUserAccount() {
        return reportedEnterpriseUserAccount;
    }


    /**
     * Sets the reportedEnterpriseUserAccount value for this AddAuditAndMonthly.
     * 
     * @param reportedEnterpriseUserAccount
     */
    public void setReportedEnterpriseUserAccount(java.lang.String reportedEnterpriseUserAccount) {
        this.reportedEnterpriseUserAccount = reportedEnterpriseUserAccount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddAuditAndMonthly)) return false;
        AddAuditAndMonthly other = (AddAuditAndMonthly) obj;
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
            ((this.audit==null && other.getAudit()==null) || 
             (this.audit!=null &&
              this.audit.equals(other.getAudit()))) &&
            ((this.listTheMonth==null && other.getListTheMonth()==null) || 
             (this.listTheMonth!=null &&
              java.util.Arrays.equals(this.listTheMonth, other.getListTheMonth()))) &&
            ((this.reportedEnterpriseUserAccount==null && other.getReportedEnterpriseUserAccount()==null) || 
             (this.reportedEnterpriseUserAccount!=null &&
              this.reportedEnterpriseUserAccount.equals(other.getReportedEnterpriseUserAccount())));
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
        if (getAudit() != null) {
            _hashCode += getAudit().hashCode();
        }
        if (getListTheMonth() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListTheMonth());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListTheMonth(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReportedEnterpriseUserAccount() != null) {
            _hashCode += getReportedEnterpriseUserAccount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddAuditAndMonthly.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AddAuditAndMonthly"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("audit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "audit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Audit"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listTheMonth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "listTheMonth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "TheMonth"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "TheMonth"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportedEnterpriseUserAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "reportedEnterpriseUserAccount"));
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
