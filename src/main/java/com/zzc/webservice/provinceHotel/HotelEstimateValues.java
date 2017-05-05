/**
 * HotelEstimateValues.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class HotelEstimateValues  implements java.io.Serializable {
    private java.lang.Integer domestic;

    private java.lang.Integer foreigner;

    private java.lang.Integer hongkong;

    private java.lang.Integer macao;

    private java.lang.Integer taiwan;

    private java.lang.Integer abroad;

    private java.lang.Integer total;

    public HotelEstimateValues() {
    }

    public HotelEstimateValues(
           java.lang.Integer domestic,
           java.lang.Integer foreigner,
           java.lang.Integer hongkong,
           java.lang.Integer macao,
           java.lang.Integer taiwan,
           java.lang.Integer abroad,
           java.lang.Integer total) {
           this.domestic = domestic;
           this.foreigner = foreigner;
           this.hongkong = hongkong;
           this.macao = macao;
           this.taiwan = taiwan;
           this.abroad = abroad;
           this.total = total;
    }


    /**
     * Gets the domestic value for this HotelEstimateValues.
     * 
     * @return domestic
     */
    public java.lang.Integer getDomestic() {
        return domestic;
    }


    /**
     * Sets the domestic value for this HotelEstimateValues.
     * 
     * @param domestic
     */
    public void setDomestic(java.lang.Integer domestic) {
        this.domestic = domestic;
    }


    /**
     * Gets the foreigner value for this HotelEstimateValues.
     * 
     * @return foreigner
     */
    public java.lang.Integer getForeigner() {
        return foreigner;
    }


    /**
     * Sets the foreigner value for this HotelEstimateValues.
     * 
     * @param foreigner
     */
    public void setForeigner(java.lang.Integer foreigner) {
        this.foreigner = foreigner;
    }


    /**
     * Gets the hongkong value for this HotelEstimateValues.
     * 
     * @return hongkong
     */
    public java.lang.Integer getHongkong() {
        return hongkong;
    }


    /**
     * Sets the hongkong value for this HotelEstimateValues.
     * 
     * @param hongkong
     */
    public void setHongkong(java.lang.Integer hongkong) {
        this.hongkong = hongkong;
    }


    /**
     * Gets the macao value for this HotelEstimateValues.
     * 
     * @return macao
     */
    public java.lang.Integer getMacao() {
        return macao;
    }


    /**
     * Sets the macao value for this HotelEstimateValues.
     * 
     * @param macao
     */
    public void setMacao(java.lang.Integer macao) {
        this.macao = macao;
    }


    /**
     * Gets the taiwan value for this HotelEstimateValues.
     * 
     * @return taiwan
     */
    public java.lang.Integer getTaiwan() {
        return taiwan;
    }


    /**
     * Sets the taiwan value for this HotelEstimateValues.
     * 
     * @param taiwan
     */
    public void setTaiwan(java.lang.Integer taiwan) {
        this.taiwan = taiwan;
    }


    /**
     * Gets the abroad value for this HotelEstimateValues.
     * 
     * @return abroad
     */
    public java.lang.Integer getAbroad() {
        return abroad;
    }


    /**
     * Sets the abroad value for this HotelEstimateValues.
     * 
     * @param abroad
     */
    public void setAbroad(java.lang.Integer abroad) {
        this.abroad = abroad;
    }


    /**
     * Gets the total value for this HotelEstimateValues.
     * 
     * @return total
     */
    public java.lang.Integer getTotal() {
        return total;
    }


    /**
     * Sets the total value for this HotelEstimateValues.
     * 
     * @param total
     */
    public void setTotal(java.lang.Integer total) {
        this.total = total;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HotelEstimateValues)) return false;
        HotelEstimateValues other = (HotelEstimateValues) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.domestic==null && other.getDomestic()==null) || 
             (this.domestic!=null &&
              this.domestic.equals(other.getDomestic()))) &&
            ((this.foreigner==null && other.getForeigner()==null) || 
             (this.foreigner!=null &&
              this.foreigner.equals(other.getForeigner()))) &&
            ((this.hongkong==null && other.getHongkong()==null) || 
             (this.hongkong!=null &&
              this.hongkong.equals(other.getHongkong()))) &&
            ((this.macao==null && other.getMacao()==null) || 
             (this.macao!=null &&
              this.macao.equals(other.getMacao()))) &&
            ((this.taiwan==null && other.getTaiwan()==null) || 
             (this.taiwan!=null &&
              this.taiwan.equals(other.getTaiwan()))) &&
            ((this.abroad==null && other.getAbroad()==null) || 
             (this.abroad!=null &&
              this.abroad.equals(other.getAbroad()))) &&
            ((this.total==null && other.getTotal()==null) || 
             (this.total!=null &&
              this.total.equals(other.getTotal())));
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
        if (getDomestic() != null) {
            _hashCode += getDomestic().hashCode();
        }
        if (getForeigner() != null) {
            _hashCode += getForeigner().hashCode();
        }
        if (getHongkong() != null) {
            _hashCode += getHongkong().hashCode();
        }
        if (getMacao() != null) {
            _hashCode += getMacao().hashCode();
        }
        if (getTaiwan() != null) {
            _hashCode += getTaiwan().hashCode();
        }
        if (getAbroad() != null) {
            _hashCode += getAbroad().hashCode();
        }
        if (getTotal() != null) {
            _hashCode += getTotal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HotelEstimateValues.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimateValues"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domestic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Domestic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foreigner");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Foreigner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hongkong");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Hongkong"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("macao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Macao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taiwan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Taiwan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abroad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Abroad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
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
